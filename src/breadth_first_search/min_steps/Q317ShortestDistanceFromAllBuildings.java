package breadth_first_search.min_steps;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Q317ShortestDistanceFromAllBuildings {

    //Difficulty: hard
    //TAG: Facebook
    //TAG: BFS

    /**
     * 317. Shortest Distance from All Buildings
     * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
     *
     * Each 0 marks an empty land which you can pass by freely.
     * Each 1 marks a building which you cannot pass through.
     * Each 2 marks an obstacle which you cannot pass through.
     * Example:
     *
     * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
     *
     * 1 - 0 - 2 - 0 - 1
     * |   |   |   |   |
     * 0 - 0 - 0 - 0 - 0
     * |   |   |   |   |
     * 0 - 0 - 1 - 0 - 0
     *
     * Output: 7
     *
     * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
     *              the point (1,2) is an ideal empty land to build a house, as the total
     *              travel distance of 3+3+1=7 is minimal. So return 7.
     * Note:
     * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
     */

    /*
    Solution:

    BFS
    1. loop the grid, count all building (1), and set 0 to 3, in order to separate steps count with 1 or 2
    2. loop the grid, do bfs and add steps to current grid when it's >= 3,
    count how many buildings can reach to current cell
    3. count the grid, min = min(min, totalSteps[i][j]) only if steps >= 3 and buildings here can reach == count

    Time: O(2mn + mn * buildings) = O(buildings * mn)
    Space: O(mn) for visited and map

    Improvement:

    1. merge first loop to second, can bfs and do set to 3 && count++ at the same time
    2. when bfs and find current steps > max, then don't need bfs from here any more
     */

    public int shortestDistance(int[][] grid) {
        if (grid == null) return -1;
        int count = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) count++;
                else if (grid[i][j] == 0) grid[i][j] = 3;
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) bfs(grid, i, j, map);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                String key = i + "," + j;
                if (grid[i][j] >= 3 && map.containsKey(key) && map.get(key) == count) min = Math.min(min, grid[i][j]);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min - 3;
    }

    private void bfs(int[][] grid, int i, int j, Map<String, Integer> map) {
        int step = 1;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                for (int[] dir: dirs) {
                    int x = poll[0] + dir[0], y = poll[1] + dir[1];
                    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length ||
                            grid[x][y] == 1 || grid[x][y] == 2 ||
                            visited[x][y]) continue;
                    visited[x][y] = true;
                    grid[x][y] += step;
                    String key = x + "," + y;
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    queue.offer(new int[]{x, y});
                }
            }
            step++;
        }
    }

}
