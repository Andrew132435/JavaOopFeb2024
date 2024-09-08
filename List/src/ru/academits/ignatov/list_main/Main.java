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
        System.out.println("Значение 1-го элемента: " + singlyLinkedList.getFirst());
        System.out.println();

        System.out.println("Поменяем значение пятого элемента. Старое значение: " + singlyLinkedList.set(4, 500));
        System.out.println("Новое значение 5-го элемента: " + singlyLinkedList.get(4));
        System.out.println(singlyLinkedList);
        System.out.println();

        System.out.println("Удалим 3-й элемент. Значение удаляемого элемента: " + singlyLinkedList.removeByIndex(2));
        System.out.println();

        System.out.println("Добавим новый элемент со значением -500 по индексу 1:");
        singlyLinkedList.addByIndex(-500, 1);
        System.out.println(singlyLinkedList);
        System.out.println();

        System.out.println("Удалим узел со значением 500. Результат удаления: " + singlyLinkedList.removeByData(500));
        System.out.println(singlyLinkedList);
        System.out.println();

        System.out.println("Удалим 1-й элемент. Значение удаляемого элемента: " + singlyLinkedList.removeFirst());
        System.out.println();

        System.out.println("Список после удаления: " + singlyLinkedList);
        System.out.println();

        singlyLinkedList.reverse();
        System.out.println("Развернём список: " + singlyLinkedList);
        System.out.println();

        System.out.println("Создадим копию списка:");
        SinglyLinkedList<Integer> singlyLinkedListCopy = singlyLinkedList.copy();
        System.out.println("Исходный список: " + singlyLinkedList);
        System.out.println("Копия исходного списка: " + singlyLinkedListCopy);
    }
}