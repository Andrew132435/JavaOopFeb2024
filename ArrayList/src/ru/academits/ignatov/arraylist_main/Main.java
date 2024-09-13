package ru.academits.ignatov.arraylist_main;

import ru.academits.ignatov.arraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<String> stringArrayList = new MyArrayList<>();
        System.out.println(stringArrayList);

        MyArrayList<Integer> intArrayList = new MyArrayList<>(20);
        System.out.println(intArrayList);
    }
}