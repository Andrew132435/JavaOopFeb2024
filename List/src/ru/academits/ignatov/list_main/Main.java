package ru.academits.ignatov.list_main;

import ru.academits.ignatov.list.SinglyLinkedList;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        Random random = new Random();

        System.out.println("Создадим список случайных целых чисел:");
        for (int i = 0; i < 10; i++) {
            singlyLinkedList.addFirst(random.nextInt(-25, 26));
        }

        System.out.println(singlyLinkedList);
        System.out.println("Размер списка: " + singlyLinkedList.getCount() + " элементов.");
        System.out.println("Значение 1-го элемента: " + singlyLinkedList.getFirstItemData());
        System.out.println();

        System.out.println("Поменяем значение пятого элемента. Старое значение: " + singlyLinkedList.setValueByIndex(4, 500));
        System.out.println("Новое значение 5-го элемента: " + singlyLinkedList.getValueByIndex(4));
        System.out.println(singlyLinkedList);
        System.out.println();

        System.out.println("Удалим 3-й элемент. Значение удаляемого элемента: " + singlyLinkedList.removeByIndex(2));
        System.out.println();

        System.out.println("Дабавим новый элемент со значением -500 по индексу 1: ");
        singlyLinkedList.addByIndex(-500, 1);
        System.out.println(singlyLinkedList);
        System.out.println();

        System.out.println("Удалим узел со значением 500. Результат удаления: " + singlyLinkedList.removeByData(500));
        System.out.println(singlyLinkedList);
        System.out.println();

        System.out.println("Удалим 1-й элемент. Значение удаляемого элемента: " + singlyLinkedList.removeFirstItem());
        System.out.println();

        System.out.println("Список после удаления: " + singlyLinkedList);
        System.out.println();

        singlyLinkedList.reverse();
        System.out.println("Развернём список: " + singlyLinkedList);
        System.out.println();

        System.out.println("Создадим копию списка:");
        SinglyLinkedList<Integer> copySinglyLinkedList = singlyLinkedList.copy();
        System.out.println("Исходный список: " + singlyLinkedList);
        System.out.println("Копия исходного списка: " + copySinglyLinkedList);
    }
}
