package ru.academit.muimarova.shapes.ru.academit.muimarova.main;

import ru.academit.muimarova.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(4.5);
        System.out.println(square.areaCalculator() + "; " + square.perimeterCalculator());

        Square square1 = new Square(6.4);
        System.out.println(square1.areaCalculator() + "; " + square1.perimeterCalculator());

        Triangle triangle = new Triangle(3,4,5,6,2,2);
        System.out.println(triangle.areaCalculator() + "; " + triangle.perimeterCalculator());
        Triangle triangle1 = new Triangle(3,4,5,6,9,9);
        System.out.println(triangle1.areaCalculator() + "; " + triangle1.perimeterCalculator());

        Rectangle rectangle = new Rectangle(3.7,6);
        System.out.println(rectangle.areaCalculator() + "; " + rectangle.perimeterCalculator());
        Rectangle rectangle1 = new Rectangle(7.5,9);
        System.out.println(rectangle1.areaCalculator() + "; " + rectangle1.perimeterCalculator());

        Circle circle = new Circle(9.8);
        System.out.println(circle.areaCalculator() + "; " + circle.perimeterCalculator());
        Circle circle1 = new Circle(8.6);
        System.out.println(circle1.areaCalculator() + "; " + circle1.perimeterCalculator());

        Shape shape = getMaxArea(square, square1, triangle, triangle1, rectangle, rectangle1, circle, circle1);
        System.out.println(shape.toString());

        Shape shape1 = getMaxPerimeter(square, square1, triangle, triangle1, rectangle, rectangle1, circle, circle1);
        System.out.println(shape1.toString());
    }

    private static Shape getMaxArea(Shape ... shapes){
        AreaComparator myAreaComparator = new AreaComparator();
        Arrays.sort(shapes, myAreaComparator);
        return shapes[0];
    }

    private static Shape getMaxPerimeter(Shape ... shapes){
        PerimeterComparator myPerimeterComparator = new PerimeterComparator();
        Arrays.sort(shapes, myPerimeterComparator);
        return shapes[1];
    }
}
