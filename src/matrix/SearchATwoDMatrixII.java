package matrix;

public class SearchATwoDMatrixII {

    //TAG: Google
    //TAG: Amazon
    //TAG: Apple
    //TAG: matrix
    //Difficulty: medium

    /**
     * Search a 2D Matrix II
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

     Integers in each row are sorted in ascending from left to right.
     Integers in each column are sorted in ascending from top to bottom.
     For example,

     Consider the following matrix:

     [
     [1,   4,  7, 11, 15],
     [2,   5,  8, 12, 19],
     [3,   6,  9, 16, 22],
     [10, 13, 14, 17, 24],
     [18, 21, 23, 26, 30]
     ]
     Given target = 5, return true.

     Given target = 20, return false.
     */

    /**
     * Solution:
     * Because sorted in row and col, so try to use binary search as much as possible
     */

    /**
     * Solution 1:
     * Check the row, which the start number <= target and end number >= target
     * Binary search these selected rows, and find the number
     *
     * Time: O(m + mlogn)
     * Space: O(m)
     */

    /**
     * Solution 2:
     * Check if target is closer to matrix[0][0] or matrix[row - 1][col - 1],
     * do a min heap or max heap BFS from left top or right down
     *
     * Time: O(mnlog)
     * Space: O(mn)
     */

    /**
     * Solution 3:
     * 1. put start number at top right corner, because need to compare large in row and small in col (reverse order is ok)
     * 2. loop check number == target
     *      1. if ==, return true
     *      2. if matrix[row][col] < target means entire row doesn't have target, and row++
     *      3. if matrix[row][col] > target entire col doesn't have target, col--
     * 3. return false due to loop haven't find target number
     *
     * Time: O(m + n)
     * Space: O(1)
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }

}
