package depth_first_search.matrix;

public class Q329LongestIncreasingPathInAMatrix {

    /**
     * 329. Longest Increasing Path in a Matrix
     *  Given an integer matrix, find the length of the longest increasing path.
     *
     * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
     *
     * Example 1:
     *
     * Input: nums =
     * [
     *   [9,9,4],
     *   [6,6,8],
     *   [2,1,1]
     * ]
     * Output: 4
     * Explanation: The longest increasing path is [1, 2, 6, 9].
     * Example 2:
     *
     * Input: nums =
     * [
     *   [3,4,5],
     *   [3,2,6],
     *   [2,2,1]
     * ]
     * Output: 4
     * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
     */

    /*
    Solution:

     */

    private int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int max = 0;
        int[][] maxMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, Integer.MIN_VALUE, maxMatrix));
            }
        }
        return max;
    }

    private int dfs(int[][] matrix, int i, int j, int pre, int[][] maxMatrix) {
        int row = matrix.length, col = matrix[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || matrix[i][j] <= pre) return 0;
        if (maxMatrix[i][j] != 0) return maxMatrix[i][j];
        int maxLen = 1;
        for (int[] dir: directions) {
            maxLen = Math.max(maxLen, 1 + dfs(matrix, i + dir[0], j + dir[1], matrix[i][j], maxMatrix));
        }
        maxMatrix[i][j] = maxLen;
        return maxLen;
    }

}
