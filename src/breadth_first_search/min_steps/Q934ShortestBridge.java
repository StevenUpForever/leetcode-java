package breadth_first_search.min_steps;

import java.util.LinkedList;
import java.util.Queue;

public class Q934ShortestBridge {

    //Difficulty: medium
    //TAG: Uber
    //TAG: bfs
    //TAG: dfs

    /**
     * 934. Shortest Bridge
     * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
     *
     * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
     *
     * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
     *
     *
     *
     * Example 1:
     *
     * Input: [[0,1],[1,0]]
     * Output: 1
     * Example 2:
     *
     * Input: [[0,1,0],[0,0,0],[0,0,1]]
     * Output: 2
     * Example 3:
     *
     * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
     * Output: 1
     *
     *
     * Note:
     *
     * 1 <= A.length = A[0].length <= 100
     * A[i][j] == 0 or A[i][j] == 1
     */

    /*
    Solution:
    1. find any island by dfs add all 1s to the queue
    2. use queue do bfs, find outer scope by step + 1, (fixed by current queue size), until find first another
    unvisited 1, then current step is the min step

    Time: O(mn + mn) = O(mn)
    Space: O(mn)
     */

    public int shortestBridge(int[][] A) {
        if (A == null || A.length == 0) return 0;
        int row = A.length, col = A[0].length;
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        outer: for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 1) {
                    dfs(A, visited, queue, i, j, directions);
                    break outer;
                }
            }
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                for (int[] dir: directions) {
                    int x = poll[0] + dir[0];
                    int y = poll[1] + dir[1];
                    if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y]) continue;
                    if (A[x][y] == 1) return step;
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
            step++;
        }
        return -1;
    }

    private void dfs(int[][] A, boolean[][] visited, Queue<int[]> queue, int x, int y, int[][] directions) {
        if (x < 0 || x >= A.length || y < 0 || y >= A[0].length || A[x][y] == 0 || visited[x][y]) return;
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        for (int[] dir: directions) {
            dfs(A, visited, queue, x + dir[0], y + dir[1], directions);
        }
    }

}
