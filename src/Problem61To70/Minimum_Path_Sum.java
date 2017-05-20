package Problem61To70;

public class Minimum_Path_Sum {

    /**
     * 64. Minimum Path Sum
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

     Note: You can only move either down or right at any point in time.
     */

    /**
     * Solution 1: 2D DP
     * Similar as Unique_Paths, at this time, it's not count the paths from left and up, it's choosing the min cost of path from left and up
     * Base case: M[0][0] = grid[0][0]
     * Induction rule:
     *      If i == 0, M[i][j] = M[i][j - 1] + grid[i][j]
     *      If j == 0, M[i][j] = M[i - 1][j] + grid[i][j]
     *      Else M[i][j] = min(M[i - 1][j], M[i][j - 1]) + grid[i][j]
     *
     * Time: O(mn)
     * Space: O(mn)
     */

    public int minPathSumS1(int[][] grid) {
        if (grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    /**
     * Solution 2: 1D DP
     * This time, have a 1D dp array int[] dp, elements in dp represent diff meaning than solution 1, which dp[i] represent the min value from top row to current row, base case is when i = 0, the first col do not calculate, just add, should be the min value
     * Base case: M[0] = grid[0][0]
     * Induction rule:
     *      if i == 0, M[j] = M[j - 1] + grid[i][j]
     *      if j == 0, M[0] = M[0] + grid[i][j]
     *      else M[j] = min(M[j], M[j - 1]) + grid[i][j], M[j] already represent the min value of [i][j]
     *
     * Time: O(mn)
     * Space: O(n) n is the col
     */

    public int minPathSumS2(int[][] grid) {
        if (grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[] dp = new int[col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) dp[j] = grid[i][j];
                else if (i == 0) dp[j] = dp[j - 1] + grid[i][j];
                else if (j == 0) dp[0] = dp[0] + grid[i][j];
                else dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[col - 1];
    }

}
