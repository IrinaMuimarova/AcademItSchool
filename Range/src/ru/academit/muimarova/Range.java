package ru.academit.muimarova;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        if (from > to) {
            this.from = to;
            this.to = from;
        } else {
            this.from = from;
            this.to = to;
        }
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    private boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntersection(Range range) {
        if (this.isIntersection(range)) {
            return new Range(Math.max(this.from, range.from), Math.min(this.to, range.to));
        }
        return null;
    }

    public Range[] getUnion(Range range) {
        Range[] ranges;
        if (this.isIntersection(range)) {
            ranges = new Range[1];
            ranges[0] = new Range(Math.min(this.from, range.from), Math.max(this.to, range.to));
        } else {
            ranges = new Range[2];
            ranges[0] = new Range(this.from, this.to);
            ranges[1] = new Range(range.from, range.to);
        }
        return ranges;
    }

    public Range[] getDifference(Range range) {
        Range[] ranges;
        if (!this.isIntersection(range)) {
            ranges = new Range[1];
            ranges[0] = new Range(this.from, this.to);
        } else {
            if (range.from <= this.from && range.to >= this.to) {
                ranges = new Range[0];
                return ranges;
            } else if (range.from > this.from && range.to < this.to) {
                ranges = new Range[2];
                ranges[0] = new Range(this.from, range.from);
                ranges[1] = new Range(range.to, this.to);
            } else {
                ranges = new Range[1];
                ranges[0] = new Range(Math.min(range.to, this.to), Math.max(range.from, this.from));
            }
        }
        return ranges;
    }

    private boolean isIntersection(Range range) {
        return this.isInside(range.to) || this.isInside(range.from) || range.isInside(this.from) || range.isInside(this.to);
    }

    public static void print(Range range) {
        if (range == null) {
            System.out.println("range = null");
        } else {
            System.out.println(range.from + " - " + range.to);
        }
    }
}