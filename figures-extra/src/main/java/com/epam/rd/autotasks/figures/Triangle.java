package com.epam.rd.autotasks.figures;

class Triangle extends Figure {
    private final Point A;
    private final Point B;
    private final Point C;
    private final double determinant;

    public Triangle(Point A, Point B, Point C) {
        if (A == null) {
            throw new IllegalArgumentException("Point a is null");
        }
        if (B == null) {
            throw new IllegalArgumentException("Point b is null");
        }
        if (C == null) {
            throw new IllegalArgumentException("Point c is null");
        }

        this.determinant = Math.abs((A.getX() - C.getX()) * (B.getY() - C.getY()) - (B.getX() - C.getX()) * (A.getY() - C.getY()));

        if (!(Double.compare(determinant, 0) == 0)) {
            this.A = A;
            this.B = B;
            this.C = C;
        }

        else throw new IllegalArgumentException("Triangle does not exists");
    }

    @Override
    public double area() {
        return 0.5*determinant;
    }

    @Override
    public Point leftmostPoint() {
        Point leftmostPoint;
        if (A.getX() < B.getX()){
            leftmostPoint = A;
        }else{
            leftmostPoint = B;
        }

        if (leftmostPoint.getX() > C.getX()){
            leftmostPoint = C;
        }
        return leftmostPoint;
    }

    public Point centroid() {
        return new Point((A.getX() + B.getX() + C.getX())/3,
                (A.getY() + B.getY() + C.getY())/3);
    }

    @Override
    public boolean isTheSame(Figure figure) {
       if (this == figure) {
            return true;
        }
        if (figure instanceof Triangle) {
            if ((this.A.isTheSame(((Triangle)figure).A) || this.A.isTheSame(((Triangle)figure).B) || this.A.isTheSame(((Triangle)figure).C))
                && (this.B.isTheSame(((Triangle)figure).A) || this.B.isTheSame(((Triangle)figure).B) || this.B.isTheSame(((Triangle)figure).C))
                && (this.C.isTheSame(((Triangle)figure).A) || this.C.isTheSame(((Triangle)figure).B) || this.C.isTheSame(((Triangle)figure).C)))
                return true;
        }
        return false;
    }

    @Override
    public String pointsToString() {
        return A.toString() + B.toString() + C.toString();
    }

}
