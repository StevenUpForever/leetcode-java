package breadth_first_search.min_steps;

import java.util.LinkedList;
import java.util.Queue;

public class Q1162AsFarFromLandAsPossible {

    //Difficulty: medium
    //TAG: bfs
    //TAG: min steps

    /**
     * 1162. As Far from Land as Possible
     * Given an N x N grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized and return the distance.
     *
     * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
     *
     * If no land or water exists in the grid, return -1.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [[1,0,1],[0,0,0],[1,0,1]]
     * Output: 2
     * Explanation:
     * The cell (1, 1) is as far as possible from all the land with distance 2.
     * Example 2:
     *
     *
     *
     * Input: [[1,0,0],[0,0,0],[0,0,0]]
     * Output: 4
     * Explanation:
     * The cell (2, 2) is as far as possible from all the land with distance 4.
     *
     *
     * Note:
     *
     * 1 <= grid.length == grid[0].length <= 100
     * grid[i][j] is 0 or 1
     */

    /*
    Solution:

    for reach 0 cell, do bfs and find first 1 cell with steps n, update n for all 0 cells to global max
    return max

    Time: O(n^2)
    Space: O(n)
     */

    public int maxDistance(int[][] grid) {
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int row = grid.length, col = grid[0].length, max = -1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    int level = 1;
                    boolean[][] visited = new boolean[row][col];
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    outer: while (!queue.isEmpty()) {
                        int size = queue.size();
                        while (size-- > 0) {
                            int[] poll = queue.poll();
                            for (int[] dir: dirs) {
                                int x = poll[0] + dir[0];
                                int y = poll[1] + dir[1];
                                if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y])
                                    continue;
                                if (grid[x][y] == 1) {
                                    max = Math.max(max, level);
                                    break outer;
                                }
                                visited[x][y] = true;
                                queue.offer(new int[]{x, y});
                            }
                        }
                        level++;
                    }
                }
            }
        }
        return max;
    }

}
