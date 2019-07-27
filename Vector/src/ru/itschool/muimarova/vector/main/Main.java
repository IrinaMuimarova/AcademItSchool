package ru.itschool.muimarova.vector.main;

import ru.itschool.muimarova.vector.Vector;

public class Main {
    public static void main(String[] args) {
        Vector vector = new Vector(4);
        Vector vector1 = new Vector(4, new double[]{3,6,8,9});
        Vector vector2 = new Vector(vector1);
        Vector vector3 = new Vector(new double[]{3,8,5,3});

        System.out.println(vector.getSize());
        vector.setComponent(3,6);
        System.out.println(vector.add(vector1).toString());
        System.out.println(vector.reversal().toString());
        System.out.println(vector.equals(vector3));
        System.out.println(vector.MultiplicationByScalar(5).toString());
        System.out.println(vector.subtraction(vector2).toString());
        Vector vector4 = Vector.add(vector1,vector2);
        System.out.println(vector4.toString());
        System.out.println(Vector.scalarMultiplication(vector3, vector4));
    }
}
