package ru.muimarova;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.itschool.muimarova.vector.Vector;

import static org.testng.Assert.assertThrows;

public class MatrixTest {

    @Test
    void testConstructorNM() {
        Matrix matrix = new Matrix(2, 2);
        Assert.assertEquals(matrix.toString(), "{{0.0, 0.0}, {0.0, 0.0}}");
    }

    @Test
    void testException() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(-1, 1));
    }

    @Test
    void testException1() {
        assertThrows(IllegalArgumentException.class, () -> new Matrix(1, -1));
    }

    @Test
    void testConstructorCopy() {
        Matrix matrix = new Matrix(new double[][]{{1, 2}, {2, 3}});
        Matrix matrix1 = new Matrix(matrix);
        Assert.assertEquals(matrix1.toString(), "{{1.0, 2.0}, {2.0, 3.0}}");
    }

    @Test
    void testConstructorDoubleArray() {
        Matrix matrix = new Matrix(new double[][]{{1, 2}, {2, 3}});
        Assert.assertEquals(matrix.toString(), "{{1.0, 2.0}, {2.0, 3.0}}");
    }

    @Test
    void testConstructorDoubleArrayWith0() {
        Matrix matrix = new Matrix(new double[][]{{1, 2, 7}, {2, 3}});
        Assert.assertEquals(matrix.toString(), "{{1.0, 2.0, 7.0}, {2.0, 3.0, 0.0}}");
    }

    @Test
    void testConstructorArrayVectorsWith0() {
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3})});
        Assert.assertEquals(matrix.toString(), "{{1.0, 2.0, 7.0}, {2.0, 3.0, 0.0}}");
    }

    @Test
    void testGetSizeMatrix(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}), new Vector(new double[]{2, 3, 6})});
        Assert.assertEquals((matrix.getSizeRows()), 3);
        Assert.assertEquals((matrix.getSizeColumn()), 3);
    }

    @Test
    void testGetVector(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}), new Vector(new double[]{2, 3, 6})});
        Assert.assertEquals(matrix.getRow(1).toString(), "{2.0, 3.0, 5.0}");
    }

    @Test
    void testSetVector(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}), new Vector(new double[]{2, 3, 6})});
        matrix.setRow(1, new Vector(new double[]{1, 2, 3}));
        Assert.assertEquals(matrix.getRow(1).toString(), "{1.0, 2.0, 3.0}");
    }

    @Test
    void testGetColumn(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        Assert.assertEquals(matrix.getColumn(2).toString(), "{7.0, 5.0, 0.0}");
    }

    @Test
    void testTranspose(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2}), new Vector(new double[]{2, 3}),
                new Vector(new double[]{2, 3})});
        matrix.transpose();
        Assert.assertEquals(matrix.toString(), "{{1.0, 2.0, 2.0}, {2.0, 3.0, 3.0}}");
    }

    @Test
    void testScalar(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2}), new Vector(new double[]{2, 3}),
                new Vector(new double[]{2, 3})});
        matrix.multiplicationByScalar(3);
        Assert.assertEquals(matrix.toString(), "{{3.0, 6.0}, {6.0, 9.0}, {6.0, 9.0}}");
    }

    @Test
    void testDeterminant(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{5, 5, 0, 3}), new Vector(new double[]{4, 7, 7, 4}), new Vector(new double[]{3, 3, 5, 5}), new Vector(new double[]{9, 8, 7, 5})});
        Assert.assertEquals(matrix.calculateDeterminant(), -438);
    }

    @Test
    void testAdd(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        matrix.add(matrix1);
        Assert.assertEquals(matrix.toString(), "{{2.0, 4.0, 14.0}, {4.0, 6.0, 10.0}, {4.0, 6.0, 0.0}}");
    }

    @Test
    void testAddStatic(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        Matrix matrix2 = Matrix.add(matrix, matrix1);
        Assert.assertEquals(matrix2.toString(), "{{2.0, 4.0, 14.0}, {4.0, 6.0, 10.0}, {4.0, 6.0, 0.0}}");
    }

    @Test
    void testSubtraction(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{3, 8, 7}), new Vector(new double[]{6, 4, 2}),
                new Vector(new double[]{-2, -3})});
        matrix.subtraction(matrix1);
        Assert.assertEquals(matrix.toString(), "{{-2.0, -6.0, 0.0}, {-4.0, -1.0, 3.0}, {4.0, 6.0, 0.0}}");
    }

    @Test
    void tesSubtractionStatic(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2, 7}), new Vector(new double[]{2, 3, 5}),
                new Vector(new double[]{2, 3})});
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{3, 8, 7}), new Vector(new double[]{6, 4, 2}),
                new Vector(new double[]{-2, -3})});
        Matrix matrix2 = Matrix.subtraction(matrix, matrix1);
        Assert.assertEquals(matrix2.toString(), "{{-2.0, -6.0, 0.0}, {-4.0, -1.0, 3.0}, {4.0, 6.0, 0.0}}");
    }

    @Test
    void testMulti(){
        Matrix matrix = new Matrix(new Vector[]{new Vector(new double[]{1, 2}), new Vector(new double[]{-3, 4}), new Vector(new double[]{-3, 4})});
        Matrix matrix1 = new Matrix(new Vector[]{new Vector(new double[]{-2, 4, 7}), new Vector(new double[]{3, 1, 6})});
        Matrix matrix2 = Matrix.multiplication(matrix, matrix1);
        Assert.assertEquals(matrix2.toString(), "{{4.0, 6.0, 19.0}, {18.0, -8.0, 3.0}, {18.0, -8.0, 3.0}}");
    }

    @Test
    void testMultiVector(){
        Matrix matrix =  new Matrix(new Vector[]{new Vector(new double[]{2, 4, 0}), new Vector(new double[]{-2, 1, 3}),
                new Vector(new double[]{-1, 0, 1})});
        matrix.multiplicationOnVector(new Vector(new double[]{1, 2, -1}));
        Assert.assertEquals(matrix.toString(), "{{2.0, 8.0, -0.0}, {-2.0, 2.0, -3.0}, {-1.0, 0.0, -1.0}}");
    }
}