package ru.muimarova;

import ru.itschool.muimarova.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private static final String ILLEGAL_COUNT_ROW= "Количество строк в матрице не может быть меньше или равно 0";
    private static final String ILLEGAL_COUNT_COLUMN= "Количество стоблцов в матрице не может быть меньше или равно 0";

    private Vector[] rows;

    public Matrix(int countRow, int countColumn) {
        if (countRow <= 0) {
            throw new IllegalArgumentException(ILLEGAL_COUNT_ROW);
        }
        if (countColumn <= 0) {
            throw new IllegalArgumentException(ILLEGAL_COUNT_COLUMN);
        }

        rows = new Vector[countRow];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(countColumn);
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
            throw new IllegalArgumentException(ILLEGAL_COUNT_ROW);
        } else if (matrix[0].length <= 0) {
            throw new IllegalArgumentException(ILLEGAL_COUNT_COLUMN);
        }

        this.rows = new Vector[matrix.length];

        int lengthString = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length > lengthString) {
                lengthString = matrix[i].length;
            }
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(Arrays.copyOf(matrix[i], lengthString));
        }
    }

    public Matrix(Vector[] matrix) {
        if (matrix.length <= 0) {
            throw new IllegalArgumentException(ILLEGAL_COUNT_ROW);
        }

        this.rows = new Vector[matrix.length];
        int lengthString = matrix[0].getSize();
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].getSize() > lengthString) {
                lengthString = matrix[i].getSize();
            }
        }

        for (int i = 0; i < rows.length; i++) {
            this.rows[i] = new Vector(lengthString);
            this.rows[i].add(matrix[i]);
        }
    }

    public int getSizeRows() {
        return this.rows.length;
    }

    public int getSizeColumn() {
        return this.rows[0].getSize();
    }

    public Vector getRow(int indexRow) {
        if (indexRow < 0) {
            throw new IllegalArgumentException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow >= this.getSizeRows()) {
            throw new IndexOutOfBoundsException("Строки не существует.");
        }
        return new Vector(this.rows[indexRow]);
    }

    public void setRow(int indexRow, Vector vector) {
        if (indexRow < 0) {
            throw new IllegalArgumentException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow >= this.getSizeColumn()) {
            throw new IndexOutOfBoundsException("Строки не существует.");
        }
        if (vector.getSize() != this.getSizeColumn()){
            throw new IllegalArgumentException("Размер строки не совпадает с размеров матрицы.");
        }

        this.rows[indexRow] = new Vector(vector);
    }

    public Vector getColumn(int indexColumn) {
        if (indexColumn < 0) {
            throw new IllegalArgumentException("Номер столбца в матрице не может быть меньше 0");
        }

        if (indexColumn >= this.getSizeColumn()) {
            throw new IndexOutOfBoundsException("Столбца не существует.");
        }

        double[] component = new double[this.getSizeRows()];
        for (int i = 0; i < this.getSizeRows(); i++) {
            component[i] = this.rows[i].getComponent(indexColumn);
        }

        return new Vector(component);
    }

    public void transpose() {
//        Matrix matrix = new Matrix(this.getSizeColumn(), this.getSizeRows());
//        for (int i = 0; i < this.getSizeColumn(); i++) {
//            matrix.setRow(i, this.getColumn(i));
//        }
//        this.rows = matrix.rows;
        for (int i = 0; i < getSizeColumn(); i++) {
            setRow(i, getColumn(i));
        }
    }

    public void multiplicationByScalar(double n) {
        for (Vector vector : this.rows) {
            vector.multiplicationByScalar(n);
        }
    }

    public double calculateDeterminant() {
        if (getSizeRows() != getSizeColumn()) {
            throw new IllegalArgumentException("Определитель можно вычислить только у квадратной матрицы.");
        }

        double calculateDeterminant = 0;
        if (this.getSizeRows() == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[1].getComponent(0) * rows[0].getComponent(1);
        }

        int factor;
        for (int i = 0; i < getSizeRows(); i++) {
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

    public void multiplicationOnVector(Vector vector) {
        if (vector.getSize() != getSizeColumn()) {
            throw new IllegalArgumentException("Не совпадает число столбцов в матрице и векторе");
        }
        int sizeRows = getSizeRows();
        int sizeColumn = getSizeColumn();
        for (int i = 0; i < sizeRows; i++) {
            Vector row = getRow(i);
            for (int j = 0; j < sizeColumn; j++) {
                row.setComponent(j, vector.getComponent(j) * row.getComponent(j));
            }
            this.setRow(i, row);
        }
    }

    public void add(Matrix matrix) {
        if (getSizeRows() != matrix.getSizeRows() || getSizeColumn() != matrix.getSizeColumn()) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getSizeRows(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSizeRows() != matrix2.getSizeRows() || matrix1.getSizeColumn() != matrix2.getSizeColumn()) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        Matrix matrixOut = new Matrix(matrix1);
        matrixOut.add(matrix2);
        return matrixOut;
    }

    public void subtraction(Matrix matrix) {
        if (getSizeRows() != matrix.getSizeRows() || getSizeColumn() != matrix.getSizeColumn()) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getSizeRows(); i++) {
            rows[i].subtraction(matrix.rows[i]);
        }
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSizeRows() != matrix2.getSizeRows() || matrix1.getSizeColumn() != matrix2.getSizeColumn()) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        Matrix matrixOut = new Matrix(matrix1);
        matrixOut.subtraction(matrix2);
        return matrixOut;
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSizeRows() != matrix2.getSizeColumn()) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }

        Matrix matrixOut = new Matrix(matrix1.getSizeRows(), matrix2.getSizeColumn());
        for (int i = 0; i < matrix1.getSizeRows(); i++) {
            Vector row = matrix1.getRow(i);

            Vector vectorOut = new Vector(matrix1.getSizeRows());
            for (int j = 0; j < matrix2.getSizeColumn(); j++) {
                Vector column = matrix2.getColumn(j);
                double sum = 0;
                for (int k = 0; k < matrix1.getSizeColumn(); k++) {
                    sum += row.getComponent(k) * column.getComponent(k);
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
