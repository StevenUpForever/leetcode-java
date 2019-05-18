package array.priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q973KClosestPointsToOrigin {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: priority queue

    /**
     * 973. K Closest Points to Origin
     * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
     *
     * (Here, the distance between two points on a plane is the Euclidean distance.)
     *
     * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
     *
     *
     *
     * Example 1:
     *
     * Input: points = [[1,3],[-2,2]], K = 1
     * Output: [[-2,2]]
     * Explanation:
     * The distance between (1, 3) and the origin is sqrt(10).
     * The distance between (-2, 2) and the origin is sqrt(8).
     * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
     * Example 2:
     *
     * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
     * Output: [[3,3],[-2,4]]
     * (The answer [[-2,4],[3,3]] would also be accepted.)
     *
     *
     * Note:
     *
     * 1 <= K <= points.length <= 10000
     * -10000 < points[i][0] < 10000
     * -10000 < points[i][1] < 10000
     */

    /*
    Solution:

    Use priority queue filter closest points to origin

    Time: O(klogk)
    Space: O(k)
     */

    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        if (points == null || points.length == 0 || points[0].length == 0 || K < 1) {
            return res;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return distance(o2) - distance(o1);
            }
        });
        for (int[] point: points) {

            if (queue.size() < K) {
                queue.offer(point);
            } else {
                int[] peek = queue.peek();
                if (distance(point) < distance(peek)) {
                    queue.poll();
                    queue.offer(point);
                }
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            res[index] = queue.poll();
            index++;
        }
        return res;
    }

    private int distance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

}
