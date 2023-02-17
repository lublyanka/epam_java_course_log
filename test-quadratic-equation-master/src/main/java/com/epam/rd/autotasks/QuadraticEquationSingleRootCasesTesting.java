package com.epam.rd.autotasks;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class QuadraticEquationSingleRootCasesTesting {
    private double a;
    private double b;
    private double c;
    private double expected;

    protected QuadraticEquation quadraticEquation = new QuadraticEquation();

    public QuadraticEquationSingleRootCasesTesting(double a, double b, double c, double expected) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.expected = expected;
    }

    @Test
    public void testSingleRootsCase() {
        String result = quadraticEquation.solve(a, b, c);
        try {
            double resultD = Double.parseDouble(result);
            assertEquals(expected, resultD, Double.MIN_NORMAL);
        } catch (NumberFormatException e) {
            fail();
        }
    }

    @Parameterized.Parameters
    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                {1, -2, 1, 1d},
                {8, 0, 0, 0d},
                {1, -6, 9, 3d},
                {1, 12, 36, -6d}
        });
    }
}