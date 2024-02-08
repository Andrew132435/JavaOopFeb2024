package ru.academits.ignatov.range_main;

import ru.academits.ignatov.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(2.3, 10.4);

        System.out.println("Начальная точка диапазона равна: " + range.getFrom());
        System.out.println("Конечная точка диапазона равна: " + range.getTo());

        System.out.println("Длина диапазона: " + range.getLength());

        if (range.isInside(8)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }

        range.setFrom(-3.5);
        range.setTo(3.5);

        System.out.println("Начальная точка нового диапазона равна: " + range.getFrom());
        System.out.println("Конечная точка нового диапазона равна: " + range.getTo());

        System.out.println("Длина нового диапазона: " + range.getLength());

        if (range.isInside(8)) {
            System.out.println("Число принадлежит диапазону");
        } else {
            System.out.println("Число не принадлежит диапазону");
        }
    }
}