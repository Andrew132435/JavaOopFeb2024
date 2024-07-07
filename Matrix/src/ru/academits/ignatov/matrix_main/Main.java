package ru.academits.ignatov.matrix_main;

import ru.academits.ignatov.matrix.Matrix;

public class Main {
    public static void main(String[] args) {
        Matrix matrix = new Matrix(5, 5);
        System.out.println("Матрица с нулями:" + System.lineSeparator() + matrix);

        System.out.println();

        Matrix copyMatrix = new Matrix(matrix);
        System.out.println("Копия предыдущей матрицы:" + System.lineSeparator() + copyMatrix);

        System.out.println();

        double[][] array = {{-3.2, 0.852, 3.65},
                {1, 1, 1, 1, 1},
                {-4, 2.88888, -1, 0}};

        Matrix matrix2 = new Matrix(array);
        System.out.println("Матрица из двумерного массива:" + System.lineSeparator() + matrix2);
    }
}
