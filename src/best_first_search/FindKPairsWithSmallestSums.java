package best_first_search;

import java.util.*;

public class FindKPairsWithSmallestSums {

    //TAG: Uber
    //TAG: Best first search
    //Difficulty: Medium


    /**
     * 373. Find K Pairs with Smallest Sums
     * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

     Define a pair (u,v) which consists of one element from the first array and one element from the second array.

     Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

     Example 1:
     Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

     Return: [1,2],[1,4],[1,6]

     The first 3 pairs are returned from the sequence:
     [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     Example 2:
     Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

     Return: [1,1],[1,1]

     The first 2 pairs are returned from the sequence:
     [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     Example 3:
     Given nums1 = [1,2], nums2 = [3],  k = 3

     Return: [1,3],[2,3]

     All possible pairs are returned from the sequence:
     [1,3],[2,3]
     */

    /**
     * Solution:
     * Similar to Best First search, (e.g. find smallest/largest k element in a sorted matrix)
     * Use priority queue filter all solutions by sum(o1) - sum(o2), store index not the value to filter visited pair in
     * a 2d array
     *
     * Time: O(mnlogmn)
     * Space: O(mn)
     */

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return res;
        int i = 0, j = 0;
        boolean[][] visited = new boolean[nums1.length][nums2.length];
        PriorityQueue<Cell> queue =  new PriorityQueue<>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return nums1[o1.x] + nums2[o1.y] - nums1[o2.x] - nums2[o2.y];
            }
        });
        queue.offer(new Cell(i, j));
        while (k-- > 0 && !queue.isEmpty()) {
            Cell poll = queue.poll();
            res.add(new int[]{nums1[poll.x], nums2[poll.y]});
            if (poll.x + 1 < nums1.length && !visited[poll.x + 1][poll.y]) {
                Cell newXCell = new Cell(poll.x + 1, poll.y);
                queue.offer(newXCell);
                visited[poll.x + 1][poll.y] = true;
            }
            if (poll.y + 1 < nums2.length && !visited[poll.x][poll.y + 1]) {
                Cell newYCell = new Cell(poll.x, poll.y + 1);
                queue.offer(newYCell);
                visited[poll.x][poll.y + 1] = true;
            }
        }
        return res;
    }

    class Cell {
        int x;
        int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
