package com.epam.rd.autotasks.figures;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment extends Figure{
    private final Point A;
    private final Point B;

    public Segment(Point a, Point b) {


        if (a.equals(b))
            throw new IllegalArgumentException("The start and the end of the segment is the same point");
        if (a == null || b == null)
            throw new IllegalArgumentException("One or both points are null");

            this.A = a;
            this.B = b;
    }

    //d={\sqrt  {(x_{2}-x_{1})^{2}+(y_{2}-y_{1})^{2}}
    public double getLength() {

        return sqrt(pow((B.getX() - A.getX()), 2)
                + pow((B.getY() - A.getY()), 2));
    }

    public Point getIntersection(Segment another) {

        //https://profmeter.com.ua/communication/learning/course/course19/lesson194/
        double x1 = this.A.getX();
        double x2 = this.B.getX();
        double x3 = another.A.getX();
        double x4 = another.B.getX();
        double y1 = this.A.getY();
        double y2 = this.B.getY();
        double y3 = another.A.getY();
        double y4 = another.B.getY();

        // k1 =  ( у2 - у1 ) / ( x2 - x1 )
        double k1;
        if (Double.compare(y2, y1) == 0) {
            k1 = 0;
        } else
            k1 = (y2 - y1) / (x2 - x1);

        //k2 =  ( у4 - у3 ) / ( x4 - x3 )
        double k2;
        if (Double.compare(y4, y3) == 0) {
            k2 = 0;
        } else
            k2 = (y4 - y3) / (x4 - x3);

        //parallel
        if (Double.compare(k1, k2) == 0) {
            return null;
        }
        //normal
        else {
            //b1 = у1 - k1 * x1
            //b2 = у3  - k2 * x3

            double b1 = y1 - k1 * x1;
            double b2 = y3 - k2 * x3;

            //x = ( b2 - b1 ) / ( k1 -  k2 )
            //y = k1 x + b1.
            double x = (b2 - b1) / (k1 - k2);
            double y = k1 * x + b1;

            if ((
                    (((x >= x1) && (x <= x2)) || ((x >= x2) && (x <= x1)))
                            && ((x >= x3) && (x <= x4)) || ((x >= x4) && (x <= x3))
            )
                    &&
                    (
                            ((y >= y1) && (y <= y2)) || ((y >= y2) && (y <= y1))
                                    && ((y >= y3) && (y <= y4)) || ((y >= y4) && (y <= y3))
                    )
            ) {
                return new Point(x, y);
            } else return null;
        }
    }

    public double getAngle(Segment segment) {
        Vector vector1 = this.toVector();
        Vector vector2 = segment.toVector();
        double cos = (vector1.a*vector2.a + vector1.b*vector2.b)
                / (sqrt(pow(vector1.a,2)+pow(vector1.b,2))*sqrt(pow(vector2.a,2)+pow(vector2.b,2)));
        //System.out.println(Math.toDegrees(Math.acos(cos)));
        return Math.toDegrees(Math.acos(cos));
    }

    //  [(x1 + x2)/2,( y1 + y2)/2]
    @Override
    public Point centroid() {
        return new Point((A.getX() + B.getX()) / 2,
                    (A.getY() + B.getY()) / 2);
    }

    //TODO
    @Override
    public boolean isTheSame(Figure figure) {
        return false;
    }

    @Override
    public double area() {
        return 0;
    }

    @Override
    public String pointsToString() {
        return A.toString() + B.toString();
    }

    @Override
    public Point leftmostPoint() {
        return A;
    }

    public Vector toVector(){
        return new Vector(this);
    }

    private class Vector {
        private double a;
        private double b;

        private Vector(Point a, Point b){
            this.a = abs(a.getX()-b.getX());
            this.b = abs(a.getY()-b.getY());
        }

        private Vector(Segment segment){
            this.a = segment.B.getX() - segment.A.getX();
            this.b = segment.B.getY() - segment.A.getY();
            System.out.println(a + " " + b);
        }
    }
}
