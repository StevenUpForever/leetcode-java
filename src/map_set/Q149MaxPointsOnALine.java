package map_set;

import public_class.Point;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q149MaxPointsOnALine {

    //Difficulty: Hard
    //TAG: LinkedIn
    //TAG: map

    /**
     * 149. Max Points on a Line
     * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
     *
     * Example 1:
     *
     * Input: [[1,1],[2,2],[3,3]]
     * Output: 3
     * Explanation:
     * ^
     * |
     * |        o
     * |     o
     * |  o
     * +------------->
     * 0  1  2  3  4
     * Example 2:
     *
     * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * Output: 4
     * Explanation:
     * ^
     * |
     * |  o
     * |     o        o
     * |        o
     * |  o        o
     * +------------------->
     * 0  1  2  3  4  5  6
     */

    /*
    Solution:
    Max length always start from one point, and all after points have the same slope with the start point

    for for loop, every inner loop means find max length of same line points start from points[i]

    Update max by current points[i] loop

    Time: O(n^2)
    Space: O(n)
     */

    public int maxPoints(Point[] points) {
        if (points == null) return 0;
        int res = 0;
        Arrays.sort(points, (o1, o2) -> o1.x - o2.x);
        for (int i = 0; i < points.length; i++) {
            int dup = 0, max = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].x - points[i].x,
                        y = points[j].y - points[i].y;
                if (x == 0 && y == 0) {
                    dup++;
                    continue;
                }
                int gcd = gcd(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                String key = x + "/" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            res = Math.max(res, max + dup + 1);
        }
        return res;
    }

    private int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }

}
