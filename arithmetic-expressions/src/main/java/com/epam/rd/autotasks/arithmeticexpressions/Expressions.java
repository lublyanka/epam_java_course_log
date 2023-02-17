package com.epam.rd.autotasks.arithmeticexpressions;

import java.util.StringJoiner;

public class Expressions {

    public static Variable var(String name, int value) {
        return new Variable(name, value);
    }

    public static Expression val(int value) {
        return new Expression() {

            @Override
            public int evaluate() {
                return value;
            }

            @Override
            public String toExpressionString() {
                if (value < 0)
                    return "(" + value + ")";
                else
                    return String.valueOf(value);
            }
        };
    }

    public static Expression sum(Expression... members) {
        return new Expression() {

            @Override
            public int evaluate() {
                int sum = 0;
                for (Expression exp : members) {
                    sum += exp.evaluate();
                }
                return sum;
            }

            @Override
            public String toExpressionString() {
                StringJoiner result = new StringJoiner(" + ");
                for (Expression exp : members) {
                    result.add(exp.toExpressionString());
                }
                return "(" + result + ")";
            }
        };
    }

    public static Expression product(Expression... members) {
        return new Expression() {

            @Override
            public int evaluate() {
                int product = 1;
                for (Expression exp : members) {
                    product *= exp.evaluate();
                }
                return product;
            }

            @Override
            public String toExpressionString() {
                StringJoiner result = new StringJoiner(" * ");
                for (Expression exp : members) {
                    result.add(exp.toExpressionString());
                }
                return "(" + result + ")";
            }
        };
    }

    public static Expression difference(Expression minuend, Expression subtrahend) {
        return new Expression() {

            @Override
            public int evaluate() {
                return minuend.evaluate() - subtrahend.evaluate();
            }

            @Override
            public String toExpressionString() {
                  return "(" + minuend.toExpressionString() +" - "+ subtrahend.toExpressionString() + ")";
            }
        };
    }

    public static Expression fraction(Expression dividend, Expression divisor) {
        return new Expression() {

            @Override
            public int evaluate() {
                return dividend.evaluate() / divisor.evaluate();
            }

            @Override
            public String toExpressionString() {
                return "(" + dividend.toExpressionString() +" / "+ divisor.toExpressionString() + ")";
            }
        };
    }

}
