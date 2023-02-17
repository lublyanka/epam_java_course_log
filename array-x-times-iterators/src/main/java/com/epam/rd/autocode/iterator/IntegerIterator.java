package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class IntegerIterator implements Iterator<Integer> {
    private final int size;
    private final int[] array;
    private final int times;
    private int index;
    private int callCount;

    public IntegerIterator(int[] array, int times) {
        this.array = array;
        this.times = times;
        size = array.length;
        index = 0;
        callCount = 0;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public Integer next() {
        if (index >= size)
            throw new NoSuchElementException();
        callCount++;
        Integer result = array[index];
        if (callCount >= times) {
            index++;
            callCount = 0;
        }
        return result;
    }
}
