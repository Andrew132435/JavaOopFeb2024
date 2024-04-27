package ru.academits.ignatov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int componentsCount) {
        if (componentsCount <= 0) {
            throw new IllegalArgumentException("Количество компонент должно быть больше 0, componentsCount = " + componentsCount);
        }

        components = new double[componentsCount];
    }

    public Vector(Vector vector) {
        components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(double... components) {
        if (components.length == 0) {
            throw new IllegalArgumentException("Количество компонент = 0");
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int componentsCount, double... components) {
        if (componentsCount <= 0) {
            throw new IllegalArgumentException("Количество компонент должно быть больше 0, componentsCount = " + componentsCount);
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public int getComponentsCount() {
        return components.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('{');

        for (double component : components) {
            stringBuilder.append(component).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append('}');

        return stringBuilder.toString();
    }

    public void getAddition(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void getSubtraction(Vector vector) {
        if (components.length < vector.components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByScalar(double scalar) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= scalar;
        }
    }

    public void reverse() {
        multiplyByScalar(-1);
    }

    public double getLength() {
        double sum = 0;

        for (double c : components) {
            sum += c * c;
        }

        return Math.sqrt(sum);
    }

    public double getComponent(int index) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " находится за пределами диапазона {0; "
                    + (components.length - 1) + "}");
        }

        return components[index];
    }

    public void setComponent(int index, double component) {
        if (index < 0 || index >= components.length) {
            throw new IndexOutOfBoundsException("Индекс " + index + " находится за пределами диапазона {0; "
                    + (components.length - 1) + "}");
        }

        components[index] = component;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        return Arrays.equals(components, vector.components);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.getAddition(vector2);

        return resultVector;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(vector1);

        resultVector.getSubtraction(vector2);

        return resultVector;
    }

    public static double getScalarProduct(Vector vector1, Vector vector2) {
        int minComponentsCount = Math.min(vector1.components.length, vector2.components.length);
        double result = 0;

        for (int i = 0; i < minComponentsCount; i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}