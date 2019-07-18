package ru.academit.muimarova.shapes.ru.academit.muimarova.main;

import ru.academit.muimarova.shapes.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o2.perimeterCalculator(), o1.perimeterCalculator());
    }
}
