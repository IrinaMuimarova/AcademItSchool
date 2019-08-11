package ru.muimarova;

import ru.itschool.muimarova.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int countRow, int countColumn) {
        if (countRow <= 0) {
            throw new IllegalArgumentException("Количество строк в матрице не может быть меньше или равно 0");
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
        if (matrix.length <= 0 || matrix[0].length <= 0){

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

    public int[] getSize() {
        return new int[]{this.rows.length, this.rows[0].getSize()};
    }

    public Vector getVectorString(int indexRow) {
        if (indexRow < 0) {
            throw new IllegalArgumentException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow > this.getSize()[0]) {
            throw new IllegalArgumentException("Строки не существует.");
        }
        return this.rows[indexRow];
    }

    public void setVectorString(int indexRow, Vector vector) {
        if (indexRow < 0) {
            throw new IllegalArgumentException("Номер строки в матрице не может быть меньше или равно 0");
        }
        if (indexRow > this.getSize()[0]) {
            throw new IllegalArgumentException("Строки не существует.");
        }
        this.rows[indexRow] = vector;
    }

    public Vector getColumn(int indexColumn) {
        if (indexColumn < 0) {
            throw new IllegalArgumentException("Номер столбца в матрице не может быть меньше 0");
        }
        if (indexColumn > this.getSize()[1]) {
            throw new IllegalArgumentException("Столбца не существует.");
        }
        double[] component = new double[this.getSize()[0]];
        for (int i = 0; i < this.getSize()[0]; i++) {
            component[i] = this.rows[i].getComponents(indexColumn);
        }
        return new Vector(component);
    }

    public void transpose() {
        Matrix matrix = new Matrix(this.getSize()[1], this.getSize()[0]);
        for (int i = 0; i < this.getSize()[1]; i++) {
            matrix.setVectorString(i, this.getColumn(i));
        }
        this.rows = matrix.rows;
    }

    public void multiplicationByScalar(double n) {
        for (int i = 0; i < this.getSize()[0]; i++) {
            this.getVectorString(i).multiplicationByScalar(n);
        }
    }

    public double calculateDeterminant() {
        if (getSize()[0] != getSize()[1]) {
            throw new IllegalArgumentException("Определитель можно вычислить только у квадратной матрицы.");
        }

        double calculateDeterminant = 0;
        if (this.getSize()[0] == 2) {
            return rows[0].getComponents(0) * rows[1].getComponents(1) - rows[1].getComponents(0) * rows[0].getComponents(1);
        }

        int factor;
        for (int i = 0; i < getSize()[0]; i++) {
            if (i % 2 == 1) {
                factor = -1;
            } else {
                factor = 1;
            }
            Matrix minor = getMinor(this, 0, i);
            calculateDeterminant += factor * rows[0].getComponents(i) * minor.calculateDeterminant();
        }
        return calculateDeterminant;
    }

    private Matrix getMinor(Matrix matrix, int row, int column) {
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
                        minor.rows[i - dI].setComponent(j - dJ, matrix.rows[i].getComponents(j));
                    }
                }
            }
        }
        return minor;
    }

    public void multiplicationOnVector(Vector vector) {
        if (vector.getSize() != getSize()[1]) {
            throw new IllegalArgumentException("Не совпадает число столбцов в матрице и векторе");
        }
        for (int i = 0; i < getSize()[0]; i++) {
            Vector string = getVectorString(i);
            for (int j = 0; j < getSize()[1]; j++) {
                string.setComponent(j, vector.getComponents(j) * string.getComponents(j));
            }
        }
    }

    public void add(Matrix matrix) {
        if (getSize()[0] != matrix.getSize()[0] && getSize()[1] != matrix.getSize()[1]) {
            throw new IllegalArgumentException("Суммировать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getSize()[0]; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public static Matrix add(Matrix matrix, Matrix matrix1) {
        Matrix matrixOut = new Matrix(matrix);
        matrixOut.add(matrix1);
        return matrixOut;
    }

    public void subtraction(Matrix matrix) {
        if (getSize()[0] != matrix.getSize()[0] && getSize()[1] != matrix.getSize()[1]) {
            throw new IllegalArgumentException("Вычитать матрицы можно только одинаковой размерности");
        }

        for (int i = 0; i < getSize()[0]; i++) {
            rows[i].subtraction(matrix.rows[i]);
        }
    }

    public static Matrix subtraction(Matrix matrix, Matrix matrix1) {
        Matrix matrixOut = new Matrix(matrix);
        matrixOut.subtraction(matrix1);
        return matrixOut;
    }

    public static Matrix multiplication(Matrix matrix, Matrix matrix1) {
        if (matrix.getSize()[1] != matrix1.getSize()[0]) {
            throw new IllegalArgumentException("Матрицы не согласованы");
        }
        Matrix matrix2 = new Matrix(matrix.getSize()[0], matrix1.getSize()[1]);
        for (int i = 0; i < matrix.getSize()[0]; i++) {
            Vector row = matrix.getVectorString(i);

            Vector vectorOut = new Vector(matrix.getSize()[0]);
            for (int j = 0; j < matrix1.getSize()[1]; j++) {
                Vector column = matrix1.getColumn(j);
                double sum = 0;
                for (int k = 0; k < matrix.getSize()[1]; k++) {
                    sum += row.getComponents(k) * column.getComponents(k);
                }
                vectorOut.setComponent(j, sum);
            }
            matrix2.setVectorString(i, vectorOut);
        }
        return matrix2;
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
