package dynamic_programming.two_d_dp;

public class UniquePaths {

    //Difficulty: Easy
    //TAG: DP

    /**
     * Unique paths
     * A robot is located at the top-left corner of a m x n grid(where m > 0 and n > 0).

     The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid.

     How many possible unique paths are there?



     Start



     End
     */

    /**
     * Solution:
     * DP, 因为只能向右向下移动，所以当前点也是从左边or上边来的，dp[i][j] = left + top,
     * 注意要initial dp[0][0] == 1，否则就会都是0
     *
     * Time: O(mn)
     * Space: O(mn)
     */

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int left = j == 0 ? 0 : dp[i][j - 1];
                    int top = i == 0 ? 0 : dp[i - 1][j];
                    dp[i][j] = left + top;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
