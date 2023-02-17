package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.StringJoiner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class QuadraticEquationTwoRootsCasesTesting {
    private double a;
    private double b;
    private double c;
    private String expected;

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public QuadraticEquationTwoRootsCasesTesting(double a, double b, double c, String expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;

    }

    @Test
    public void testTwoRootsCase() {
        String[] expexctedList = expected.split(" ");
        assertTrue((new StringJoiner(" ").add(expexctedList[0]).add(expexctedList[1]).toString().equals(quadraticEquation.solve(a, b, c))
                || new StringJoiner(" ").add(expexctedList[1]).add(expexctedList[0]).toString().equals(quadraticEquation.solve(a, b, c))));
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {2, 5, -3, "0.5 -3.0"},
                {1, -3, 1, "2.618033988749895 0.3819660112501051"},
                {2, -38, 156, "13.0 6.0"},
                {-0.5, 34, 1046.5, "-23.0 91.0"}
        });
    }
}
