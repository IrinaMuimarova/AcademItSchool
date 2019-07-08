package ru.academit.muimarova.shapes;

public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Rectangle rectangle = (Rectangle) obj;
        return height == rectangle.height && width == rectangle.width;
    }

    @Override
    public String toString() {
        return "Прямоугольник со сторанами " + height + " и " + width;
    }

    @Override
    public int hashCode() {
        final int prime = 53;
        int hash = 1;
        hash = prime + hash + Double.hashCode(height);
        hash = prime + hash + Double.hashCode(width);
        return hash;
    }
}
