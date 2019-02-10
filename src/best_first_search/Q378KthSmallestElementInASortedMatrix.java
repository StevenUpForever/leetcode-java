package best_first_search;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q378KthSmallestElementInASortedMatrix {

    //Difficulty: medium
    //TAG: Apple
    //TAG: best first search

    /**
     * 378. Kth Smallest Element in a Sorted Matrix
     * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
     *
     * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
     *
     * Example:
     *
     * matrix = [
     *    [ 1,  5,  9],
     *    [10, 11, 13],
     *    [12, 13, 15]
     * ],
     * k = 8,
     *
     * return 13.
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ n2.
     */

    /*
    Solution:

    Best first search
    use priority queue (min heap) filter numbers and do bfs

    Time: O(klogk)
    Space: O(k)
     */

    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<cell> queue = new PriorityQueue<>(new Comparator<cell>() {
            @Override
            public int compare(cell o1, cell o2) {
                return o1.value - o2.value;
            }
        });
        boolean[][] valid = new boolean[m][n];
        queue.offer(new cell(0, 0, matrix[0][0]));
        valid[0][0] = true;
        for (int i = 0; i < k - 1 && !queue.isEmpty(); i++) {
            cell poll = queue.poll();
            if (poll.row + 1 < m && !valid[poll.row + 1][poll.col]) {
                queue.offer(new cell(poll.row + 1, poll.col, matrix[poll.row + 1][poll.col]));
                valid[poll.row + 1][poll.col] = true;
            }
            if (poll.col + 1 < n && !valid[poll.row][poll.col + 1]) {
                queue.offer(new cell(poll.row, poll.col + 1, matrix[poll.row][poll.col + 1]));
                valid[poll.row][poll.col + 1] = true;
            }
        }
        return queue.isEmpty() ? 0 : queue.peek().value;
    }

    private class cell {
        int value;
        int row;
        int col;
        cell(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.value = val;
        }
    }

}
