package com.epam.rd.autotasks.segments;

class Point {
    private double x;
    private double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {

        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Point or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Point)) {
            return false;
        }

        // typecast o to Point so that we can compare data members
        Point c = (Point) o;

        // Compare the data members and return accordingly
        return Double.compare(x, c.getX()) == 0
                && Double.compare(y, c.getY()) == 0;
    }
}
