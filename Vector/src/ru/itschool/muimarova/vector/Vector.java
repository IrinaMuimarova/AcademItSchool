package ru.itschool.muimarova.vector;

import java.util.Arrays;
import java.util.HashMap;

public class Vector {
    private double[] array;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        double[] array = new double[n];
        Arrays.fill(array, 0);
        this.array = array;
    }

    public Vector(double[] array) {
        this.array = array;
    }

    public Vector(Vector vector) {
        array = vector.array;
    }

    public Vector(int n, double[] array) {
        Vector vector = new Vector(n);
        for (int i = 0; i < array.length; i++) {
            vector.array[i] = array[i];
        }
        this.array = vector.array;
    }

    public double[] getArray() {
        return array;
    }

    public void setArray(double[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        if (array == null)
            return "null";
        int iMax = array.length - 1;
        if (iMax == -1)
            return "{}";

        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == iMax)
                return b.append('}').toString();
            b.append(", ");
        }
    }

    public int getSize() {
        return array.length;
    }

    public Vector add(Vector vector) {
        Vector vector1 = this;
        if (this.getSize() < vector.getSize()) {
            vector1 = new Vector(vector.getSize(), this.array);
        }
        if (this.getSize() > vector.getSize()) {
            vector = new Vector(this.getSize(), vector.array);
        }
        for (int i = 0; i < vector.getSize(); i++) {
            vector1.array[i] += vector.array[i];
        }

        return vector1;
    }

    public static Vector add(Vector vector1, Vector vector2) {
        return new Vector(vector1.add(vector2));
    }

    public Vector subtraction(Vector vector) {
        Vector vector1 = this;
        if (this.getSize() < vector.getSize()) {
            vector1 = new Vector(vector.getSize(), this.array);
        }
        if (this.getSize() > vector.getSize()) {
            vector = new Vector(this.getSize(), vector.array);
        }
        for (int i = 0; i < vector.getSize(); i++) {
            vector1.array[i] -= vector.array[i];
        }

        return vector1;
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        return new Vector(vector1.subtraction(vector2));
    }

    public Vector scalarMultiplication(double n) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= n;
        }
        return this;
    }

    public static Vector scalarMultiplication(Vector vector, Vector vector1) {
        if (vector1.getSize() < vector.getSize()) {
            vector1 = new Vector(vector.getSize(), vector1.array);
        }
        if (vector1.getSize() > vector.getSize()) {
            vector = new Vector(vector1.getSize(), vector.array);
        }
        for (int i = 0; i < vector.getSize(); i++) {
            vector1.array[i] *= vector.array[i];
        }

        return new Vector(vector1);
    }

    public Vector reversal() {
        for (int i = 0; i < array.length; i++) {
            array[i] *= -1;
        }
        return this;
    }

    public void setComponent(int index, double value){
        array[index] = value;
    }

    public boolean equals(Vector vector) {
        if (this.getSize() != vector.getSize()){
            return false;
        }
        for (int i = 0; i < this.getSize(); i++) {
            if (this.array[i] != vector.array[i])
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int hash = 1;
        hash = prime + hash + Arrays.hashCode(array);
        return hash;
    }
}
