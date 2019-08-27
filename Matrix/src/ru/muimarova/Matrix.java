package ru.muimarova;

import ru.itschool.muimarova.vector.Vector;

public class Matrix {
    private static final String ILLEGAL_ROWS_COUNT = "Количество строк в матрице не может быть меньше или равно 0";
    private static final String ILLEGAL_COLUMNS_COUNT = "Количество стоблцов в матрице не может быть меньше или равно 0";

    private Vector[] rows;

    public Matrix(int rowsCount, int columnsCount) {
        if (rowsCount <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ROWS_COUNT);
        }
        if (columnsCount <= 0) {
            throw new IllegalArgumentException(ILLEGAL_COLUMNS_COUNT);
        }

        rows = new Vector[rowsCount];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsCount);
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
            throw new IllegalArgumentException(ILLEGAL_ROWS_COUNT);
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length > 0){
                break;
            }
            if (i == matrix.length - 1){
                throw new IllegalArgumentException(ILLEGAL_COLUMNS_COUNT);
            }
        }

        this.rows = new Vector[matrix.length];

        int rowLength = matrix[0].length;
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length > rowLength) {
                rowLength = matrix[i].length;
            }
        }

        for (int i = 0; i < this.rows.length; i++) {
            this.rows[i] = new Vector(rowLength, matrix[i]);
        }
    }

    public Matrix(Vector[] matrix) {
        if (matrix.length <= 0) {
            throw new IllegalArgumentException(ILLEGAL_ROWS_COUNT);
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

    public int getRowsCount() {
        return this.rows.length;
    }

    public int getColumnsCount() {
        return this.rows[0].getSize();
    }

    public Vector getRow(int indexRow) {
        if (indexRow < 0) {
            throw new IndexOutOfBoundsException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow >= this.getRowsCount()) {
            throw new IndexOutOfBoundsException("Строки не существует.");
        }
        return new Vector(this.rows[indexRow]);
    }

    public void setRow(int indexRow, Vector vector) {
        if (indexRow < 0) {
            throw new IndexOutOfBoundsException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow >= this.getColumnsCount()) {
            throw new IndexOutOfBoundsException("Строки не существует.");
        }
        if (vector.getSize() != this.getColumnsCount()) {
            throw new IllegalArgumentException("Размер строки не совпадает с размером матрицы.");
        }

        this.rows[indexRow] = new Vector(vector);
    }

    public Vector getColumn(int indexColumn) {
        if (indexColumn < 0) {
            throw new IndexOutOfBoundsException("Номер столбца в матрице не может быть меньше 0");
        }

        if (indexColumn >= this.getColumnsCount()) {
            throw new IndexOutOfBoundsException("Столбца не существует.");
        }

        double[] component = new double[this.getRowsCount()];
        for (int i = 0; i < this.getRowsCount(); i++) {
            component[i] = this.rows[i].getComponent(indexColumn);
        }

        return new Vector(component);
    }

    public void transpose() {
        Vector[] matrix = new Vector[this.getColumnsCount()];
        for (int i = 0; i < this.getColumnsCount(); i++) {
            matrix[i] = getColumn(i);
        }
        this.rows = matrix;
    }

    public void multiplicationByScalar(double n) {
        for (Vector vector : this.rows) {
            vector.multiplicationByScalar(n);
        }
    }

    public double calculateDeterminant() {
        if (getRowsCount() != getColumnsCount()) {
            throw new IllegalArgumentException("Определитель можно вычислить только у квадратной матрицы.");
        }

        double calculateDeterminant = 0;
        if (this.getRowsCount() == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[1].getComponent(0) * rows[0].getComponent(1);
        }

        int factor;
        for (int i = 0; i < getRowsCount(); i++) {
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
        if (vector.getSize() != getColumnsCount()) {
            throw new IllegalArgumentException("Не совпадает число столбцов в матрице и векторе");
        }

        int rowsCount = getRowsCount();
        int columnsCount = getColumnsCount();
        Vector vectorOut = new Vector(vector.getSize());
        for (int i = 0; i < rowsCount; i++) {
            Vector row = getRow(i);
            double temp = 0;
            for (int j = 0; j < columnsCount; j++) {
                temp += row.getComponent(j) * vector.getComponent(i);
            }
            vectorOut.setComponent(i, temp);
        }
        return vectorOut;
    }

    public void add(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        Matrix matrixOut = new Matrix(matrix1);
        matrixOut.add(matrix2);
        return matrixOut;
    }

    public void subtraction(Matrix matrix) {
        if (getRowsCount() != matrix.getRowsCount() || getColumnsCount() != matrix.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getRowsCount(); i++) {
            rows[i].subtraction(matrix.rows[i]);
        }
    }

    public static Matrix subtraction(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getRowsCount() != matrix2.getRowsCount() || matrix1.getColumnsCount() != matrix2.getColumnsCount()) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        Matrix matrixOut = new Matrix(matrix1);
        matrixOut.subtraction(matrix2);
        return matrixOut;
    }

    public static Matrix multiplication(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsCount() != matrix2.getRowsCount()) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }

        Matrix matrixOut = new Matrix(matrix1.getRowsCount(), matrix2.getColumnsCount());
        for (int i = 0; i < matrix1.getRowsCount(); i++) {
            Vector vectorOut = new Vector(matrix1.getRowsCount());
            for (int j = 0; j < matrix2.getColumnsCount(); j++) {
                Vector column = matrix2.getColumn(j);
                double sum = 0;
                for (int k = 0; k < matrix1.getColumnsCount(); k++) {
                    sum += matrix1.rows[i].getComponent(k) * column.getComponent(k);
                }
                vectorOut.setComponent(j, sum);
            }
            matrixOut.rows[i] = vectorOut;
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
