package com.epam.rd.autotasks.matrices;

import java.util.Arrays;

public class MultiplyMatrix {
    public static int[][] multiply(int[][] matrix1, int[][] matrix2) {

        // for i = 1 to a do
        //	for j = 1 to c do
        //		R[i, j] =0
        //		for k = 1 to b do
        //			R[i, j] = R[i, j] + G[i, k]*H[k, j]
        //		end for k
        //	end for j
        //end for i
        int[][] resultArray = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                resultArray[i][j] = 0;
                for (int k = 0; k < matrix2.length; k++) {
                    resultArray[i][j] = resultArray[i][j] + matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        int[][] a = {
                {0, 123},
                {45, 0},
                {3, 56}};

        int[][] b = {
                {65, 0, 253},
                {0, 61, 6}};

        int[][] result = multiply(a, b);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }
}
