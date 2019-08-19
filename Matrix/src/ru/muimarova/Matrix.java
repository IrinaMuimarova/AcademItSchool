package ru.muimarova;

import ru.itschool.muimarova.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private static final String ILLEGAL_ROW_COUNTS = "Количество строк в матрице не может быть меньше или равно 0";
    private static final String ILLEGAL_COLUMN_COUNTS = "Количество стоблцов в матрице не может быть меньше или равно 0";

    private Vector[] rows;

    public Matrix(int rowCounts, int columnCounts) {
        if (rowCounts <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ROW_COUNTS);
        }
        if (columnCounts <= 0) {
            throw new IllegalArgumentException(ILLEGAL_COLUMN_COUNTS);
        }

        rows = new Vector[rowCounts];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnCounts);
        }
    }

    public Matrix(Matrix matrix) {
        this.rows = new Vector[matrix.rows.length];
        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] matrix) {
        if (matrix.length <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ROW_COUNTS);
        }

        this.rows = new Vector[matrix.length];

        int rowLength = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length > rowLength) {
                rowLength = matrix[i].length;
            }
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(Arrays.copyOf(matrix[i], rowLength));
        }
    }

    public Matrix(Vector[] matrix) {
        if (matrix.length <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ROW_COUNTS);
        }

        this.rows = new Vector[matrix.length];
        int rowLength = matrix[0].getSize();
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].getSize() > rowLength) {
                rowLength = matrix[i].getSize();
            }
        }

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(rowLength);
            this.rows[i].add(matrix[i]);
        }
    }

    public int getRowCounts() {
        return this.rows.length;
    }

    public int getColumnCounts() {
        return this.rows[0].getSize();
    }

    public Vector getRow(int indexRow) {
        if (indexRow < 0) {
            throw new IndexOutOfBoundsException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow >= this.getRowCounts()) {
            throw new IndexOutOfBoundsException("Строки не существует.");
        }
        return new Vector(this.rows[indexRow]);
    }

    public void setRow(int indexRow, Vector vector) {
        if (indexRow < 0) {
            throw new IndexOutOfBoundsException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow >= this.getColumnCounts()) {
            throw new IndexOutOfBoundsException("Строки не существует.");
        }
        if (vector.getSize() != this.getColumnCounts()) {
            throw new IndexOutOfBoundsException("Размер строки не совпадает с размеров матрицы.");
        }

        this.rows[indexRow] = new Vector(vector);
    }

    public Vector getColumn(int indexColumn) {
        if (indexColumn < 0) {
            throw new IndexOutOfBoundsException("Номер столбца в матрице не может быть меньше 0");
        }

        if (indexColumn >= this.getColumnCounts()) {
            throw new IndexOutOfBoundsException("Столбца не существует.");
        }

        double[] component = new double[this.getRowCounts()];
        for (int i = 0; i < this.getRowCounts(); i++) {
            component[i] = this.rows[i].getComponent(indexColumn);
        }

        return new Vector(component);
    }

    public void transpose() {
        Vector[] matrix = new Vector[this.getColumnCounts()];
        for (int i = 0; i < this.getColumnCounts(); i++) {
            matrix[i] = new Vector(getColumn(i));

        }
        this.rows = matrix;
    }

    public void multiplicationByScalar(double n) {
        for (Vector vector : this.rows) {
            vector.multiplicationByScalar(n);
        }
    }

    public double calculateDeterminant() {
        if (getRowCounts() != getColumnCounts()) {
            throw new IllegalArgumentException("Определитель можно вычислить только у квадратной матрицы.");
        }

        double calculateDeterminant = 0;
        if (this.getRowCounts() == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[1].getComponent(0) * rows[0].getComponent(1);
        }

        int factor;
        for (int i = 0; i < getRowCounts(); i++) {
            if (i % 2 == 1) {
                factor = -1;
            } else {
                factor = 1;
            }
            Matrix minor = getMinor(this, 0, i);
            calculateDeterminant += factor * rows[0].getComponent(i) * minor.calculateDeterminant();
        }
        return calculateDeterminant;
    }

    private static Matrix getMinor(Matrix matrix, int row, int column) {
        int minorLength = matrix.rows.length - 1;
        Matrix minor = new Matrix(minorLength, minorLength);
        int dI = 0;

        for (int i = 0; i <= minorLength; i++) {
            int dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor.rows[i - dI].setComponent(j - dJ, matrix.rows[i].getComponent(j));
                    }
                }
            }
        }
        return minor;
    }

    public Vector multiplicationOnVector(Vector vector) {
        if (vector.getSize() != getRowCounts()) {
            throw new IllegalArgumentException("Не совпадает число строк в матрице и векторе");
        }

        int rowCounts = getRowCounts();
        int columnCounts = getColumnCounts();
        Vector vectorOut = new Vector(vector.getSize());
        for (int i = 0; i < rowCounts; i++) {
            Vector row = getRow(i);
            double temp = 0;
            for (int j = 0; j < columnCounts; j++) {
                temp += row.getComponent(j) * vector.getComponent(i);
            }
            vectorOut.setComponent(i, temp);
        }
        return vectorOut;
    }

    public void add(Matrix matrix) {
        if (getRowCounts() != matrix.getRowCounts() || getColumnCounts() != matrix.getColumnCounts()) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getRowCounts(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCounts() != matrix2.getRowCounts() || matrix1.getColumnCounts() != matrix2.getColumnCounts()) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        Matrix matrixOut = new Matrix(matrix1);
        matrixOut.add(matrix2);
        return matrixOut;
    }

    public void subtraction(Matrix matrix) {
        if (getRowCounts() != matrix.getRowCounts() || getColumnCounts() != matrix.getColumnCounts()) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getRowCounts(); i++) {
            rows[i].subtraction(matrix.rows[i]);
        }
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCounts() != matrix2.getRowCounts() || matrix1.getColumnCounts() != matrix2.getColumnCounts()) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        Matrix matrixOut = new Matrix(matrix1);
        matrixOut.subtraction(matrix2);
        return matrixOut;
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowCounts() != matrix2.getColumnCounts() || matrix1.getColumnCounts() != matrix2.getRowCounts()) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }

        Matrix matrixOut = new Matrix(matrix1.getRowCounts(), matrix2.getColumnCounts());
        for (int i = 0; i < matrix1.getRowCounts(); i++) {
            Vector vectorOut = new Vector(matrix1.getRowCounts());
            for (int j = 0; j < matrix2.getColumnCounts(); j++) {
                Vector column = matrix2.getColumn(j);
                double sum = 0;
                for (int k = 0; k < matrix1.getColumnCounts(); k++) {
                    sum += matrix1.rows[i].getComponent(k) * column.getComponent(k);
                }
                vectorOut.setComponent(j, sum);
            }
            matrixOut.setRow(i, vectorOut);
        }
        return matrixOut;
    }

    @Override
    public String toString() {
        int iMax = rows.length - 1;

        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(rows[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }
}
