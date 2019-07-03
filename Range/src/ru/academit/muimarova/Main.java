package ru.academit.muimarova;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(1, 5);
        Range range1 = new Range(2, 2);

        Range range2 = range.getIntervalIntersection(range1);

        System.out.println(range2.getFrom() + " " + range2.getTo());
    }
}
