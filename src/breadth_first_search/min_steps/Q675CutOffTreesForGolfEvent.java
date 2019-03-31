package breadth_first_search.min_steps;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Q675CutOffTreesForGolfEvent {

    //Difficulty: hard
    //TAG: Amazon
    //TAG: bfs

    /**
     * 675. Cut Off Trees for Golf Event
     * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
     *
     * 0 represents the obstacle can't be reached.
     * 1 represents the ground can be walked through.
     * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
     *
     *
     * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. And after cutting, the original place has the tree will become a grass (value 1).
     *
     * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
     *
     * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
     *
     * Example 1:
     *
     * Input:
     * [
     *  [1,2,3],
     *  [0,0,4],
     *  [7,6,5]
     * ]
     * Output: 6
     *
     *
     * Example 2:
     *
     * Input:
     * [
     *  [1,2,3],
     *  [0,0,0],
     *  [7,6,5]
     * ]
     * Output: -1
     *
     *
     * Example 3:
     *
     * Input:
     * [
     *  [2,3,4],
     *  [0,0,5],
     *  [8,7,6]
     * ]
     * Output: 6
     * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
     *
     *
     * Hint: size of the given matrix will not exceed 50x50.
     */

    /*
    Solution:
    min steps should use dp or bfs, due to we could walk to 4 directions, then bfs is the right way

    save all values > 1 means tree height in a min priority queue, due to need do from smallest to largest

    every time when bfs find current value is the pq.peek(), reset the queue start new bfs from current node
    otherwise do bfs and add steps each loop

    Time: O(n + klogk) k is all values > 1
    Space: O(n) assume 1 node and n will be larger than k
     */

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) return -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (List<Integer> list: forest) {
            for (int num: list) {
                if (num > 1) pq.offer(num);
            }
        }
        System.out.println(pq.peek());
        int row = forest.size(), col = forest.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        if (forest.get(0).get(0) > 0) {
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;
        }
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty() && !pq.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                if (pq.isEmpty()) return step;
                int[] poll = queue.poll();
                int x = poll[0], y = poll[1], val = forest.get(x).get(y);
                if (val == pq.peek()) {
                    pq.poll();
                    queue.clear();
                    size = 0;
                    step--;
                    queue.offer(new int[]{x, y});
                    visited = new boolean[row][col];
                    visited[x][y] = true;
                    System.out.println("x: " + x + " y : " + y);
                    System.out.println("step: " + step);
                } else {
                    for (int[] dir: dirs) {
                        int m = x + dir[0], n = y + dir[1];
                        if (m >= 0 && m < row && n >= 0 && n < col &&
                                !visited[m][n] && forest.get(m).get(n) != 0) {
                            visited[m][n] = true;
                            queue.offer(new int[]{m, n});
                        }
                    }
                }
            }
            step++;
        }
        return pq.isEmpty() ? step : -1;
    }

}
