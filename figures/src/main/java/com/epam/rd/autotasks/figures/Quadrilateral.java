package com.epam.rd.autotasks.figures;

class Quadrilateral extends Figure {

    final Point A;
    final Point B;
    final Point C;
    final Point D;

    public Quadrilateral(Point A, Point B, Point C, Point D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    @Override
    public double area() {
        return new Triangle(A,B,C).area() + new Triangle(A,C,D).area();
    }

    @Override
    public Point leftmostPoint() {
        Point leftmostPoint;
        if (A.getX() < B.getX()) {
            leftmostPoint = A;
        } else {
            leftmostPoint = B;
        }

        if (leftmostPoint.getX() > C.getX()) {
            leftmostPoint = C;
        }
        if (leftmostPoint.getX() > D.getX()){
            leftmostPoint = D;
        }

        return leftmostPoint;
    }

    @Override
    public String pointsToString() {
        return A.toString() + B.toString() + C.toString() + D.toString();
    }

}
