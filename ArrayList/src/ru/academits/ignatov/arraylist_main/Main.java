package ru.academits.ignatov.arraylist_main;

import ru.academits.ignatov.arraylist.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        System.out.println(stringArrayList);

        ArrayList<Integer> intArrayList = new ArrayList<>(20);
        System.out.println(intArrayList);
    }
}