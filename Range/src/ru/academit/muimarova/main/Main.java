package ru.academit.muimarova.main;

import ru.academit.muimarova.Range;

import static ru.academit.muimarova.Range.print;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(7, 10);
        Range range1 = new Range(6, 8);

        Range range2 = range.getIntersection(range1);

        System.out.println("getIntersection");

        print(range2);

        Range[] ranges;
        ranges = range.getUnion(range1);

        System.out.println("getInterval");

        print(ranges[0]);
        print(ranges[1]);

        ranges = range.getDifference(range1);

        System.out.println("getDifference");

        print(ranges[0]);
        print(ranges[1]);

    }
}
