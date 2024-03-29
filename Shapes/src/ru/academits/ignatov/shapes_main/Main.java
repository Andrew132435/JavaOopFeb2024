package ru.academits.ignatov.shapes_main;

import ru.academits.ignatov.shapes.comparators.ShapeAreaComparator;
import ru.academits.ignatov.shapes.comparators.ShapePerimeterComparator;
import ru.academits.ignatov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(10),
                new Rectangle(16, 9),
                new Square(7),
                new Triangle(0, 0, -4, 5, 0, -3),
                new Circle(3),
                new Rectangle(4, 3),
                new Square(1),
                new Triangle(3, 2, 10, 10, 13, -1)
        };

        Shape shapeWithMaxArea = getShapeWithMaxArea(shapes);
        System.out.println("Фигура с максимальной площадью: " + shapeWithMaxArea);

        Shape shapeWithSecondPerimeter = getShapeWithSecondPerimeter(shapes);
        System.out.println("Фигура со 2-ым периметром по величине: " + shapeWithSecondPerimeter);
    }

    private static Shape getShapeWithMaxArea(Shape... shapes) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив фигур пустой");
        }

        Arrays.sort(shapes, new ShapeAreaComparator());
        return shapes[shapes.length - 1];
    }

    private static Shape getShapeWithSecondPerimeter(Shape... shapes) {
        if (shapes.length == 0) {
            throw new IllegalArgumentException("Массив фигур пустой");
        }

        if (shapes.length == 1) {
            throw new IllegalArgumentException("В массиве одна фигура");
        }

        Arrays.sort(shapes, new ShapePerimeterComparator());
        return shapes[shapes.length - 2];
    }
}