package com.epam.rd.autocode.decorator;

import java.util.*;

public class Decorators {

    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return new ConcreteDecorator(sourceList);
    }

}