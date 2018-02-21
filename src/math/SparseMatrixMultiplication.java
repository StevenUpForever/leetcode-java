package math;

public class SparseMatrixMultiplication {

    //TAG: LinkedIn
    //TAG: math
    //Difficulty: Medium

    /**
     * 311. Sparse Matrix Multiplication
     * Given two sparse matrices A and B, return the result of AB.

     You may assume that A's column number is equal to B's row number.

     Example:

     A = [
     [ 1, 0, 0],
     [-1, 0, 3]
     ]

     B = [
     [ 7, 0, 0 ],
     [ 0, 0, 0 ],
     [ 0, 0, 1 ]
     ]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
     AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
     | 0 0 1 |
     */

    /**
     * Solution:
     * Do a matrix multiplication,rules under https://en.wikipedia.org/wiki/Matrix_multiplication
     * Diff is due to every number in A need to multiple by all the row in B, so if A[i][j] is 0, skip the inner loop due to all numbers sum will be 0 too, Sparse Matrix will have most of 0 values which will save the time
     *
     * m represent row of A, n represent col of A, i represent row of B, j represent col of B
     * Time: O(mnj)
     * Space: O(mj)
     */

    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < B[0].length; k++) {
                        res[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }
        return res;
    }

}
