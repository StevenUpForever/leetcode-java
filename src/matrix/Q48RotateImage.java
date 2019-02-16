package matrix;

public class Q48RotateImage {

    //Diffculty: Medium
    //TAG: Apple
    //TAG: matrix

    /**
     * 48. Rotate Image
     * You are given an n x n 2D matrix representing an image.
     *
     * Rotate the image by 90 degrees (clockwise).
     *
     * Note:
     *
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.
     *
     * Example 1:
     *
     * Given input matrix =
     * [
     *   [1,2,3],
     *   [4,5,6],
     *   [7,8,9]
     * ],
     *
     * rotate the input matrix in-place such that it becomes:
     * [
     *   [7,4,1],
     *   [8,5,2],
     *   [9,6,3]
     * ]
     * Example 2:
     *
     * Given input matrix =
     * [
     *   [ 5, 1, 9,11],
     *   [ 2, 4, 8,10],
     *   [13, 3, 6, 7],
     *   [15,14,12,16]
     * ],
     *
     * rotate the input matrix in-place such that it becomes:
     * [
     *   [15,13, 2, 5],
     *   [14, 3, 4, 1],
     *   [12, 6, 8, 9],
     *   [16, 7,10,11]
     * ]
     */

    /*
    Solution:
    Rotate the matrix by levels from outside to inside

    Time: O(n + n - 2 + n - 4 + ... + 1) = O(n^2)
    Space: O(1)
     */

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length <= 1) return;
        int len = matrix.length;
        for (int i = 0; i < len/2; i++) { //len/2是offset，最多到1/2为起点
            int left = i, right = len - 2 - i; //len - 2 - i 是这一行last second element，因为第一个要和最后一个做比较
            for (int j = left; j <= right; j++) {
                int temp = matrix[left][j];
                matrix[left][j] = matrix[len - j - 1][left];
                matrix[len - j - 1][left] = matrix[len - left - 1][len - j - 1];
                matrix[len - left - 1][len - j - 1] = matrix[j][len - 1 - left];
                matrix[j][len - 1 - left] = temp;
            }
        }
    }

}
