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
            return new Range(Math.max(from, range.from), Math.min(to, range.to));
        }
        return null;
    }

    public Range[] getUnion(Range range) {
        if ((range.to >= from && range.to <= to) || (range.from >= from && range.from <= to) ||
                (from >= range.from && from <= range.to) || (to >= range.from && to <= range.to)) {
            return new Range[]{new Range(Math.min(from, range.from), Math.max(to, range.to))};
        } else {
            return new Range[]{new Range(from, to), new Range(range.from, range.to)};
        }
    }

    public Range[] getDifference(Range range) {
        if (!this.isIntersection(range)) {
            return new Range[]{new Range(from, to)};
        } else {
            if (from >= range.from && to <= range.to) {
                return new Range[0];
            } else if (from < range.from && to > range.to) {
                return new Range[]{new Range(from, range.from), new Range(range.to, to)};
            } else if (from < range.from && to <= range.to) {
                return new Range[]{new Range(from, range.from)};
            } else {
                return new Range[]{new Range(range.to, to)};
            }

        }
    }

    private boolean isIntersection(Range range) {
        return ((range.to >= from && range.to <= to) || (range.from >= from && range.from <= to) ||
                (from >= range.from && from <= range.to) || (to >= range.from && to <= range.to)) &&
                (!(from == range.to) && !(to == range.from));
    }

    public static void print(Range range) {
        if (range == null) {
            System.out.println("range = null");
        } else {
            System.out.println(range.from + " - " + range.to);
        }
    }

    @Override
    public String toString() {
        return from + ", " + to;
    }
}