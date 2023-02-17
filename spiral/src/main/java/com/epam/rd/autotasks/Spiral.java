package com.epam.rd.autotasks;

class Spiral {
    static int[][] spiral(int rows, int columns) {
        int[][] array = new int[rows][columns];
        int currentNumber = 1;

        // Initial boundaries
        int topRow = 0, bottomRow = rows - 1, leftFront = 0, rightFront = columns - 1;

        // Directions of travelling
        int currentDirection = 1;

        //while top don't meet the bottom and left don't meet right we are not in the center
        while (topRow <= bottomRow && leftFront <= rightFront) {

            // first direction travelling from left to right, that is where we are starting
            if (currentDirection == 1) {
                for (int i = leftFront; i <= rightFront; ++i) {
                    array[topRow][i] = currentNumber++;
                }
                //when we finish the line no need to re-visit it
                ++topRow;
                // when we finish the line time to change the direction to vertical, to the right side
                currentDirection = 2;
            }
            // second direction travelling from top to bottom
            else if (currentDirection == 2) {
                for (int i = topRow; i <= bottomRow; ++i) {
                    array[i][rightFront] = currentNumber++;
                }
                //when we finish no need to re-visit it
                --rightFront;
                // when we finish time to change the direction to horizontal reverse
                currentDirection = 3;
            } // third direction travelling from right to left (reverse mode)
            else if (currentDirection == 3) {
                for (int i = rightFront; i >= leftFront; --i) {
                    array[bottomRow][i] = currentNumber++;
                }
                //when we finish no need to re-visit it
                --bottomRow;
                currentDirection = 4;
            } // forth direction moving from bottom to up (reverse mode)
            else if (currentDirection == 4) {
                for (int i = bottomRow; i >= topRow; --i) {
                    array[i][leftFront] = currentNumber++;
                }
                //when we finish no need to re-visit it
                ++leftFront;
                // when we finish time to change the direction once again to the original one
                currentDirection = 1;
            }
        }
        return array;
    }
}
