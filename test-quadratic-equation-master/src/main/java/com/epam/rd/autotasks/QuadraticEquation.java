package com.epam.rd.autotasks;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public String solve(double a, double b, double c) {

        if (a == 0)
            throw new IllegalArgumentException();

        double x1, x2;
        String result;

        //D = b2 − 4ac
        double d = b * b - 4 * a * c;

        //roots
        if (d < 0) {
            result = "no roots";
            System.out.println(result);
        } else {
            double firstRoot = (-b + sqrt(d)) / (2 * a);

            if (d == 0) {
                x1 = firstRoot;
                result = String.valueOf(x1);
                System.out.println(result);
            } else {
                x1 = firstRoot;
                x2 = (-b - sqrt(d)) / (2 * a);
                result = x1 + " " + x2;
            }
        }
        return result;
    }
}