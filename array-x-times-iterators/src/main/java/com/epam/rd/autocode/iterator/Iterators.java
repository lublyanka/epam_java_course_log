package com.epam.rd.autocode.iterator;

import java.util.Iterator;

class Iterators {

    public static Iterator<Integer> intArrayXTimesIterator(int[] array, int times) {
        if(times <=0)
            throw new IllegalArgumentException();
        return new IntegerIterator(array, times);
    }

}


