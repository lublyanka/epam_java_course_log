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

        if (A == null) {
            throw new IllegalArgumentException("Point a is null");
        } else if (B == null) {
            throw new IllegalArgumentException("Point b is null");
        } else if (C == null) {
            throw new IllegalArgumentException("Point c is null");
        } else if (D == null) {
            throw new IllegalArgumentException("Point d is null");
        }

        if (new Segment(A, B).getLength() <= 0
                || new Segment(B, C).getLength() <= 0
                || new Segment(C, D).getLength() <= 0
                || new Segment(D, A).getLength() <= 0) {
            throw new IllegalArgumentException(("Quadrilateral does not exists"));
        } else if (area() <= 0.000001)
            throw new IllegalArgumentException(("Quadrilateral does not exists"));
        else if(360.0 - getAngleSum() > 0.000001)
        {
            throw new IllegalArgumentException(("Quadrilateral does not exists"));
        }
    }

    public double getAngleSum(){
        double result=Math.abs(new Segment(A, B).getAngle(new Segment(A, D))
                + new Segment(B, A).getAngle(new Segment(B, C))
                + new Segment(C, B).getAngle(new Segment(C, D))
                + new Segment(D, C).getAngle(new Segment(D, A)));
        //System.out.println(result);
        return result;
    }
    @Override
    public Point centroid() {
        Triangle triangle1 = new Triangle(A, B, C);
        Triangle triangle2 = new Triangle(A, C, D);
        Triangle triangle3 = new Triangle(A, B, D);
        Triangle triangle4 = new Triangle(B, C, D);
        Point centre1 = triangle1.centroid();
        Point centre2 = triangle2.centroid();
        Point centre3 = triangle3.centroid();
        Point centre4 = triangle4.centroid();
        return new Segment(centre1, centre2).getIntersection(new Segment(centre3, centre4));
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) {
            return true;
        }
        if (figure instanceof Quadrilateral) {
            if ((this.A.isTheSame(((Quadrilateral) figure).A) || this.A.isTheSame(((Quadrilateral) figure).B) || this.A.isTheSame(((Quadrilateral) figure).C) || this.A.isTheSame(((Quadrilateral) figure).D))
                    && (this.B.isTheSame(((Quadrilateral) figure).A) || this.B.isTheSame(((Quadrilateral) figure).B) || this.B.isTheSame(((Quadrilateral) figure).C) || this.B.isTheSame(((Quadrilateral) figure).D))
                    && (this.C.isTheSame(((Quadrilateral) figure).A) || this.C.isTheSame(((Quadrilateral) figure).B) || this.C.isTheSame(((Quadrilateral) figure).C) || this.C.isTheSame(((Quadrilateral) figure).D))
                    && (this.D.isTheSame(((Quadrilateral) figure).A) || this.D.isTheSame(((Quadrilateral) figure).B) || this.D.isTheSame(((Quadrilateral) figure).C) || this.D.isTheSame(((Quadrilateral) figure).D)))
                return true;
        }
        return false;
    }

    @Override
    public double area() {
        return new Triangle(A, B, C).area() + new Triangle(A, C, D).area();
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
        if (leftmostPoint.getX() > D.getX()) {
            leftmostPoint = D;
        }

        return leftmostPoint;
    }

    @Override
    public String pointsToString() {
        return A.toString() + B.toString() + C.toString() + D.toString();
    }
}
