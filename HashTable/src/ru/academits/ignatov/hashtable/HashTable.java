package ru.academits.ignatov.hashtable;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private int size;
    private final List<E>[] buckets;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        buckets = (List<E>[]) new List[10];
    }

    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Вместимость хэш-таблицы не должна быть меньше 1. " +
                    "Вместимость равна: " + initialCapacity);
        }

        //noinspection unchecked
        buckets = (List<E>[]) new List[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % buckets.length);
    }

    @Override
    public boolean add(E e) {
        int index = getIndex(e);

        if (buckets[index] == null) {
            buckets[index] = new ArrayList<>();
        }

        buckets[index].add(e);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return buckets[index] != null && buckets[index].contains(o);
    }

    private class HashTableIterator implements Iterator<E> {
        private int visitedItemsCount;
        private int bucketIndex;
        private int bucketItemIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В хэш-таблице закончились элементы");
            }

            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("За время обхода хэш-таблица была изменена");
            }

            while (bucketIndex < buckets.length) {
                if (buckets[bucketIndex] != null && bucketItemIndex < buckets[bucketIndex].size() - 1) {
                    bucketItemIndex++;
                    visitedItemsCount++;
                    return buckets[bucketIndex].get(bucketItemIndex);
                }

                bucketIndex++;
                bucketItemIndex = -1;
            }

            throw new NoSuchElementException("В хэш-таблице закончились элементы");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        for (List<E> bucket : buckets) {
            if (bucket != null) {
                bucket.clear();
            }
        }

        size = 0;
        modCount++;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (E item : this) {
            array[i] = item;
            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (buckets[index] == null) {
            return false;
        }

        if (buckets[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E item : c) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        int oldSize = size;

        for (List<E> bucket : buckets) {
            if (bucket != null) {
                int bucketSize = bucket.size();

                if (bucket.removeAll(c)) {
                    size -= bucketSize - bucket.size();
                }
            }
        }

        boolean isModified = oldSize != size;

        if (isModified) {
            ++modCount;
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (List<E> bucket : buckets) {
            if (bucket != null) {
                size -= bucket.size();
                bucket.retainAll(c);
                size += bucket.size();
            }
        }

        if (initialSize != size) {
            modCount++;
        }

        return initialSize != size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (List<E> bucket : buckets) {
            if (bucket != null && !bucket.isEmpty()) {
                stringBuilder.append(bucket).append(", ");
            }
        }

        return stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']').toString();
    }
}