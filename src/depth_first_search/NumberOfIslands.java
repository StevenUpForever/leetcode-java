package depth_first_search;

public class NumberOfIslands {

    //TAG: Google
    //TAG: Facebook
    //TAG: Microsoft
    //TAG: Amazon
    //TAG: DFS
    //TAG: BFS
    //Difficulty: medium

    /**
     * 200. Number of Islands
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
     * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
     * You may assume all four edges of the grid are all surrounded by water.

     Example 1:

     11110
     11010
     11000
     00000
     Answer: 1

     Example 2:

     11000
     11000
     00100
     00011
     Answer: 3
     */

    /**
     * Solution 1:
     * DFS, when find a '1', try to set all adjacent '1' to '0' (including itself)
     *
     * Then when met another 1, it must be another island
     *
     * Time: O(2mn)
     * Space: O(1)
     */

    public int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
    }

}
