package depth_first_search;

public class Q695MaxAreaOfIsland {

    //Difficulty: medium
    //TAG: Uber
    //TAG: DFS

    /**
     * 695. Max Area of Island
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
     *
     * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
     *
     * Example 1:
     *
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
     * Example 2:
     *
     * [[0,0,0,0,0,0,0,0]]
     * Given the above grid, return 0.
     * Note: The length of each dimension in the given grid does not exceed 50.
     */

    /*
    Solution:
    loop the matrix, when find 1 and not visited, do dfs from current cell and calculate the area

    after dfs, update max with local area

    Time: O(mn)
    Space: O(mn)
     */

    private int curArea = 0;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int area = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    maxAreaOfIslandHelper(grid, visited, i, j);
                    area = Math.max(area, curArea);
                    curArea = 0;
                }
            }
        }
        return area;
    }

    private void maxAreaOfIslandHelper(int[][] grid, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= grid.length ||
        y < 0 || y >= grid[0].length ||
        grid[x][y] == 0 || visited[x][y]) return;
        visited[x][y] = true;
        curArea++;
        maxAreaOfIslandHelper(grid, visited, x + 1, y);
        maxAreaOfIslandHelper(grid, visited, x - 1, y);
        maxAreaOfIslandHelper(grid, visited, x, y + 1);
        maxAreaOfIslandHelper(grid, visited, x, y - 1);
    }

    /*
    Solution 2:
    If could modify the matrix, don't need visited matrix anymore, set matrix[i][j] = 0 represent visited

    Time: O(mn)
    Space: O(mn)
    */

    public int maxAreaOfIsland2(int[][] grid) {
        int max_area = 0;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] == 1)max_area = Math.max(max_area, AreaOfIsland(grid, i, j));
        return max_area;
    }

    private int AreaOfIsland(int[][] grid, int i, int j){
        if( i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1){
            grid[i][j] = 0;
            return 1 + AreaOfIsland(grid, i+1, j) + AreaOfIsland(grid, i-1, j) +
                    AreaOfIsland(grid, i, j-1) + AreaOfIsland(grid, i, j+1);
        }
        return 0;
    }

}
