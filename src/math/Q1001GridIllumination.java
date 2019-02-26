package math;

import java.util.*;

public class Q1001GridIllumination {

    //Difficulty: Hard
    //TAG: matrix

    /**
     * 1001. Grid Illumination
     * On a N x N grid of cells, each cell (x, y) with 0 <= x < N and 0 <= y < N has a lamp.
     *
     * Initially, some number of lamps are on.  lamps[i] tells us the location of the i-th lamp that is on.  Each lamp that is on illuminates every square on its x-axis, y-axis, and both diagonals (similar to a Queen in chess).
     *
     * For the i-th query queries[i] = (x, y), the answer to the query is 1 if the cell (x, y) is illuminated, else 0.
     *
     * After each query (x, y) [in the order given by queries], we turn off any lamps that are at cell (x, y) or are adjacent 8-directionally (ie., share a corner or edge with cell (x, y).)
     *
     * Return an array of answers.  Each value answer[i] should be equal to the answer of the i-th query queries[i].
     *
     *
     *
     * Example 1:
     *
     * Input: N = 5, lamps = [[0,0],[4,4]], queries = [[1,1],[1,0]]
     * Output: [1,0]
     * Explanation:
     * Before performing the first query we have both lamps [0,0] and [4,4] on.
     * The grid representing which cells are lit looks like this, where [0,0] is the top left corner, and [4,4] is the bottom right corner:
     * 1 1 1 1 1
     * 1 1 0 0 1
     * 1 0 1 0 1
     * 1 0 0 1 1
     * 1 1 1 1 1
     * Then the query at [1, 1] returns 1 because the cell is lit.  After this query, the lamp at [0, 0] turns off, and the grid now looks like this:
     * 1 0 0 0 1
     * 0 1 0 0 1
     * 0 0 1 0 1
     * 0 0 0 1 1
     * 1 1 1 1 1
     * Before performing the second query we have only the lamp [4,4] on.  Now the query at [1,0] returns 0, because the cell is no longer lit.
     *
     *
     * Note:
     *
     * 1 <= N <= 10^9
     * 0 <= lamps.length <= 20000
     * 0 <= queries.length <= 20000
     * lamps[i].length == queries[i].length == 2
     */

    /*
    Solution:

    referenced by: https://leetcode.com/problems/grid-illumination/discuss/242898/C%2B%2B-with-picture-similar-to-N-Queens

    Similar to n-queens, if we have a lamp at the same row, col, diagnose of current query, then the illuminate is 1
    we set 4 sets represent row, col, add diagnose, minus diagnose (4 diagnose can be simplified to x + y, x - y,
    e.g. x - x1 = y - y1, then x - y == x1 - y1)
    set another Map store all lamps positions

    when loop queries:
    any 4 maps contains lamp coordinator, illuminate is 1
    remove adjacent possible lamps, and decrese related map value

    Time: O(m + n) m is length of lamps, n is length of queries
    Space: O(m + n)
     */

    public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
        if (N <= 0 || lamps == null || queries == null) return new int[0];
        int[] illuminations = new int[queries.length];
        Map<Integer, Integer> xMap = new HashMap<>(), yMap = new HashMap<>(),
                diagMap1 = new HashMap<>(), diagMap2 = new HashMap<>();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] lamp : lamps) {
            xMap.put(lamp[0], xMap.getOrDefault(lamp[0], 0) + 1);
            yMap.put(lamp[1], yMap.getOrDefault(lamp[1], 0) + 1);
            diagMap1.put(lamp[0] + lamp[1], xMap.getOrDefault(lamp[0] + lamp[1], 0) + 1);
            diagMap2.put(lamp[0] - lamp[1], xMap.getOrDefault(lamp[0] - lamp[1], 0) + 1);
            map.putIfAbsent(lamp[0], new HashSet<>());
            map.get(lamp[0]).add(lamp[1]);
        }
        for (int i = 0; i < queries.length; i++) {

            int x = queries[i][0], y = queries[i][1];
            if (xMap.getOrDefault(x, 0) > 0 ||
            yMap.getOrDefault(y, 0) > 0 ||
            diagMap1.getOrDefault(x + y, 0) > 0 ||
            diagMap2.getOrDefault(x - y, 0) > 0) illuminations[i] = 1;
            for (int xx = -1; xx <= 1; xx++) {
                for (int yy = -1; yy <= 1; yy++) {
                    int newX = xx + x, newY = yy + y;
                    if (map.containsKey(newX) && map.get(newX).contains(newY)) {
                        map.get(newX).remove(newY);
                        xMap.put(newX, xMap.getOrDefault(newX, 0) - 1);
                        yMap.put(newY, yMap.getOrDefault(newY, 0) - 1);
                        diagMap1.put(newX + newY, diagMap1.getOrDefault(newX + newY, 0) - 1);
                        diagMap2.put(newX - newY, diagMap2.getOrDefault(newX - newY, 0) - 1);
                    }
                }
            }
        }
        return illuminations;
    }

    public static int consecutive(long num) {
        Map<Long, Long> map = new HashMap<>();
        map.put(0L, 1L);
        long preSum = 0, count = 0;
        for (long i = 1; i <= (num + 1)/2; i++) {
            preSum += i;
            count += map.getOrDefault(num - preSum, 0L);
            map.put(preSum, map.getOrDefault(preSum, 0L) + 1);
        }
        return (int)count;
    }

}
