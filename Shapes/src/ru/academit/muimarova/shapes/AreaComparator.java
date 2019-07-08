package ru.academit.muimarova.shapes;

import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return Double.compare(o2.getArea(), o1.getArea());
    }
}
