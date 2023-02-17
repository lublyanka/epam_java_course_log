package com.epam.rd.autocode.decorator;

import java.util.List;

public abstract class Decorator implements List<String>  {
    protected final List<String> sourceList;

    public Decorator(List<String> sourceList) {

        this.sourceList = sourceList;
    }

}
