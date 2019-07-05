package ru.academit.muimarova;

public class Range {
    private double from;
    private double to;

    double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    Range(double from, double to) {
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

    private boolean isInside(double number) {
        return number >= from && number <= to;
    }

    Range getIntervalIntersection(Range range) {
        if (this.isIntersection(range)) {
            return new Range(this.from > range.from ? this.from : range.from, this.to < range.to ? this.to : range.to);
        }
        return null;
    }

    Range[] getUnionInterval(Range range) {
        Range[] ranges = new Range[2];
        if (this.isIntersection(range)) {
            ranges[0] = new Range(this.from < range.from ? this.from : range.from, this.to > range.to ? this.to : range.to);
        } else {
            ranges[0] = this;
            ranges[1] = range;
        }
        return ranges;
    }

    Range[] getIntervalDifference(Range range) {
        Range[] ranges = new Range[2];
        if (!this.isIntersection(range)) {
            ranges[0] = this;
        } else {
            if (range.from <= this.from && range.to >= this.to) {  //случай когда первый интервал равен или входит в границы второго,
                return null;                                       // так как в условии не прописан этот сценарий, возвращаю null;
            } else if (range.from > this.from && range.to < this.to) {
                ranges[0] = new Range(this.from, range.from);
                ranges[1] = new Range(range.to, this.to);
            } else {
                ranges[0] = new Range(range.to < this.to ? range.to : this.from, range.from > this.from ? range.from : this.to);
            }

        }
        return ranges;
    }

    private boolean isIntersection(Range range) {
        return this.isInside(range.to) || this.isInside(range.from) || range.isInside(this.from) || range.isInside(this.to);
    }

    void print() {
        System.out.println(from + " - " + to);
    }
}
