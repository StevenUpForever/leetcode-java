package breadth_first_search.min_steps;

import java.util.LinkedList;
import java.util.Queue;

public class Q994RottingOranges {

    //Difficulty: Easy
    //TAG: BFS

    /**
     * 994. Rotting Oranges
     * In a given grid, each cell can have one of three values:
     *
     * the value 0 representing an empty cell;
     * the value 1 representing a fresh orange;
     * the value 2 representing a rotten orange.
     * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [[2,1,1],[1,1,0],[0,1,1]]
     * Output: 4
     * Example 2:
     *
     * Input: [[2,1,1],[0,1,1],[1,0,1]]
     * Output: -1
     * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
     * Example 3:
     *
     * Input: [[0,2]]
     * Output: 0
     * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
     *
     *
     * Note:
     *
     * 1 <= grid.length <= 10
     * 1 <= grid[0].length <= 10
     * grid[i][j] is only 0, 1, or 2.
     */

    /*
    Solution:
    BFS fill all 1 with 2, finally find if all 1 are filled otherwise return steps

    Time: O(n)
    Space: O(1)
     */

    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int row = grid.length, col = grid[0].length,
                countNeeded = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) countNeeded++;
                if (grid[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                for (int[] dir: dirs) {
                    int x = poll[0] + dir[0], y = poll[1] + dir[1];
                    if (x < 0 || x >= row || y < 0 || y >= col ||
                            grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    countNeeded--;
                    queue.offer(new int[]{x, y});
                }
            }
            step++;
        }
        return countNeeded == 0 ? Math.max(0, step - 1) : -1;
    }

}
