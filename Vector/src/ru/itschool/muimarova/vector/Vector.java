package ru.itschool.muimarova.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора меньше или равна 0.");
        }
        this.components = new double[n];
    }

    public Vector(double[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Массив равен 0. Вектор не создан");
        }
        this.components = Arrays.copyOf(array, array.length);
    }

    public Vector(Vector vector) {
        this.components = Arrays.copyOf(vector.components, vector.components.length);
    }

    public Vector(int n, double[] array) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размерность вектора меньше или равна 0.");
        }
        this.components = Arrays.copyOf(array, n);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double value) {
        components[index] = value;
    }

    @Override
    public String toString() {
        int iMax = components.length - 1;

        StringBuilder b = new StringBuilder();
        b.append('{');
        for (int i = 0; ; i++) {
            b.append(components[i]);
            if (i == iMax) {
                return b.append('}').toString();
            }
            b.append(", ");
        }
    }

    public int getSize() {
        return components.length;
    }

    public void add(Vector vector) {
        if (this.getSize() < vector.getSize()) {
            this.components = Arrays.copyOf(this.components, vector.getSize());
        }
        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] += vector.components[i];
        }
    }

    public static Vector add(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.add(vector2);
        return vector;
    }

    public void subtraction(Vector vector) {
        if (this.getSize() < vector.getSize()) {
            this.components = Arrays.copyOf(components, vector.getSize());
        }

        for (int i = 0; i < vector.getSize(); i++) {
            this.components[i] -= vector.components[i];
        }
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        Vector vector = new Vector(vector1);
        vector.subtraction(vector2);
        return vector;
    }

    public void multiplicationByScalar(double n) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= n;
        }
    }

    public static double scalarMultiplication(Vector vector, Vector vector1) {
        double scalarMultiplication = 0;
        int minSize = Math.min(vector1.getSize(), vector.getSize());

        for (int i = 0; i < minSize; i++) {
            scalarMultiplication += vector.components[i] * vector1.components[i];
        }

        return scalarMultiplication;
    }

    public void invert() {
        this.multiplicationByScalar(-1);
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
            if (this.components[i] != vector.components[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int hash = 1;
        hash = prime + hash + Arrays.hashCode(components);
        return hash;
    }
}
