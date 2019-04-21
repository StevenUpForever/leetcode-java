package breadth_first_search.min_steps;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q1030MatrixCellsInDistanceOrder {

    //Difficulty: easy
    //TAG: BFS

    /**
     * 1030. Matrix Cells in Distance Order
     * We are given a matrix with R rows and C columns has cells with integer coordinates (r, c), where 0 <= r < R and 0 <= c < C.
     *
     * Additionally, we are given a cell in that matrix with coordinates (r0, c0).
     *
     * Return the coordinates of all cells in the matrix, sorted by their distance from (r0, c0) from smallest distance to largest distance.  Here, the distance between two cells (r1, c1) and (r2, c2) is the Manhattan distance, |r1 - r2| + |c1 - c2|.  (You may return the answer in any order that satisfies this condition.)
     *
     *
     *
     * Example 1:
     *
     * Input: R = 1, C = 2, r0 = 0, c0 = 0
     * Output: [[0,0],[0,1]]
     * Explanation: The distances from (r0, c0) to other cells are: [0,1]
     * Example 2:
     *
     * Input: R = 2, C = 2, r0 = 0, c0 = 1
     * Output: [[0,1],[0,0],[1,1],[1,0]]
     * Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2]
     * The answer [[0,1],[1,1],[0,0],[1,0]] would also be accepted as correct.
     * Example 3:
     *
     * Input: R = 2, C = 3, r0 = 1, c0 = 2
     * Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     * Explanation: The distances from (r0, c0) to other cells are: [0,1,1,2,2,3]
     * There are other answers that would also be accepted as correct, such as [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]].
     *
     *
     * Note:
     *
     * 1 <= R <= 100
     * 1 <= C <= 100
     * 0 <= r0 < R
     * 0 <= c0 < C
     */

    /*
    Solution 1:

    Use priority queue add all points

    Time: O(mnlog(mn))
    Space: (mn)
     */

    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        if (r0 < 0 || r0 >= R || c0 < 0 || c0 >= C) return new int[0][0];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return distance(o1[0], o1[1], r0, c0) - distance(o2[0], o2[1], r0, c0);
            }
        });
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                pq.offer(new int[]{i, j});
            }
        }
        int[][] res = new int[R * C][2];
        int index = 0;
        while (!pq.isEmpty()) res[index++] = pq.poll();
        return res;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /*
    Solution 2:
    bfs

    Time: O(mn)
    Space: O(mn)
     */

    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        if (r0 < 0 || r0 >= R || c0 < 0 || c0 >= C) return new int[0][0];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r0, c0});
        int index = 0;
        int[][] res = new int[R * C][2];
        boolean[][] visited = new boolean[R][C];
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x < 0 || x >= R || y < 0 || y >= C || visited[x][y]) continue;
            res[index++] = poll;
            visited[x][y] = true;
            queue.offer(new int[]{x + 1, y});
            queue.offer(new int[]{x - 1, y});
            queue.offer(new int[]{x, y + 1});
            queue.offer(new int[]{x, y - 1});
        }
        return res;
    }

}
