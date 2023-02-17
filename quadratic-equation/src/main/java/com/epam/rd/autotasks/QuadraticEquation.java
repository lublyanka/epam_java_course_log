package com.epam.rd.autotasks;

import java.util.Locale;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class QuadraticEquation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double x1, x2;

        //D = b2 − 4ac

        double d = b * b - 4 * a * c;

        //roots
        if (d < 0){
            System.out.println("no roots");}
        else if (d == 0) {
            x1 = (-b + sqrt(d)) / (2 * a);
            System.out.println(x1);
            }
        else {
            x1 = (-b + sqrt(d)) / (2 * a);
            x2 = (-b - sqrt(d)) / (2 * a);
            System.out.println(x1 + " " + x2);
        }
    }
}