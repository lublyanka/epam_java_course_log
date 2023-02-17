package com.epam.rd.autotasks.segments;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

class Segment {
    private final Point start;
    private final Point end;

    public Segment(Point start, Point end) {


        if (start.equals(end))
            throw new IllegalArgumentException("The start and the end of the segment is the same point");
        if (start == null || end == null)
            throw new IllegalArgumentException("One or both points are null");

        //should be  x1 ≤ x2; x3 ≤ x4;
        if (start.getX() <= end.getX()){
            this.start = start;
            this.end = end;}
        else {
            this.start = end;
            this.end = start;}
    }

    //d={\sqrt  {(x_{2}-x_{1})^{2}+(y_{2}-y_{1})^{2}}
    double length() {

        return sqrt(pow((end.getX() - start.getX()), 2)
                  + pow((end.getY() - start.getY()), 2));
    }

    //  [(x1 + x2)/2,( y1 + y2)/2]
    Point middle() {

        return new Point((start.getX() + end.getX()) / 2,
                         (start.getY() + end.getY()) / 2);
    }

    Point intersection(Segment another) {

        //https://profmeter.com.ua/communication/learning/course/course19/lesson194/
        double x1 = this.start.getX();
        double x2 = this.end.getX();
        double x3 = another.start.getX();
        double x4 = another.end.getX();
        double y1 = this.start.getY();
        double y2 = this.end.getY();
        double y3 = another.start.getY();
        double y4 = another.end.getY();

        // k1 =  ( у2 - у1 ) / ( x2 - x1 )
        double k1;
        if(Double.compare(y2,y1)==0)
          {k1=0;}
        else
        k1 =  ( y2 - y1 ) / ( x2 - x1 );

        //k2 =  ( у4 - у3 ) / ( x4 - x3 )
        double k2;
        if(Double.compare(y4,y3)==0)
            {k2=0;}
       else
            k2 =  ( y4 - y3 ) / ( x4 - x3 );

        //parallel
        if (Double.compare (k1,k2)==0){return null;}
        //normal
        else {
            //b1 = у1 - k1 * x1
            //b2 = у3  - k2 * x3

            double b1 = y1 - k1*x1;
            double b2 = y3 - k2 *x3;

            //x = ( b2 - b1 ) / ( k1 -  k2 )
            //y = k1 x + b1.
            double x = (b2 - b1)/(k1 - k2);
            double y =k1 * x + b1;

            if (    (
                        (((x>=x1)&&(x<=x2))||((x>=x2)&&(x<=x1)))
                        && ((x>=x3)&&(x<=x4))||((x>=x4)&&(x<=x3))
                    )
                    &&
                    (
                        ((y>=y1)&&(y<=y2))||((y>=y2)&&(y<=y1))
                        && ((y>=y3)&&(y<=y4))||((y>=y4)&&(y<=y3))
                    )
                    )
            {return new Point(x, y);}
            else return null;
        }


    }

}
