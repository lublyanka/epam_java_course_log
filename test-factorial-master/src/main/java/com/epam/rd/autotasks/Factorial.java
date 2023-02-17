package com.epam.rd.autotasks;

import java.math.BigInteger;

public class Factorial {
    public String factorial(String n) {
        BigInteger factorialCounted;
        try {
            if (n == null || n.matches("^-.*"))
                throw new IllegalArgumentException();
            factorialCounted = new BigInteger(n);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
        return String.valueOf(factorial(factorialCounted));
    }

    private BigInteger factorial(BigInteger n) {
        //The value of 0! is 1, according to the convention for an empty product.
        if (n.equals(BigInteger.ZERO))
            return BigInteger.ONE;
        else return n.multiply(factorial(n.subtract(BigInteger.ONE)));
    }
}
