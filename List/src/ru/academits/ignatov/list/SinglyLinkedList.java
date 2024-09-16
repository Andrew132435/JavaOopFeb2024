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
        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + maxIndex
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
        checkIndex(index, count - 1);

        return getItem(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index, count - 1);

        ListItem<E> item = getItem(index);
        E oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public E removeByIndex(int index) {
        checkIndex(index, count - 1);

        if (count == 0) {
            return removeFirst();
        }

        ListItem<E> previousItem = getItem(index - 1);
        ListItem<E> removedItem = previousItem.getNext();
        previousItem.setNext(removedItem.getNext());
        count--;

        return removedItem.getData();
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(E data, int index) {
        checkIndex(index, count);

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

        E removedData = head.getData();
        head = head.getNext();
        count--;

        return removedData;
    }

    public void reverse() {
        ListItem<E> previousItem = null;
        ListItem<E> currentItem = head;

        while (currentItem != null) {
            ListItem<E> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();

        if (count == 0) {
            return listCopy;
        }

        listCopy.head = new ListItem<>(head.getData());

        for (ListItem<E> currentItem = head.getNext(), copyCurrentItem = listCopy.head; currentItem != null;
             currentItem = currentItem.getNext(), copyCurrentItem = copyCurrentItem.getNext()) {
            copyCurrentItem.setNext(new ListItem<>(currentItem.getData()));
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