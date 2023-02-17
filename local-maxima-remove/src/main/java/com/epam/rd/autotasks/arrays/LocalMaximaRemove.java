package com.epam.rd.autotasks.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class LocalMaximaRemove {

    public static void main(String[] args) {
        int[] array = //new int[]{-18, 21, 3, 6, 7, 65};
                new int[]{18, 1, 3, 6, 7, -5};

        System.out.println(Arrays.toString(removeLocalMaxima(array)));
    }

    public static int[] removeLocalMaxima(int[] array){

        ArrayList <Integer> newArray = new ArrayList<>();
        if (array[0] <= array[1])
            newArray.add(array[0]);
        for (int i = 1; i < array.length-1; i++) {
            if (array[i] > array[i-1] && array[i] > array[i+1])
            {}
            else
                newArray.add(array[i]);
        }
        if (array[array.length-1] <= array[array.length-2])
            newArray.add(array[array.length-1]);
        int[] resultArray = new int[newArray.size()];

        for (int i = 0; i < newArray.size(); i++)
            resultArray[i] = newArray.get(i);

        return resultArray;
        //throw new UnsupportedOperationException();
    }
}
