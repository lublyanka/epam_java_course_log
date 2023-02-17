package com.epam.rd.autotasks.intersection;

public class Line {

    private final int k;
    private final int b;

    public Line(int k, int b) {
        this.k = k;
        this.b = b;
    }

    public Point intersection(Line other) {
        int x;
        int y;

        int del = (this.k- other.k);
        if (del == 0) return null;
        x = (other.b - this.b) / del;
        y = this.k * x + this.b;

        return new Point(x, y);
    }

}
