package com.epam.rd.autotasks.matrices;

import java.util.Arrays;

public class TransposeMatrix {
    public static int[][] transpose(int[][] matrix) {

        int[][] resultArray = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //SQUARE
                //int temp = matrix[i][j];
                //resultArray[i][j] = matrix[j][i];
                //resultArray[j][i]=temp;
                resultArray[j][i] = matrix[i][j];
            }
        }
        return resultArray;
    }

    public static void main(String[] args) {

        System.out.println("Test your code here!\n");

        // Get a result of your code

        int[][] matrix = {
         {1, 2},
        {7, -13} };
        //int[][] matrix = {{-4, -65, 56}, {78, -13, 32}};

        int[][] result = transpose(matrix);
        System.out.println(Arrays.deepToString(result).replace("],", "]\n"));
    }

}
