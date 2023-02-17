package com.epam.rd.autotasks;

import java.util.Arrays;

class CycleSwap {
    static void cycleSwap(int[] array) {
        int [] copyArray = Arrays.copyOf(array, array.length);
        for (int i = 0; i < copyArray.length; i++) {
            if (i == copyArray.length-1)
                array[0]=copyArray[i];
            else
            array[i+1] = copyArray[i];
        }
    }

    static void cycleSwap(int[] array, int shift) {
        if(array != null && array.length !=0)
        for(int i=0 ; i< shift % array.length ; i++) // we take a modulus in case the value of k is >= N
        {
            cycleSwap(array) ;
        }
    }
}
