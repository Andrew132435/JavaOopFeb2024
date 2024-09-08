package ru.academits.ignatov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private static void checkIndex(int index, int maxIndex) {
        if (index < 0 || index >= maxIndex) {
            throw new IndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + (maxIndex - 1)
                    + ". Индекс равен " + index);
        }
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public E get(int index) {
        checkIndex(index, count);

        return getItem(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index, count);

        ListItem<E> item = getItem(index);
        E oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public E removeByIndex(int index) {
        checkIndex(index, count);

        if (count == 0) {
            return removeFirst();
        }

        ListItem<E> currentItem = getItem(index);
        ListItem<E> previousItem = getItem(index - 1);
        E removedData = currentItem.getData();
        previousItem.setNext(currentItem.getNext());
        count--;

        return removedData;
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(E data, int index) {
        checkIndex(index, count + 1);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        count++;
    }

    public boolean removeByData(E data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeFirst();
            return true;
        }

        for (ListItem<E> previousItem = head, currentItem = previousItem.getNext(); currentItem != null;
             previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(currentItem.getData(), data)) {
                previousItem.setNext(currentItem.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public E removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст");
        }

        ListItem<E> removedItem = head;
        head = head.getNext();
        count--;

        return removedItem.getData();
    }

    public void reverse() {
        ListItem<E> reversedItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(reversedItem);
            reversedItem = currentItem;
            currentItem = nextItem;
        }

        head = reversedItem;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();

        if (count == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(getFirst(), head);

        for (ListItem<E> currentItem = head.getNext(), currentItemCopy = listCopy.head; currentItem != null;
             currentItem = currentItem.getNext(), currentItemCopy = currentItemCopy.getNext()) {
            currentItemCopy.setNext(new ListItem<>(currentItem.getData()));
        }

        listCopy.count = count;

        return listCopy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');

        for (ListItem<E> item = head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(']');

        return stringBuilder.toString();
    }
}