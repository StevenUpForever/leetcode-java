package Problem61To70;

public class Unique_Paths {

    /**
     * 62. Unique Paths
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

     The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

     How many possible unique paths are there?


     Above is a 3 x 7 grid. How many possible unique paths are there?

     Note: m and n will be at most 100.
     */

    /**
     * Solution 1: DP
     * As for a cell (x, y), the max paths could be the max path until (x - 1, y) and (x, y - 1)
     * Base case: M[0][0] = 1
     * Induction rule: Because one cell can only go right or down, which also means, to reach the cell, need come from left or up, so the total paths is paths from left + paths from up, M[i][j] = M[i - 1][j] + M[i][j - 1]
     *
     * Time: O(mn)
     * Space: O(mn) dp matrix
     */

    public int uniquePathsS1(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * Solution 2: math (idea from LeetCode)
     * make down or right as a pivot, say down as pivot, all steps as steps, so the max paths is the combination C(steps, down)
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int uniquePathsS2(int m, int n) {
        //All steps need to go, except the first cell
        int allSteps = n + m - 2;
        //All down steps need to go, except the first cell
        int down = m - 1;
        double res = 1;
        // C(allSteps, down) = n! / k!(n - down)!
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        for (int i = 1; i <= down; i++)
            res = res * (allSteps - down + i) / i;
        return (int)res;
    }

}
