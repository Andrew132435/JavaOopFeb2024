package ru.academits.ignatov.matrix;

import ru.academits.ignatov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException("Количество строк должно быть больше нуля. Количество строк: " + rowsCount);
        }

        if (columnsCount <= 0) {
            throw new IllegalArgumentException("Количество столбцов должно быть больше нуля. Количество столбцов: " + columnsCount);
        }

        rows = new Vector[rowsCount];

        for (int i = 0; i < rowsCount; i++) {
            rows[i] = new Vector(columnsCount);
        }
    }

    public Matrix(Matrix matrix) {
        if (matrix == null) {
            throw new NullPointerException("Переданная матрица должна быть не null");
        }

        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array == null) {
            throw new NullPointerException("Переданный массив должен быть не null");
        }

        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк двумерного массива должно быть больше нуля");
        }

        int columnsCount = array[0].length;

        for (int i = 1; i < array.length; i++) {
            if (columnsCount < array[i].length) {
                columnsCount = array[i].length;
            }
        }

        if (columnsCount == 0) {
            throw new IllegalArgumentException("Количество столбцов в матрице равно нулю");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(columnsCount, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors == null) {
            throw new NullPointerException("Переданный массив векторов должен быть не null");
        }

        if (vectors.length == 0) {
            throw new IllegalArgumentException("Длина массива векторов должна быть больше нуля");
        }

        int maxVectorDimension = vectors[0].getComponentsCount();

        for (int i = 1; i < vectors.length; i++) {
            if (maxVectorDimension < vectors[i].getComponentsCount()) {
                maxVectorDimension = vectors[i].getComponentsCount();
            }
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxVectorDimension);
            rows[i].add(vectors[i]);
        }
    }

    public int getColumnsCount() {
        return rows[0].getComponentsCount();
    }

    public int getRowsCount() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс строки должен находиться в диапазоне [0; " + (rows.length - 1)
                    + "]. Индекс строки: " + index);
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор должен быть не null");
        }

        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException("Индекс строки должен находиться в диапазоне [0; " + (rows.length - 1)
                    + "]. Индекс строки: " + index);
        }

        if (getColumnsCount() != vector.getComponentsCount()) {
            throw new IllegalArgumentException("Количество строк в матрице и размерность вектора отличаются. " +
                    "Количество столбцов в матрице: " + getColumnsCount()
                    + ". Размерность вектора: " + vector.getComponentsCount());
        }

        rows[index] = new Vector(vector);
    }

    public Vector getColumn(int index) {
        if (index < 0 || index >= getColumnsCount()) {
            throw new IndexOutOfBoundsException("Индекс столбца должен находиться в диапазоне [0; "
                    + (getColumnsCount() - 1) + "]. Индекс столбца: " + index);
        }

        double[] columnComponentsArray = new double[rows.length];

        for (int i = 0; i < rows.length; i++) {
            columnComponentsArray[i] = rows[i].getComponent(index);
        }

        return new Vector(columnComponentsArray);
    }

    public void transpose() {
        Vector[] newRows = new Vector[getColumnsCount()];

        for (int i = 0; i < newRows.length; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiply(double scalar) {
        for (Vector row : rows) {
            row.multiplyByScalar(scalar);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsCount()) {
            throw new IllegalStateException("Количество столбцов и строк в матрице должно быть одинаковым. " +
                    "Количество строк: " + rows.length + ". Количество столбцов: " + getColumnsCount());
        }

        if (rows.length == 1) {
            return rows[0].getComponent(0);
        }

        if (rows.length == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1)
                    - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double determinant = 0;
        int sign = 1;

        for (int determinantColumn = 0; determinantColumn < getColumnsCount(); determinantColumn++) {
            double[][] minor = new double[rows.length - 1][rows.length - 1];
            int minorRow = 0;

            for (int row = 1; row < rows.length; ++row) {
                int minorColumn = 0;

                for (int column = 0; column < getColumnsCount(); column++) {
                    if (column != determinantColumn) {
                        minor[minorRow][minorColumn] = rows[row].getComponent(column);
                        minorColumn++;
                    }
                }

                minorRow++;
            }

            determinant += sign * rows[0].getComponent(determinantColumn) * new Matrix(minor).getDeterminant();
            sign *= -1;
        }

        return determinant;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");

        for (Vector row : rows) {
            stringBuilder.append(row).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append('}');

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Matrix matrix = (Matrix) obj;

        return Arrays.equals(rows, matrix.rows);
    }

    @Override
    public int hashCode() {
        final int prime = 37;

        return prime + Arrays.hashCode(rows);
    }

    public Vector multiply(Vector vector) {
        if (vector == null) {
            throw new NullPointerException("Вектор должен быть не null");
        }

        if (getColumnsCount() != vector.getComponentsCount()) {
            throw new IllegalArgumentException("Количество столбцов в матрице и количество компонент вектора должны совпадать. "
                    + "Количество столбцов в матрице: " + getColumnsCount()
                    + ". Количество компонент вектора: " + vector.getComponentsCount());
        }

        Vector resultVector = new Vector(rows.length);

        for (int i = 0; i < rows.length; i++) {
            resultVector.setComponent(i, Vector.getScalarProduct(rows[i], vector));
        }

        return resultVector;
    }

    private static void checkMatricesSizesEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("1-я матрица не должна быть null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("2-я матрица не должна быть null");
        }

        if (matrix1.rows.length != matrix2.rows.length || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Обе матрицы должны быть одинакового размера" +
                    ". Размер 1-й матрицы: " + matrix1.rows.length + "x" + matrix1.getColumnsCount() +
                    ". Размер 2-й матрицы: " + matrix2.rows.length + "x" + matrix2.getColumnsCount());
        }
    }

    public void add(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkMatricesSizesEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkMatricesSizesEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1 == null) {
            throw new NullPointerException("1-я матрица не должна быть null");
        }

        if (matrix2 == null) {
            throw new NullPointerException("2-я матрица не должна быть null");
        }

        if (matrix1.getColumnsCount() != matrix2.rows.length) {
            throw new IllegalArgumentException("Количество столбцов в 1-й матрице и количество строк во 2-й матрице должно совпадать"
                    + ". Количество столбцов в 1-й матрице: " + matrix1.getColumnsCount()
                    + ". Количество строк во 2-й матрице: " + matrix2.rows.length);
        }

        double[][] array = new double[matrix1.rows.length][matrix2.getColumnsCount()];

        for (int i = 0; i < matrix1.rows.length; i++) {
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                array[i][j] = Vector.getScalarProduct(matrix1.rows[i], matrix2.getColumn(j));
            }
        }

        return new Matrix(array);
    }
}