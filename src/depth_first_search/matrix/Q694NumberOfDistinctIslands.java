package depth_first_search.matrix;

import java.util.HashSet;
import java.util.Set;

public class Q694NumberOfDistinctIslands {

    //Difficulty: medium
    //TAG: dfs

    /**
     * 694. Number of Distinct Islands
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
     *
     * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
     *
     * Example 1:
     * 11000
     * 11000
     * 00011
     * 00011
     * Given the above grid map, return 1.
     * Example 2:
     * 11011
     * 10000
     * 00001
     * 11011
     * Given the above grid map, return 3.
     *
     * Notice that:
     * 11
     * 1
     * and
     *  1
     * 11
     * are considered different island shapes, because we do not consider reflection / rotation.
     * Note: The length of each dimension in the given grid does not exceed 50.
     */

    /*
    Solution:

    Similar to number of islands, but for each island, append a string represent the path of the island,
    same path island will be dup island, add all islands path string to set and return set.size()

    Time: O(mn)
    Space: O(mn)
     */

    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    set.add(dfs(grid, i, j, "CUR"));
                }
            }
        }
        return set.size();
    }

    private String dfs(int[][] grid, int x, int y, String dir) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) return "";
        grid[x][y] = 0;
        return "CUR:" + dir + ",L:" + dfs(grid, x, y - 1, "L") +
                ",R:" + dfs(grid, x, y + 1, "R") +
                ",U:" + dfs(grid, x - 1, y, "U") +
                ",D:" + dfs(grid, x + 1, y, "D") + ",";
    }

}
