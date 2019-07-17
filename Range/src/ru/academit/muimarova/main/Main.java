package ru.academit.muimarova.main;

import ru.academit.muimarova.Range;

import static ru.academit.muimarova.Range.print;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(1, 7);
        Range range1 = new Range(5, 7);

        Range range2 = range.getIntersection(range1);

        System.out.println("getIntersection");

        print(range2);

        Range[] ranges;
        ranges = range.getUnion(range1);

        System.out.println("getInterval");

        print(ranges[0]);
        try {
            print(ranges[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Элемента массива не существует." + e);
        }
        ranges = range.getDifference(range1);

        System.out.println("getDifference");
        try {
            print(ranges[0]);
            print(ranges[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Элемента массива не существует." + e);
        }
    }
}
