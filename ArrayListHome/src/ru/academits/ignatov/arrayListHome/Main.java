package ru.academits.ignatov.arrayListHome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> fileLinesList = new ArrayList<>();

            String line;

            while ((line = reader.readLine()) != null) {
                fileLinesList.add(line);
            }

            return fileLinesList;
        }
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbersList) {
        for (int i = numbersList.size() - 1; i >= 0; i--) {
            if (numbersList.get(i) % 2 == 0) {
                numbersList.remove(i);
            }
        }
    }

    public static ArrayList<Integer> getNumbersListWithoutRepeats(ArrayList<Integer> numbersList) {
        ArrayList<Integer> numbersListWithoutRepeats = new ArrayList<>(numbersList.size());

        for (Integer number : numbersList) {
            if (!numbersListWithoutRepeats.contains(number)) {
                numbersListWithoutRepeats.add(number);
            }
        }

        return numbersListWithoutRepeats;
    }

    public static void main(String[] args) {
        System.out.println("1. Прочитать в список все строки из файла");

        try {
            ArrayList<String> fileLinesList = getFileLines("ArrayListHome/src/input.txt");
            System.out.println(fileLinesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла");
            System.out.println(e.getMessage());
        }

        System.out.println();

        System.out.println("2. Есть список из целых чисел. Удалить из него все четные числа. В этой задаче новый список создавать нельзя");

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
        System.out.println("Исходный список:");
        System.out.println(numbersList);
        removeEvenNumbers(numbersList);
        System.out.println("Этот же список после удаления чётных чисел:");
        System.out.println(numbersList);

        System.out.println();

        System.out.println("3. Есть список из целых чисел, в нём некоторые числа могут" + System.lineSeparator() +
                "повторяться. Надо создать новый список, в котором будут" + System.lineSeparator() +
                "элементы первого списка в таком же порядке, но без повторений");

        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(0, 1, 2, 0, 2, 5, 5, -1, -2, -2));
        System.out.println("Исходный список:");
        System.out.println(numbersList2);
        System.out.println("Новый список без повторов:");
        System.out.println(getNumbersListWithoutRepeats(numbersList2));
    }
}