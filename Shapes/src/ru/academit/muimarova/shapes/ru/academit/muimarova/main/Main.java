package ru.academit.muimarova.shapes.ru.academit.muimarova.main;

import ru.academit.muimarova.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Square square = new Square(4.5);
        System.out.println(square.getArea() + "; " + square.getPerimeter());

        Square square1 = new Square(6.4);
        System.out.println(square1.getArea() + "; " + square1.getPerimeter());

        Triangle triangle = new Triangle(3,4,5,6,2,2);
        System.out.println(triangle.getArea() + "; " + triangle.getPerimeter());
        Triangle triangle1 = new Triangle(3,4,5,6,9,9);
        System.out.println(triangle1.getArea() + "; " + triangle1.getPerimeter());

        Rectangle rectangle = new Rectangle(3.7,6);
        System.out.println(rectangle.getArea() + "; " + rectangle.getPerimeter());
        Rectangle rectangle1 = new Rectangle(7.5,9);
        System.out.println(rectangle1.getArea() + "; " + rectangle1.getPerimeter());

        Circle circle = new Circle(9.8);
        System.out.println(circle.getArea() + "; " + circle.getPerimeter());
        Circle circle1 = new Circle(8.6);
        System.out.println(circle1.getArea() + "; " + circle1.getPerimeter());

        Shape shape = getMaxArea(square, square1, triangle, triangle1, rectangle, rectangle1, circle, circle1);
        System.out.println(shape.toString());

        Shape shape1 = getMaxPerimeter(square, square1, triangle, triangle1, rectangle, rectangle1, circle, circle1);
        System.out.println(shape1.toString());
    }

    public static Shape getMaxArea(Shape ... shapes){
        AreaComparator myAreaComparator = new AreaComparator();
        Arrays.sort(shapes, myAreaComparator);
        return shapes[0];
    }

    public static Shape getMaxPerimeter(Shape ... shapes){
        PerimeterComparator myPerimeterComparator = new PerimeterComparator();
        Arrays.sort(shapes, myPerimeterComparator);
        return shapes[1];
    }
}
