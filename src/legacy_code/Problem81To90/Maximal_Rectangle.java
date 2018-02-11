package legacy_code.Problem81To90;

import java.util.*;

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
     * Time: O(m3n) = O(mn)
     * Space: O(2m) + O(n) = O(2m + n)
     */

    public int maximalRectangleS3(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int area = 0;
        //Three 1D dp array to store the left max length, right max length and max height
        int[] left = new int[col], right = new int[col], height = new int[col];
        for (int i = 0; i < row; i++) {
            int leftIndex = 0, rightIndex = col;
            for (int j = 0; j < col; j++) {
                //Initial right dp value to col (which is the length not index)
                if (i == 0) right[j] = col;
                if (matrix[i][j] == '0') {
                    //Left dp value increase only when pre are all 1s
                    left[j] = 0;
                    //Otherwise set left index to the index exclusive the current left 0 value
                    leftIndex = j + 1;
                    height[j] = 0;
                } else {
                    //if is 1, left dp value increase depends on current index, or height increase by 1
                    left[j] = Math.max(left[j], leftIndex);
                    height[j]++;
                }
            }
            //right dp value initially is col, and set from end to start as same rule as left dp value
            for (int j = col - 1; j >= 0; j--) {
                if (matrix[i][j] == '0') {
                    right[j] = col;
                    rightIndex = j;
                } else {
                    right[j] = Math.min(right[j], rightIndex);
                }
            }
            //At last for the current dp values in left, right and height, calculate the max rectangle area
            for (int j = 0; j < col; j++) area = Math.max(area, (right[j] - left[j]) * height[j]);
        }
        return area;
    }

    /**
     * Solution 4: Largest Rectangle in Histogram problem
     * For loop the matrix, for every row, we update the max area of height[0...j]
     * Only consider about this row, it's a Largest Rectangle in Histogram problem (legacy_code.Problem81To90), extra step is need to maintain a height array
     *
     * Time: O(mn) * O(2n) (largest rectangle problem time) = O(mn^2)
     * Space: O(2n) height dp array + stack for each loop = O(n)
     */

    public int maximalRectangleS4(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int[] height = new int[col];
        int area = 0;
        for (int i = 0; i < row; i++) {
            //Main Largest Rectangle in Histogram logic
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j <= col; j++) {
                if (j < col) {
                    //Height steps same as solution 1
                    if (matrix[i][j] == '0') height[j] = 0;
                    else height[j]++;
                }
                int curHeight = j == col ? 0 : height[j];
                if (stack.isEmpty() || height[stack.peek()] < curHeight) stack.push(j);
                else {
                    int pop = stack.pop();
                    area = Math.max(area, (stack.isEmpty() ? j : j - 1 - stack.peek()) * height[pop]);
                    //If need to reset j, also need to reset height[j]
                    if (j < col) height[j]--;
                    j--;
                }
            }
        }
        return area;
    }

}
