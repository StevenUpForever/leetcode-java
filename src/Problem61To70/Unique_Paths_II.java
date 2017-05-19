package Problem61To70;

public class Unique_Paths_II {

    /**
     * 63. Unique Paths II
     * Follow up for "Unique Paths":

     Now consider if some obstacles are added to the grids. How many unique paths would there be?

     An obstacle and empty space is marked as 1 and 0 respectively in the grid.

     For example,
     There is one obstacle in the middle of a 3x3 grid as illustrated below.

     [
     [0,0,0],
     [0,1,0],
     [0,0,0]
     ]
     The total number of unique paths is 2.

     Note: m and n will be at most 100.
     */

    /**
     * Solution: DP Smilar as Unique_Path (Problem61To70)
     *  * As for a cell (x, y), the max paths could be the max path until (x - 1, y) and (x, y - 1)
     * Base case: M[0][0] = 1
     * Induction rule: Because one cell can only go right or down, which also means, to reach the cell, need come from left or up, so the total paths is paths from left + paths from up
     *      *** 1. if matrix[i][j] == 1, M[i][j] = 0, not possible to reach ***
     *      *** 2. if i == 0 || j == 0, need to check the previous left value (when i == 0) or upper value (when j == 0), then if previous dp value is 0, current value is 0 ***
     *      *** 2. else M[i][j] = M[i - 1][j] + M[i][j - 1] ***
     *
     * Time: O(mn)
     * Space: O(mn) dp matrix
     */

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) return 0;
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //If obstacleGrid value is 1, have obstacle, dp value is 0
                if (obstacleGrid[i][j] == 0) {
                    //Path to (0, 0) must be 1, no matter obstacleGrid is 0 or not, due to start is this point
                    if (i == 0 && j == 0) dp[i][j] = 1;
                    //Except the (0, 0), when i == 0 or j == 0, only if previous dp path is 1, then could goes there(only one path to this cell), otherwise is 0
                    else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] == 1 ? 1 : 0;
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] == 1 ? 1 : 0;
                    } else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

}
