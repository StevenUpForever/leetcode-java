package dynamic_programming.one_d_dp;

import java.util.Arrays;

public class Q746MinCostClimbingStairs {

    //Difficulty: easy
    //TAG: Amazon
    //TAG: dp

    /**
     * 746. Min Cost Climbing Stairs
     * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
     *
     * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.
     *
     * Example 1:
     * Input: cost = [10, 15, 20]
     * Output: 15
     * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
     * Example 2:
     * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * Output: 6
     * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
     * Note:
     * cost will have a length in the range [2, 1000].
     * Every cost[i] will be an integer in the range [0, 999].
     */

    /*
    Solution 1:

    dp, assign dp with length cost.length + 1,
    every time min the value if dp[i + 1] dp[i + 2] by dp[i] + cost[i]
    finally return dp[cost.length]

    Time: O(n)
    Space: O(n)
     */

    public int minCostClimbingStairs(int[] cost) {
        if (cost.length < 2) return 0;
        int[] dp = new int[cost.length + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; dp[1] = 0;
        for (int i = 0; i < cost.length; i++) {
            if (i + 1 <= cost.length) dp[i + 1] = Math.min(dp[i + 1], dp[i] + cost[i]);
            if (i + 2 <= cost.length) dp[i + 2] = Math.min(dp[i + 2], dp[i] + cost[i]);
        }
        return dp[cost.length];
    }

    /*
    Solution 2:

    do dp within cost

    Time: O(n)
    Space: O(1)
     */

    public int minCostClimbingStairs2(int[] cost) {
        for (int i = 2; i < cost.length; i++) {
            cost[i] += Math.min(cost[i-1], cost[i-2]);
        }
        return Math.min(cost[cost.length-1], cost[cost.length-2]);
    }

}
