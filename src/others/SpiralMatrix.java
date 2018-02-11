package others;

import java.util.*;

public class SpiralMatrix {

    //TAG: Uber
    //TAG: Others

    /**
     * 54. Spiral Matrix
     * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

     For example,
     Given the following matrix:

     [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
     ]
     You should return [1,2,3,6,9,8,7,4,5].
     */

    /**
     * Solution:
     * Similar as Rotate_Image (legacy_code.Problem41To50) problem
     * append values from outside round to inside round, keep the x offset and y offset to indicate the coordinate and length
     * Diff is this is a m * n matrix not n * n, so the base case is when x < lenX || y < lenY
     *
     * Time: O(mn) every element go over once
     * Space: O(mn) the arrayList to contain m * n numbers
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList();
        //Base case avoid below lenY = matrix[0].length error
        if (matrix.length == 0) return result;
        int x = 0, y = 0;
        int lenX = matrix.length, lenY = matrix[0].length;
        while (x < lenX && y < lenY) {
            //Left to right
            for (int i = y; i < lenY; i++) result.add(matrix[x][i]);
            x++;
            //top to down
            //The start position is just correct after x++
            for (int j = x; j < lenX; j++) result.add(matrix[j][lenY - 1]);
            lenY--;
            //Right to left
            //At the same m >= current y, need to make sure the base condition x < lenX
            for (int m = lenY - 1; x < lenX && m >= y; m--) result.add(matrix[lenX - 1][m]);
            lenX--;
            //down to top
            for (int n = lenX - 1; y < lenY && n >= x; n--) result.add(matrix[n][y]);
            y++;
        }
        return result;
    }

}
