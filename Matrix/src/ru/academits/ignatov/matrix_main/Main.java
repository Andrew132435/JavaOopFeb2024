package ru.academits.ignatov.matrix_main;

import ru.academits.ignatov.matrix.Matrix;
import ru.academits.ignatov.vector.Vector;

public class Main {
    public static void print(Matrix matrix) {
        for (int i = 0; i < matrix.getRowsCount(); i++) {
            System.out.println(matrix.getRow(i));
        }
    }

    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(5, 5);
        System.out.println("Матрица нулей размером 5х5:");
        print(matrix1);

        System.out.println();

        Matrix matrix2 = new Matrix(matrix1);
        System.out.println("Копия matrix1:");
        print(matrix2);

        System.out.println();

        double[][] array = {{2.2}, {1.2, 5.0, -2.8}, {-3.1, 0.2}};

        Matrix matrix3 = new Matrix(array);
        System.out.println("Матрица из двумерного массива:");
        print(matrix3);

        System.out.println();

        double[] array1 = {0.1, 2.2};
        double[] array2 = {-5.6};
        double[] array3 = {6.6, 7.7, 8.8, 9.9};

        Vector vector1 = new Vector(array1);
        Vector vector2 = new Vector(array2);
        Vector vector3 = new Vector(array3);

        Vector[] vectors = {vector1, vector2, vector3};

        Matrix matrix4 = new Matrix(vectors);
        System.out.println("Матрица из массива векторов-строк");
        print(matrix4);

        System.out.println();

        System.out.println("Размер матрицы matrix4: " + matrix4.getColumnsCount() + "x" + matrix4.getRowsCount());

        System.out.println("Выведем 2-ю строку из matrix4: " + matrix4.getRow(1));
        matrix4.setRow(1, vector3);
        System.out.println();

        System.out.println("Изменили 2-ю строку в матрице matrix4:");
        print(matrix4);
        System.out.println();

        matrix4.transpose();
        System.out.println("Транспонируем matrix4:");
        print(matrix4);
        System.out.println();

        matrix4.multiply(2.5);
        System.out.println("Умножим matrix4 на скаляр:");
        print(matrix4);
        System.out.println();

        System.out.println("Вычислим определитель матрицы matrix3: " + matrix3.getDeterminant());
        System.out.println();

        Vector vector4 = new Vector(3.3, 1.1, 1.1);

        System.out.println("Умножим matrix4 на вектор " + vector4 + ": " + matrix4.multiply(vector4));
        System.out.println();

        double[][] array4 = {{2.2}, {1.2, 5.0, -2.8}, {-3.1, 0.2}};
        double[][] array5 = {{2.2, 2.2}, {0.1, 2.3, 7.9}, {9.0}};

        Matrix matrix5 = new Matrix(array4);
        Matrix matrix6 = new Matrix(array5);
        System.out.println("Сложение матриц matrix5 и matrix6 статическим методом:");
        print(Matrix.getSum(matrix5, matrix6));
        System.out.println();

        System.out.println("Разница матриц matrix5 и matrix6 статическим методом:");
        print(Matrix.getDifference(matrix5, matrix6));
        System.out.println();

        System.out.println("Умножение матриц matrix5 и matrix6 статическим методом:");
        print(Matrix.getProduct(matrix5, matrix6));
    }
}