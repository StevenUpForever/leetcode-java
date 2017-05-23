package Problem71To80;

public class Search_a_2D_Matrix {

    /**
     * 74. Search a 2D Matrix
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

     Integers in each row are sorted from left to right.
     The first integer of each row is greater than the last integer of the previous row.
     For example,

     Consider the following matrix:

     [
     [1,   3,  5,  7],
     [10, 11, 16, 20],
     [23, 30, 34, 50]
     ]
     Given target = 3, return true.
     */

    /**
     * Solution 1: Brute force
     * for for loop the matrix, find the right number or return false if cannot find target in matrix
     *
     * Time: O(mn)
     * Space: O(1)
     */

    /**
     * Solution 2: 2D binary search
     * The matrix is sorted from left to right, up to bottom, because every row is a larger scope than uppper row, so
     * 1. we run binary search on column side, to find which row it belongs to (left < right - 1 binary search)
     * 2. record this row, do binary search in this row, find the index or not (left <= right binary search)
     *
     * Time: O(logm + logn)
     * Space: O(1)
     */

    /**
     * Solution 3: 1D binary search
     * As for the last number of current row, and next start number in next row, it's also sorted, so we could treat the matrix as 1D sorted array, [row 1], [row 2] ... [row n]
     * So run binary search in this 1D array, left = 0, right = m * n - 1, run (left <= right) binary search
     *
     * Time: O(logmn)
     * Space: O(1)
     */

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int row = matrix.length, col = matrix[0].length;
        int left = 0, right = row * col - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            int cur = matrix[mid/col][mid%col];
            if (cur == target) return true;
            else if (cur < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

}
