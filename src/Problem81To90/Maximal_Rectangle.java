package Problem81To90;

public class Maximal_Rectangle {

    /**
     * 85. Maximal Rectangle
     * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

     For example, given the following matrix:

     1 0 1 0 0
     1 0 1 1 1
     1 1 1 1 1
     1 0 0 1 0
     Return 6.
     */

    /**
     * Solution 1: Brute force
     *
     * Permutation all sub matrix and verify if this matrix is all 1
     * keep a global area and return the max area
     *
     * Time: O(m * n) (all points) * O(mn) (all sub matrix from current point) * O(mn) (verify current sub matrix) = O((mn)^6)
     * Space: O(1)
     */

    /**
     * Solution 2: Optimized Brute force
     *
     * 1. for loop all cells in matrix
     *      for each cell, try to go though as far as possible for every row from this cell, keep all numbers be 1s
     *      at last, use the min cols as cols, the furthest row - cur.i = row, have a max rectangle for current cell
     * 2. keep a global area for this
     *
     * Time: O(mn) * O(mn) = O((mn)^2)
     * Space: O(1)
     */

    /**
     * Solution 3: DP
     * Advanced one of max square of 1s, which separate the rectangle to row, and height
     * In that case, we can use 1D array to represent for current row, best length and height for current col j, which need a left[], right[] and height[], area = Max(area, (right[j] - left[j]) * height[j])
     *
     * Time: O(mn)
     * Space: O(2m) + O(n) = O(2m + n)
     */

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int area = 0;
        int[] left = new int[col], right = new int[col], height = new int[col];
//        for (int j = 0; j < col; j++) right[j] = col;
        for (int i = 0; i < row; i++) {
            int leftIndex = 0, rightIndex = col;
            for (int j = 0; j < col; j++) {
                if (i == 0) right[j] = col;
                if (matrix[i][j] == '0') {
                    left[j] = 0;
                    leftIndex = j + 1;
//                    right[j] = col;
//                    rightIndex = col - j;
                    height[j] = 0;
                } else {
                    left[j] = Math.max(left[j], leftIndex);
//                    right[j] = Math.min(right[j], rightIndex);
                    height[j]++;
                }
            }
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '0') {
                    right[j] = col;
                    rightIndex = j;
                } else {
                    right[j] = Math.min(right[j], rightIndex);
                }
            }
            for (int j = 0; j < col; j++) area = Math.max(area, (right[j] - left[j]) * height[j]);
        }
        return area;
    }

}
