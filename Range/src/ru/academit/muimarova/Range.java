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
        if (this.isIntersection(range)) {
            return new Range[]{new Range(Math.min(this.from, range.from), Math.max(this.to, range.to))};
        } else {
            return new Range[]{new Range(this.from, this.to), new Range(range.from, range.to)};
        }
    }

    public Range[] getDifference(Range range) {
        if (!this.isIntersection(range)) {
            return new Range[]{new Range(this.from, this.to)};
        } else {
            if (range.from <= this.from && range.to >= this.to) {
                return new Range[0];
            } else if (range.from > this.from && range.to < this.to) {
                return new Range[]{new Range(this.from, range.from), new Range(range.to, this.to)};
            } else {
                return new Range[]{new Range(Math.min(range.to, this.to), Math.max(range.from, this.from))};
            }
        }
    }

    private boolean isIntersection(Range range) {
        return (this.isInside(range.to) || this.isInside(range.from) || range.isInside(this.from) || range.isInside(this.to))
                && (!((this.from == range.to || this.to == range.from) && !(this.from == range.to && this.to == range.from)));

    }

    public static void print(Range range) {
        if (range == null) {
            System.out.println("range = null");
        } else {
            System.out.println(range.from + " - " + range.to);
        }
    }
}