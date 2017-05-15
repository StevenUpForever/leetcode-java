package Problem41To50;

public class Rotate_Image {

    /**
     * 48. Rotate Image
     * You are given an n x n 2D matrix representing an image.

     Rotate the image by 90 degrees (clockwise).

     Follow up:
     Could you do this in-place?
     */

    /**
     *  Approach:
     * Rotate the matrix from outer round to inner round recursively, graph as show:
     * AE1111
     * 12222B
     * 12332F
     * H23321
     * D22221
     * 111GC1
     * Could implemented in recursion way or iterative way
     */

     /**
      * Solution 1: Recursion
     * Base case: when the length of current round is 1 or less, stop and return
     * Recursion rule:
     *      1. Record a offset which add to start point (0,0) to make a real start point (1,1), (2,2) etc..., also cal the len of current round by len - offset
     *      2. keep a temp first element, rotate the numbers one by one as A -> B -> C -> D, E -> F -> G -> H
     *
     * Time: O(mn) m represent the rows of the matrix, n represent the cols of the matrix
     * Space: O(1)
     */


    public void rotateS1(int[][] matrix) {
        rotateHelper(matrix, 0);
    }

    private void rotateHelper(int[][] matrix, int offset) {
        if (offset >= matrix.length/2) return;
        for (int i = offset; i <= matrix.length - 2 - offset; i++) {
            int temp = matrix[offset][i];
            matrix[offset][i] = matrix[matrix.length - i - 1][offset];
            matrix[matrix.length - i - 1][offset] = matrix[matrix.length - offset - 1][matrix.length - i - 1];
            matrix[matrix.length - offset - 1][matrix.length - i - 1] = matrix[i][matrix.length - offset - 1];
            matrix[i][matrix.length - offset - 1] = temp;
        }
        rotateHelper(matrix, offset + 1);
    }

    /**
     * Solution 2: Iterative
     * Same idea as recursion, have a outer loop to loop the offset
     *
     * Time: O(mn)
     * Space: O(1)
     */

    public void rotateS2(int[][] matrix) {
        for (int offset = 0; offset < matrix.length/2; offset++) {
            for (int i = offset; i <= matrix.length - 2 - offset; i++) {
                int temp = matrix[offset][i];
                matrix[offset][i] = matrix[matrix.length - i - 1][offset];
                matrix[matrix.length - i - 1][offset] = matrix[matrix.length - offset - 1][matrix.length - i - 1];
                matrix[matrix.length - offset - 1][matrix.length - i - 1] = matrix[i][matrix.length - offset - 1];
                matrix[i][matrix.length - offset - 1] = temp;
            }
        }
    }

}
