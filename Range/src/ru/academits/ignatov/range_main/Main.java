package ru.academits.ignatov.range_main;

import ru.academits.ignatov.range.Range;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Range range1 = new Range(2.3, 10.4);

        System.out.println("Начальная точка диапазона равна: " + range1.getFrom());
        System.out.println("Конечная точка диапазона равна: " + range1.getTo());

        System.out.println("Длина диапазона: " + range1.getLength());

        if (range1.isInside(8)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }

        range1.setFrom(2);
        range1.setTo(10);

        System.out.println("Начальная точка нового диапазона равна: " + range1.getFrom());
        System.out.println("Конечная точка нового диапазона равна: " + range1.getTo());

        System.out.println("Длина нового диапазона: " + range1.getLength());

        if (range1.isInside(8)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }

        Range range2 = new Range(3, 6);

        System.out.println("Проверка на пересечение интервалов: " + range1.getIntersection(range2));

        Range[] union = range1.getUnion(range2);
        System.out.println("Результат объединения интервалов: " + Arrays.toString(union));

        Range[] difference = range1.getDifference(range2);
        System.out.println("Результат разности интервалов: " + Arrays.toString(difference));
    }
}