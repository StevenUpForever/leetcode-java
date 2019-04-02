package depth_first_search.matrix;

public class Q1031NumberOfEnclaves {

    //Difficulty: medium
    //TAG: dfs
    //TAG: bfs

    /**
     * 1031. Number of Enclaves
     * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
     *
     * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
     *
     * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
     *
     *
     *
     * Example 1:
     *
     * Input: [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
     * Output: 3
     * Explanation:
     * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
     * Example 2:
     *
     * Input: [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
     * Output: 0
     * Explanation:
     * All 1s are either on the boundary or can reach the boundary.
     *
     *
     * Note:
     *
     * 1 <= A.length <= 500
     * 1 <= A[i].length <= 500
     * 0 <= A[i][j] <= 1
     * All rows have the same size.
     */

    /*
    Solution:
    eliminate all 1s that start from 1 on boundaries, then count rest of 1s

    Time: O(mn)
    Space: O(max(m, n)) by dfs
     */

    public int numEnclaves(int[][] A) {
        if (A == null || A[0].length == 0) return 0;
        int row = A.length, col = A[0].length;
        for (int i = 0; i < row; i++) {
            dfs(A, i, 0);
            dfs(A, i, col - 1);
        }
        for (int i = 0; i < col; i++) {
            dfs(A, 0, i);
            dfs(A, row - 1, i);
        }
        int count = 0;
        for (int[] arr: A) {
            for (int num: arr) {
                if (num == 1) count++;
            }
        }
        return count;
    }

    private void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[x].length || grid[x][y] == 0) return;
        grid[x][y] = 0;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }

}
