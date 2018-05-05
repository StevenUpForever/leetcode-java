package depth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {

    //TAG: Google
    //TAG: Facebook
    //TAG: Uber
    //TAG: dfs
    //TAG: bfs
    //difficulty: medium

    /**
     * 286. Walls and Gates
     * You are given a m x n 2D grid initialized with these three possible values.
     *
     * -1 - A wall or an obstacle.
     * 0 - A gate.
     * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     *
     * For example, given the 2D grid:
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     *   0  -1 INF INF
     * After running your function, the 2D grid should be:
     *   3  -1   0   1
     *   2   2   1  -1
     *   1  -1   2  -1
     *   0  -1   3   4
     */

    /*
    Solution 1: dfs
    dfs to 4 directions from all 0, if met -1 or out of bound or the current value is updated and smaller than kept value
    return
    otherwise keep dfs

    Time: for each 0, may not go over all matrix, due to some value may smaller than what going to update
    so, it could be some constant which between 0...1 O(amn) + O(bmn) + .. = O(mn)
    Space: O(1)
     */

    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                //Only met gate (0) do dfs
                if (rooms[i][j] == 0) dfs(rooms, i, j, 0);
            }
        }
    }

    //Could use visit matrix which has O(n^2) space and save when rooms[x][y] == val dfs
    private void dfs(int[][] rooms, int x, int y, int val) {
        if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length
                || rooms[x][y] == -1 || rooms[x][y] < val) return;
        rooms[x][y] = val;
        dfs(rooms, x - 1, y, val + 1);
        dfs(rooms, x + 1, y, val + 1);
        dfs(rooms, x, y - 1, val + 1);
        dfs(rooms, x, y + 1, val + 1);
    }

    /*
    Solution 2: bfs
    bfs from all 0s, so each step, 0 will trying to update nearest value without check if rooms[x][y] < val and update

    Time: O(mn) every grid visited exactly once
    Space: O(mn) if all values are 0
     */

    public void wallsAndGates2(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) queue.add(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] top = queue.remove();
            int row = top[0], col = top[1];
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
        }
    }

}
