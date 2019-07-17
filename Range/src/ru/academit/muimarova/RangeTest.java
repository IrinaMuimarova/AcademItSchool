package ru.academit.muimarova;

import org.junit.Assert;
import org.junit.Test;



public class RangeTest {
    @Test
    public void getIntersectionExist() {
        Range range = new Range(1, 7);
        Range range1 = new Range(5, 7);
        Range expected = range.getIntersection(range1);

        Assert.assertEquals(expected.toString(), "5.0, 7.0");
    }

    @Test
    public void getIntersectionExist1() {
        Range range = new Range(1, 9);
        Range range1 = new Range(5, 7);
        Range expected = range.getIntersection(range1);

        Assert.assertEquals(expected.toString(), "5.0, 7.0");
    }

    @Test
    public void getIntersectionExist2() {
        Range range = new Range(1, 9);
        Range range1 = new Range(-5, 17);
        Range expected = range.getIntersection(range1);

        Assert.assertEquals(expected.toString(), "1.0, 9.0");
    }

    @Test
    public void getIntersectionExist3() {
        Range range = new Range(5, 9);
        Range range1 = new Range(1, 7);
        Range expected = range.getIntersection(range1);

        Assert.assertEquals(expected.toString(), "5.0, 7.0");
    }



    @Test
    public void getIntersectionNotExist() {
        Range range = new Range(1, 7);
        Range range1 = new Range(7, 17);
        Range expected = range.getIntersection(range1);

        Assert.assertNull(expected);
    }

    @Test
    public void getIntersectionNotExist1() {
        Range range = new Range(17, 77);
        Range range1 = new Range(7, 17);
        Range expected = range.getIntersection(range1);

        Assert.assertNull(expected);
    }

    @Test
    public void getIntersectionNotExist2() {
        Range range = new Range(47, 77);
        Range range1 = new Range(7, 17);
        Range expected = range.getIntersection(range1);

        Assert.assertNull(expected);
    }

    @Test
    public void getIntersectionNotExist3() {
        Range range = new Range(1, 5);
        Range range1 = new Range(7, 17);
        Range expected = range.getIntersection(range1);

        Assert.assertNull(expected);
    }

    @Test
    public void getUnion() {
        Range range = new Range(1, 7);
        Range range1 = new Range(5, 7);
        Range[] expected = range.getUnion(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 7.0");
    }

    @Test
    public void getUnion1() {
        Range range = new Range(1, 3);
        Range range1 = new Range(5, 7);
        Range[] expected = range.getUnion(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 3.0");
        Assert.assertEquals(expected[1].toString(), "5.0, 7.0");
    }

    @Test
    public void getUnion2() {
        Range range = new Range(1, 7);
        Range range1 = new Range(1, 5);
        Range[] expected = range.getUnion(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 7.0");
    }

    @Test
    public void getUnion3() {
        Range range = new Range(1, 5);
        Range range1 = new Range(5, 7);
        Range[] expected = range.getUnion(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 7.0");
    }

    @Test
    public void getUnion4() {
        Range range = new Range(5, 7);
        Range range1 = new Range(1, 5);
        Range[] expected = range.getUnion(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 7.0");
    }

    @Test
    public void getDifference() {
        Range range = new Range(1, 7);
        Range range1 = new Range(5, 7);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 5.0");
    }

    @Test
    public void getDifferenceExist1() {
        Range range = new Range(1, 9);
        Range range1 = new Range(5, 7);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 5.0");
    }


    @Test
    public void getDifferenceExist2() {
        Range range = new Range(1, 9);
        Range range1 = new Range(-5, 17);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected.length, 0);
    }

    @Test
    public void getDifferenceExist3() {
        Range range = new Range(5, 9);
        Range range1 = new Range(1, 7);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "7.0, 9.0");
    }



    @Test
    public void getDifferenceNotExist() {
        Range range = new Range(1, 7);
        Range range1 = new Range(7, 17);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 7.0");
    }

    @Test
    public void getDifferenceNotExist1() {
        Range range = new Range(17, 77);
        Range range1 = new Range(7, 17);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "17.0, 77.0");

    }

    @Test
    public void getDifferenceNotExist2() {
        Range range = new Range(47, 77);
        Range range1 = new Range(7, 17);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "47.0, 77.0");
    }

    @Test
    public void getDifferenceNotExist3() {
        Range range = new Range(1, 5);
        Range range1 = new Range(7, 17);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 5.0");
    }

    @Test
    public void getDifferenceExist4() {
        Range range = new Range(1, 22);
        Range range1 = new Range(7, 17);
        Range[] expected = range.getDifference(range1);

        Assert.assertEquals(expected[0].toString(), "1.0, 7.0");
        Assert.assertEquals(expected[1].toString(), "17.0, 22.0");
    }

}