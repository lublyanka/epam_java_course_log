package com.epam.rd.autotasks.figures;

class Circle extends Figure {
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        if (center == null) {
            throw new IllegalArgumentException("Point center is null");
        }
        if (!(radius > 0)) {
            throw new IllegalArgumentException("Radius is 0");
        }
        this.center = center;
        this.radius = radius;
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) {
            return true;
        }
        if (figure instanceof Circle) {
            if (Math.abs(this.radius - ((Circle) figure).radius) >= 0.000001) {
                return false;
            }

            if (this.center.isTheSame(((Circle) figure).center))
                return true;
            }
        return false;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public Point leftmostPoint() {
        return new Point(center.getX() - radius, center.getY());
    }

    @Override
    public String pointsToString() {
        return center.toString();
    }

    @Override
    public String toString() {
        return Circle.class.getSimpleName() + "[" + pointsToString() + radius + "]";
    }

   /* @Override
    public String toString() {
        return super.toString().replace("]",radius + "]");
    }*/

}
