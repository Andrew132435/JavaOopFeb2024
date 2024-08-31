package ru.academits.ignatov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public T getFirstItemData() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст");
        }

        return head.getData();
    }

    private static void checkIndex(int index, int count) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс должен быть в диапазоне от 0 до " + (count - 1)
                    + ". Индекс равен " + index);
        }
    }

    public ListItem<T> getItem(int index) {
        checkIndex(index, count);

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    public T getValueByIndex(int index) {
        checkIndex(index, count);

        return getItem(index).getData();
    }

    public T setValueByIndex(int index, T data) {
        checkIndex(index, count);

        ListItem<T> item = getItem(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index, count);

        if (count == 0) {
            return removeFirstItem();
        }

        ListItem<T> item = getItem(index - 1);
        T deletedData = item.getNext().getData();
        item.setNext(item.getNext().getNext());
        count--;

        return deletedData;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addByIndex(T data, int index) {
        checkIndex(index, count + 1);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
        count++;
    }

    public boolean removeByData(T data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeFirstItem();
            return true;
        }

        for (ListItem<T> previousItem = head, currentItem = previousItem.getNext(); currentItem != null;
             previousItem = currentItem, currentItem = currentItem.getNext()) {
            if (Objects.equals(currentItem.getData(), data)) {
                previousItem.setNext(currentItem.getNext());
                count--;
                return true;
            }
        }

        return false;
    }

    public T removeFirstItem() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст");
        }

        ListItem<T> deletedItem = head;
        head = head.getNext();
        count--;

        return deletedItem.getData();
    }

    public void reverse() {
        ListItem<T> tempItem = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(tempItem);
            tempItem = currentItem;
            currentItem = nextItem;
        }

        head = tempItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> listCopy = new SinglyLinkedList<>();

        if (count == 0) {
            return listCopy;
        }

        listCopy.addFirst(head.getData());

        for (ListItem<T> currentItem = head.getNext(), currentCopyItem = listCopy.head; currentItem != null;
             currentItem = currentItem.getNext(), currentCopyItem = currentCopyItem.getNext()) {
            currentCopyItem.setNext(new ListItem<>(currentItem.getData()));
        }

        listCopy.count = count;

        return listCopy;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            stringBuilder.append(item.getData()).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("]");

        return stringBuilder.toString();
    }
}