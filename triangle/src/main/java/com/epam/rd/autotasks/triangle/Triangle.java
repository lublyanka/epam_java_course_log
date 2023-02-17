package com.epam.rd.autotasks.triangle;

class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;

    private final double determinant;

    public Triangle(Point a, Point b, Point c) {
        if (a == null) {
            throw new IllegalArgumentException("Point a is null");
        }
        if (b == null) {
            throw new IllegalArgumentException("Point b is null");
        }
        if (c == null) {
            throw new IllegalArgumentException("Point c is null");
        }

        this.determinant = Math.abs((a.getX() - c.getX()) * (b.getY() - c.getY()) - (b.getX() - c.getX()) * (a.getY() - c.getY()));
        if (!(Double.compare(determinant,0) == 0)) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        else throw new IllegalArgumentException("Triangle not exists");
    }

    public double area() {
        return 0.5*determinant;
    }

    public Point centroid() {
        return new Point((a.getX() + b.getX() + c.getX())/3,
                         (a.getY() + b.getY() + c.getY())/3);
    }

}
