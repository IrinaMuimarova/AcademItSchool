package ru.academit.muimarova;

import static ru.academit.muimarova.Range.print;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(7, 10);
        Range range1 = new Range(6, 8);

        Range range2 = range.getIntervalIntersection(range1);

        System.out.println("getIntervalIntersection");

        print(range2);

        Range[] ranges;
        ranges = range.getUnionInterval(range1);

        System.out.println("getUnionInterval");

        print(ranges[0]);
        print(ranges[1]);

        ranges = range.getIntervalDifference(range1);

        System.out.println("getIntervalDifference");

        print(ranges[0]);
        print(ranges[1]);

    }
}
