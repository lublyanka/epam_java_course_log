package com.epam.rd.autotasks.arrays;

import java.util.Arrays;

public class SumOfPrevious {

    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 0, 4, 6, 10, 15, 25};

        System.out.println(Arrays.toString(getSumCheckArray(array)));
    }

    public static boolean[] getSumCheckArray(int[] array) {
        boolean[] resultArray = new boolean[array.length];

        //First two elements of the boolean array are always false.
        resultArray[0] = false;
        resultArray[1] = false;

        //check if a corresponding element is a sum of two previous elements in given array.
        for (int i = 2; i < array.length; i++) {
            int sum = array[i - 1] + array[i - 2];
            if (Integer.compare(array[i], sum) == 0)
                resultArray[i] = true;
            else resultArray[i] = false;
        }

        return resultArray;
        //throw new UnsupportedOperationException();
    }
}
