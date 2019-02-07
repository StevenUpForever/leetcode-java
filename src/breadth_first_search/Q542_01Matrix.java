package breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class Q542_01Matrix {

    //Difficulty: medium
    //TAG: Uber
    //TAG: BFS

    /**
     * 542. 01 Matrix
     * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     * Example 1:
     * Input:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * Output:
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * Example 2:
     * Input:
     *
     * 0 0 0
     * 0 1 0
     * 1 1 1
     * Output:
     * 0 0 0
     * 0 1 0
     * 1 2 1
     * Note:
     * The number of elements of the given matrix will not exceed 10,000.
     * There are at least one 0 in the given matrix.
     * The cells are adjacent in only four directions: up, down, left and right.
     */

    /*
    Solution:
    BFS, use fixed size as pivot of step++, fill all non visited 1 cell to step

    Time: O(mn)
    Space: O(mn)
     */

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;
        int row = matrix.length, col = matrix[0].length;
        int[][] res = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) queue.offer(new int[]{i, j});
            }
        }
        boolean[][] visited = new boolean[row][col];
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                for (int[] dir: directions) {
                    int i = poll[0] + dir[0], j = poll[1] + dir[1];
                    if (i < 0 || i >= row || j < 0 || j >= col ||
                            matrix[i][j] == 0 || visited[i][j]) continue;
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                    res[i][j] = step;
                }
            }
            step++;
        }
        return res;
    }

}
