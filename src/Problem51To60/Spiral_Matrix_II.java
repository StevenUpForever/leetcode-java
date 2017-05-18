package Problem51To60;

import java.util.ArrayList;
import java.util.List;

public class Spiral_Matrix_II {

    /**
     * 59. Spiral Matrix II
     * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

     For example,
     Given n = 3,

     You should return the following matrix:
     [
     [ 1, 2, 3 ],
     [ 8, 9, 4 ],
     [ 7, 6, 5 ]
     ]
     */

    /**
     * Solution:
     * Similar as Sprial Matrix(Problem51To60), but fill the matrix with a globalNum++
     *
     * Time: O(n^2) totally n^2 numbers in matrix
     * Space: O(n^2)
     */

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int x = 0, y = 0;
        int lenX = n, lenY = n;
        while (x < lenX && y < lenY) {
            //Left to right
            for (int i = y; i < lenY; i++) matrix[x][i] = num++;
            x++;
            //top to down
            //The start position is just correct after x++
            for (int i = x; i < lenX; i++) matrix[i][lenY - 1] = num++;
            lenY--;
            //Right to left
            //At the same m >= current y, need to make sure the base condition x < lenX
            for (int i = lenY - 1; i < lenX && i >= y; i--) matrix[lenX - 1][i] = num++;
            lenX--;
            //down to top
            for (int i = lenX - 1; i < lenY && i >= x; i--) matrix[i][y] = num++;
            y++;
        }
        return matrix;
    }

}
