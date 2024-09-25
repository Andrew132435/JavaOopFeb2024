package ru.academits.ignatov.hashtable_main;

import ru.academits.ignatov.hashtable.HashTable;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> intHashTable = new HashTable<>();
        System.out.println(intHashTable);
        System.out.println();

        HashTable<String> stringsHashTable = new HashTable<>(10);
        System.out.println(stringsHashTable);
        System.out.println(stringsHashTable.size());

        stringsHashTable.add("Nadya");
        stringsHashTable.add("Andrew");
        stringsHashTable.add("Nadya");
        stringsHashTable.add("123123");
        stringsHashTable.add("Artem");

        System.out.println(stringsHashTable);
        System.out.println(stringsHashTable.size());
        System.out.println(stringsHashTable.contains("Andrew"));
        System.out.println();

        for (String s : stringsHashTable) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println(stringsHashTable.remove("Nadya"));
        System.out.println(stringsHashTable);
        System.out.println(stringsHashTable.size());
        System.out.println();

        System.out.println(Arrays.toString(stringsHashTable.toArray()));

        stringsHashTable.clear();
        System.out.println(stringsHashTable);
        System.out.println(stringsHashTable.size());
        System.out.println();
    }
}