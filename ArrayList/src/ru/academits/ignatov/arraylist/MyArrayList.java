package ru.academits.ignatov.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        this.items = (E[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не должна быть меньше 0. Вместимость равна: " + initialCapacity);
        }

        //noinspection unchecked
        this.items = (E[]) new Object[initialCapacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public class MyIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменён!");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Список закончился! Следующий элемент отсутствует!");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    private static void checkIndex(int index, int maxIndex) {
        if (index < 0 || index >= maxIndex) {
            throw new IndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + (maxIndex - 1)
                    + ". Индекс равен " + index);
        }
    }

    public void increaseCapacity() {
        if (items.length == 0) {
            items = Arrays.copyOf(items, 10);
            return;
        }

        items = Arrays.copyOf(items, items.length * 2);
    }

    @Override
    public void add(int index, E element) {
        checkIndex(index, size);

        if (size == items.length) {
            increaseCapacity();
        }

        if (index != size) {
            System.arraycopy(items, index, items, index + 1, size - index);
        }

        items[index] = element;
        size++;
        modCount++;
    }

    @Override
    public boolean add(E e) {
        add(size, e);
        modCount++;
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index, size);

        E removedItem = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        items[size - 1] = null;
        size--;
        modCount++;

        return removedItem;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c.isEmpty()) {
            return true;
        }

        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index, size);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity((size + c.size()));

        if (index != size) {
            System.arraycopy(items, index, items, index + c.size(), size - index);
        }

        int i = index;

        for (E element : c) {
            items[i] = element;
            i++;
        }

        size += c.size();
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (isEmpty() || c.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty()) {
            return false;
        }

        if (c.isEmpty()) {
            clear();
            return true;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size, null);

        size = 0;
        modCount++;
    }

    @Override
    public E get(int index) {
        checkIndex(index, size);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, size - 1);

        E oldItem = items[index];
        items[index] = element;

        return oldItem;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        MyArrayList<E> arrayList = (MyArrayList<E>) obj;

        if (size != arrayList.size) {
            return false;
        }

        boolean hasEqual = true;

        for (int i = 0; i < size; i++) {
            if (!items[i].equals(arrayList.get(i))) {
                return false;
            }
        }

        return hasEqual;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;

        for (E item : items) {
            hash += prime * hash + Double.hashCode((Double) item);
        }

        return hash;
    }
}