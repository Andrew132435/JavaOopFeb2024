package ru.academits.ignatov.main;

import ru.academits.ignatov.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector1 = new Vector(5);
        System.out.println("Создали нулевой вектор: " + vector1);

        Vector vector2 = new Vector(-3.5, 0, 2.5, 3.9);
        System.out.println("Создали 2-й вектор и заполнили компоненты из массива: " + vector2);

        Vector vector3 = new Vector(10, 0, -1.1, 3.2, -8.5, 2.1);
        System.out.println("Создали 3-й вектор с указанием размерности и заполнили его элементами из массива: " + vector3);

        Vector copyVector = new Vector(vector3);
        System.out.println("Скопировали vector3: " + copyVector);
        System.out.println("Количество компонент в vector3: " + copyVector.getSize());

        vector2.addition(vector3);
        System.out.println("Результат сложения vector2 и vector3: " + vector2);

        copyVector.subtraction(vector3);
        System.out.println("Результат вычитания vector3 из copyVector: " + copyVector);

        vector3.multiplyByScalar(2);
        System.out.println("Результат умножения vector3 на скаляр: " + vector3);

        Vector vector4 = new Vector(-5.3, -3.2, 0.7, 3.6, 5.5);
        System.out.println("Создали 4-й вектор и заполнили компоненты из массива: " + vector4);
        System.out.println("Длина vector4: " + vector4.getLength());
        System.out.println("Значение компоненты по индексу 2 в vector4: " + vector4.getComponent(2));
        vector4.setComponent(2, 0);
        System.out.println("Установим новое значение компоненты по индексу 2 в vector4: " + vector4);

        vector4.reverse();
        System.out.println("Развернули vector4: " + vector4);

        Vector vector5 = new Vector(1.1, 2, 3, 4, 5);
        Vector vector6 = new Vector(-1.1, -2, -3, -4, -5, -6);
        System.out.println("Сумма vector5 и vector6: " + Vector.getSum(vector5, vector6));
        System.out.println("Разница vector5 и vector6: " + Vector.getDiff(vector5, vector6));
        System.out.println("Скалярное произведение vector5 и vector6: " + Vector.getScalarMultiplication(vector5, vector6));
    }
}