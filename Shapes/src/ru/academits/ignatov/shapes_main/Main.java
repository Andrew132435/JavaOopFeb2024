package ru.academits.ignatov.shapes_main;

import ru.academits.ignatov.shapes.*;

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
                new Triangle(3, 2, 10, 10, 13, -1)};
    }
}
