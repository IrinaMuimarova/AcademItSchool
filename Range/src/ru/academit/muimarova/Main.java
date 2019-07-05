package ru.academit.muimarova;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(7, 10);
        Range range1 = new Range(6, 8);

        Range range2 = range.getIntervalIntersection(range1);

        System.out.println("getIntervalIntersection");

        if (range2 != null) {
            range2.print();
        } else {
            System.out.println("range2 = null");
        }

        Range[] ranges;
        ranges = range.getUnionInterval(range1);

        System.out.println("getUnionInterval");

        ranges[0].print();
        if (ranges[1] != null) {
            ranges[1].print();
        } else {
            System.out.println("ranges[1] = null");
        }

        ranges = range.getIntervalDifference(range1);

        System.out.println("getIntervalDifference");

        if (ranges[0] != null) {
            ranges[0].print();
        } else {
            System.out.println("ranges[0] = null");
        }

        if (ranges[1] != null) {
            ranges[1].print();
        } else {
            System.out.println("ranges[1] = null");
        }
    }
}
