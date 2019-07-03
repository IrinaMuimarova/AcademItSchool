package ru.academit.muimarova;

public class Range {
    private double from;
    private double to;

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

    public Range(double from, double to) {
        if (from > to) {
            this.from = to;
            this.to = from;
        } else {
            this.from = from;
            this.to = to;
        }
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getIntervalIntersection(Range range) {
        if (this.from <= range.from) {
            if (this.to >= range.from) {
                return new Range(range.from, this.to);
            }
        } else if (range.to >= this.from) {
            return new Range(this.from, range.to);
        }
        return null;
    }

    public Range[] getUnionInterval(Range range){return null;}
}
