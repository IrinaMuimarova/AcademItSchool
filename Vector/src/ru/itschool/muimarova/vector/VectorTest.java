package ru.itschool.muimarova.vector;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static ru.itschool.muimarova.vector.Vector.*;

class VectorTest {

    @Test
    void createVectorWithN() {
        Vector vector = new Vector(3);
        Assert.assertEquals(vector.toString(), "{0.0, 0.0, 0.0}");
    }

    @Test
    void createVectorWithArray() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        Assert.assertEquals(vector.toString(), "{1.0, 2.0, 3.0, 4.0}");
    }

    @Test
    void createVectorCopy() {
        Vector vector = new Vector(3);
        Vector vector1 = new Vector(vector);
        Assert.assertEquals(vector1.toString(), "{0.0, 0.0, 0.0}");
    }

    @Test
    void createVectorWithArrayAndN() {
        Vector vector = new Vector(6, new double[]{1, 2, 3, 4});
        Assert.assertEquals(vector.toString(), "{1.0, 2.0, 3.0, 4.0, 0.0, 0.0}");
    }

    @Test
    void testGetSize() {
        Vector vector = new Vector(8);
        Assert.assertEquals(vector.getSize(), 8);
    }

    @Test
    void testAddVector(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3,4,5,6});
        Assert.assertEquals(vector.add(vector1).toString(), "{2.0, 4.0, 6.0, 8.0, 5.0, 6.0}");
    }

    @Test
    void testAddVector1(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3});
        Assert.assertEquals(vector.add(vector1).toString(), "{2.0, 4.0, 6.0, 4.0}");
    }

    @Test
    void testAddVectorStatic(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3,4,5,6});
        Assert.assertEquals(add(vector, vector1).toString(), "{2.0, 4.0, 6.0, 8.0, 5.0, 6.0}");
    }

    @Test
    void testAddVectorStatic1(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3});
        Assert.assertEquals(add(vector,vector1).toString(), "{2.0, 4.0, 6.0, 4.0}");
    }

    @Test
    void testSubtractionVector(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3,4,5,6});
        Assert.assertEquals(vector.subtraction(vector1).toString(), "{0.0, 0.0, 0.0, 0.0, -5.0, -6.0}");
    }

    @Test
    void testSubtractionVector1(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3});
        Assert.assertEquals(vector.subtraction(vector1).toString(), "{0.0, 0.0, 0.0, 4.0}");
    }

    @Test
    void testSubtractionVectorStatic(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3,4,5,6});
        Assert.assertEquals(subtraction(vector, vector1).toString(), "{0.0, 0.0, 0.0, 0.0, -5.0, -6.0}");
    }

    @Test
    void testSubtractionVectorStatic1(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3});
        Assert.assertEquals(subtraction(vector,vector1).toString(), "{0.0, 0.0, 0.0, 4.0}");
    }

    @Test
    void testscalarMultiplicationStatic(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3,4,5,6});
        Assert.assertEquals(scalarMultiplication(vector, vector1).toString(), "{1.0, 4.0, 9.0, 16.0, 0.0, 0.0}");
    }

    @Test
    void testscalarMultiplicationStatic1(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        Vector vector1 = new Vector(new double[]{1,2,3});
        Assert.assertEquals(scalarMultiplication(vector,vector1).toString(), "{1.0, 4.0, 9.0, 0.0}");
    }

    @Test
    void testScalarMulti(){
        Vector vector = new Vector(new double[]{1,2,3,4});
        vector.scalarMultiplication(2);
        Assert.assertEquals(vector.toString(), "{2.0, 4.0, 6.0, 8.0}");
    }

    @Test
    void testReversal() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        vector.reversal();
        Assert.assertEquals(vector.toString(), "{-1.0, -2.0, -3.0, -4.0}");
    }

    @Test
    void testSetComponent() {
        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        vector.setComponent(2, 4.6);
        Assert.assertEquals(vector.toString(), "{1.0, 2.0, 4.6, 4.0}");
    }

    @Test
    void testEquals(){
        Vector vector = new Vector(new double[]{1, 2, 3, 4});
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Assert.assertTrue(vector.equals(vector1));
    }

    @Test
    void testEquals1(){
        Vector vector = new Vector(new double[]{1, 2, 3, 4, 5});
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Assert.assertFalse(vector.equals(vector1));
    }

    @Test
    void testEquals2(){
        Vector vector = new Vector(new double[]{1, 5, 3, 4});
        Vector vector1 = new Vector(new double[]{1, 2, 3, 4});
        Assert.assertFalse(vector.equals(vector1));
    }
}