package ru.itschool.muimarova.vector;

import java.util.Arrays;

public class Vector {
    private double[] arrayOfVectorComponents;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность матрицы меньше или равна 0.");
        }
        this.arrayOfVectorComponents = new double[n];
    }

    public Vector(double[] array) {
        this.arrayOfVectorComponents = Arrays.copyOf(array, array.length);
    }

    public Vector(Vector vector) {
        this.arrayOfVectorComponents = Arrays.copyOf(vector.arrayOfVectorComponents, vector.arrayOfVectorComponents.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность матрицы меньше или равна 0.");
        }
        this.arrayOfVectorComponents = Arrays.copyOf(array, n);
    }

    public double getArrayOfVectorComponents(int index) {
        return arrayOfVectorComponents[index];
    }

    @Override
    public String toString() {
        int iMax = arrayOfVectorComponents.length - 1;

        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(arrayOfVectorComponents[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }

    public int getSize() {
        return arrayOfVectorComponents.length;
    }

    public Vector add(Vector vector) {
        if (this.getSize() < vector.getSize()) {
            this.arrayOfVectorComponents = Arrays.copyOf(this.arrayOfVectorComponents, vector.getSize());
        }
        for (int i = 0; i < vector.getSize(); i++) {
            this.arrayOfVectorComponents[i] += vector.arrayOfVectorComponents[i];
        }
        return this;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1.getSize() > vector2.getSize() ? vector1.getSize() : vector2.getSize());

        if (vector1.getSize() < vector.getSize()) {
            vector1.arrayOfVectorComponents = Arrays.copyOf(vector1.arrayOfVectorComponents, vector.getSize());
        }

        if (vector2.getSize() < vector.getSize()) {
            vector2.arrayOfVectorComponents = Arrays.copyOf(vector2.arrayOfVectorComponents, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            vector.arrayOfVectorComponents[i] = vector1.arrayOfVectorComponents[i] + vector2.arrayOfVectorComponents[i];
        }

        return vector;
    }

    public Vector subtraction(Vector vector) {
        if (this.getSize() < vector.getSize()) {
            this.arrayOfVectorComponents = Arrays.copyOf(arrayOfVectorComponents, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.arrayOfVectorComponents[i] -= vector.arrayOfVectorComponents[i];
        }

        return this;
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1.getSize() > vector2.getSize() ? vector1.getSize() : vector2.getSize());

        if (vector1.getSize() < vector.getSize()) {
            vector1.arrayOfVectorComponents = Arrays.copyOf(vector1.arrayOfVectorComponents, vector.getSize());
        }

        if (vector2.getSize() < vector.getSize()) {
            vector2.arrayOfVectorComponents = Arrays.copyOf(vector2.arrayOfVectorComponents, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            vector.arrayOfVectorComponents[i] = vector1.arrayOfVectorComponents[i] - vector2.arrayOfVectorComponents[i];
        }

        return vector;
    }

    public Vector MultiplicationByScalar(double n) {
        for (int i = 0; i < arrayOfVectorComponents.length; i++) {
            arrayOfVectorComponents[i] *= n;
        }

        return this;
    }

    public static double scalarMultiplication(Vector vector, Vector vector1) {
        if (vector1.getSize() < vector.getSize()) {
            vector1 = new Vector(vector.getSize(), vector1.arrayOfVectorComponents);
        }

        if (vector1.getSize() > vector.getSize()) {
            vector = new Vector(vector1.getSize(), vector.arrayOfVectorComponents);
        }

        double scalarMultiplication = 0;

        for (int i = 0; i < vector.getSize(); i++) {
            scalarMultiplication += vector.arrayOfVectorComponents[i] * vector1.arrayOfVectorComponents[i];
        }

        return scalarMultiplication;
    }

    public Vector reversal() {
        this.MultiplicationByScalar(-1);
        return this;
    }

    public void setComponent(int index, double value) {
        arrayOfVectorComponents[index] = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;
        if (this.getSize() != vector.getSize()) {
            return false;
        }
        for (int i = 0; i < this.getSize(); i++) {
            if (this.arrayOfVectorComponents[i] != vector.arrayOfVectorComponents[i])
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int hash = 1;
        hash = prime + hash + Arrays.hashCode(arrayOfVectorComponents);
        return hash;
    }
}
