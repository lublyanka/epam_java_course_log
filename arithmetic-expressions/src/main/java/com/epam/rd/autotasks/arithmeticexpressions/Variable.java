package com.epam.rd.autotasks.arithmeticexpressions;

public class Variable implements Expression {
    private final String name;
    private int value;

    public Variable(String name, int value) {
        if (name != null)
            this.name = name;
        else throw new IllegalArgumentException("name can't be null");
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public String toExpressionString() {
        return name;
    }
}
