package legacy_code.Problem71To80;

public class Set_Matrix_Zeroes {

    /**
     * 73. Set Matrix Zeroes
     * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

     click to show follow up.

     Follow up:
     Did you use extra space?
     A straight forward solution using O(mn) space is probably a bad idea.
     A simple improvement uses O(m + n) space, but still not the best solution.
     Could you devise a constant space solution?
     */

    /**
     * Approach:
     * key point is, if a number is set to 0, but originally not 0, then this number should be 0, but entire row and column should NOT be 0s
     */

    /**
     * Solution 1:
     * Use a matrix helper to indicate to store all same numbers in matrix
     * For for loop all numbers in matrix, if find a 0, and helper[i][j] is also 0, set entire row and column from this cell to 0
     *
     * Time: O(mn) for for loop * O(mn) set row and column to 0 = O((mn)^2)
     * Space: O(mn) for a helper matrix
     */

    /**
     * Solution 2:
     * Based on idea from solution 1, we don't need to set row and col all 0's when we just find a right 0, so that we may set duplicated row and columns again and again
     * And we don't need a O(mn) helper matrix due to we just need tags for rows and columns
     *
     * So we could delay the step to set row and column to 0s, and have one row array and a column array
     *      1. we need tags to indicate which row and column to set to 0s, first for for loop, if number is 0, we set row[i] and column[j] to 1
     *      2. the second for for loop, for each number, if any of it's tag (row[i] || column[j]) is 1, we set this number to 0
     *
     * Time: O(2mn) = O(mn)
     * Space: O(m + n)
     */

    public void setZeroesS2(int[][] matrix) {
        if (matrix.length == 0) return;
        int[] row = new int[matrix.length];
        int[] col = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //Due to for for loop from top left to right bottom, and tags are tagged at left and top, so no duplicated 0s were set
                if (matrix[i][j] == 0) row[i] = col[j] = 1;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row[i] == 1 || col[j] == 1) matrix[i][j] = 0;
            }
        }
    }

    /**
     * Best/Easiest way
     * Solution 3:
     * Depends on solution 2, we could set tag to top and left number of matrix itself, like when matrix[i][j] = 0, set matrix[i][0] = matrix[0][j] = 0, but there's a problem that for row (0, j) and column (i, 0), we cannot know if current number could set to 0, because this row and column is as tag, for example:
     * 1 2 3
     * 0 4 5
     * This matrix, if not use top row and column well, 3 will set to 0, but it shouldn't
     *
     * 1. we have two boolean value to indicate if base row and column need set 0s
     * 2. just verify numbers from (1, 1) which from this, numbers not affect on base row and column
     * 3. verify base row and column to see if set to 0s
     *
     * Time: O(2mn) = O(mn)
     * Space: O(2) = O(1)
     */

    public void setZeroesS3(int[][] matrix) {
        if (matrix.length == 0) return;
        boolean row = false, col = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    //If any 0 is at the base row/col, set tag of this row/col to true
                    if (i == 0) row = true;
                    if (j == 0) col = true;
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }
        //First loop all numbers from (1, 1) if the top or left tag is 0, set to 0
        //Cannot do base row/col with all other numbers together, base row/col may set value to 0 at first which became (but should not) a top/left tag for other numbers
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        }
        if (row) {
            for (int j = 0; j < matrix[0].length; j++) matrix[0][j] = 0;
        }
        if (col) {
            for (int i = 0; i < matrix.length; i++) matrix[i][0] = 0;
        }
    }

    /**
     * Solution 4: From legacy_code
     * Depends on solution 3, which the base column will be effect of set 0 for every for loop (be effect from (0, 0))
     * But base row will only effect when first inner j loop, and then affect all other numbers from (1, 1)
     * We could move second for for loop (set main part numbers to 0) from right bottom to left top, then the base row will be changed at last, will not affect on other numbers
     * Keep the col boolean but save the row boolean
     *
     * Time: O(2mn) = O(mn)
     * Space: O(1)
     */

    public void setZeroesS4(int[][] matrix) {
        if (matrix.length == 0) return;
        boolean col = false;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) col = true;
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) matrix[i][0] = matrix[0][j] = 0;
            }
        }
        //Loop from right bottom to left top
        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
            if (col) matrix[i][0] = 0;
        }
    }

}
