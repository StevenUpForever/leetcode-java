package dynamic_programming.two_d_dp;

import java.util.HashMap;
import java.util.Map;

public class Q1039MinimumScoreTriangulationOfPolygon {

    //Difficulty: medium
    //TAG: dp

    /**
     * 1039. Minimum Score Triangulation of Polygon
     *Given N, consider a convex N-sided polygon with vertices labelled A[0], A[i], ..., A[N-1] in clockwise order.
     *
     * Suppose you triangulate the polygon into N-2 triangles.  For each triangle, the value of that triangle is the product of the labels of the vertices, and the total score of the triangulation is the sum of these values over all N-2 triangles in the triangulation.
     *
     * Return the smallest possible total score that you can achieve with some triangulation of the polygon.
     *
     *
     *
     * Example 1:
     *
     * Input: [1,2,3]
     * Output: 6
     * Explanation: The polygon is already triangulated, and the score of the only triangle is 6.
     * Example 2:
     *
     *
     *
     * Input: [3,7,4,5]
     * Output: 144
     * Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
     * Example 3:
     *
     * Input: [1,3,1,4,1,5]
     * Output: 13
     * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.
     *
     *
     * Note:
     *
     * 3 <= A.length <= 50
     * 1 <= A[i] <= 100
     */

    /*
    Solution 1:
    two d dp, dp[i][j] represent when make i and j as a side, min values

    dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
    divide the polygon by i-j side, then loop from i to j by k, means make triangle if i-j-k be pivot
    min value = min left or pivot + min right of pivot + pivot

    Time: O(n^3)
    Space: O(n^2)
     */

    public int minScoreTriangulation(int[] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int j = 2; j < len; j++) {
            for (int i = j - 2; i >= 0; i--) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][len - 1];
    }

    /*
    Solution 2:

    memorized dfs, similar to dfs

    Time: O(n^3)
    Space: O(n)
     */

    public int minScoreTriangulation2(int[] A) {
        return dfs(A, 0, A.length - 1, new HashMap<>());
    }

    private int dfs(int[] A, int start, int end, Map<String, Integer> map) {
        if (end == start + 2) {
            return A[start] * A[start + 1] * A[end];
        } else if (end < start + 2) return 0;
        String key = start + "," + end;
        if (map.containsKey(key)) return map.get(key);
        int min = A[start] * A[end];
        int prod = Integer.MAX_VALUE;
        for (int i = start + 1; i < end; i++) {
            int temp = min * A[i];
            int first = dfs(A, start, i, map), last = dfs(A, i, end, map);
            map.put(start + "," + i, first);
            map.put(i + "," + end, last);
            prod = Math.min(prod, temp + first + last);
        }
        return prod;
    }

}
