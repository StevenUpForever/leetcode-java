package dynamic_programming.one_d_dp;

import java.util.Arrays;

public class Q45JumpGameII {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: dp
    //TAG: Greedy

    /**
     * 45. Jump Game II
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Your goal is to reach the last index in the minimum number of jumps.
     *
     * Example:
     *
     * Input: [2,3,1,1,4]
     * Output: 2
     * Explanation: The minimum number of jumps to reach the last index is 2.
     *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Note:
     *
     * You can assume that you can always reach the last index.
     */

    /*
    Solution:
    Use int[] dp array, dp[i] represent min steps reach from index 0 to i
    base case is last index dp[len - 1] = 0 means last index is for sure to reach at 0 step
    so we have several conditions:
    1. when current could reach to the end, dp[i] = 1
    2. when current nums[i] == 0, cannot reach and dp[i] = int.max
    3. loop from i + 1 to nums[i] + i, find min steps, and dp[i] = min + 1
    return dp[0]

    Time: O(n^2)
    Space: O(n)
    */

    public int jump(int[] nums) {
        if (nums == null) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= nums.length - 1) dp[i] = 1;
            else if (nums[i] != 0) {
                int min = dp[i];
                for (int j = i + 1; j <= nums[i] + i; j++) {
                    min = Math.min(min, dp[j]);
                }
                dp[i] = min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
            }
        }
        return dp[0] == Integer.MAX_VALUE ? 0 : dp[0];
    }

    /*
    Solution 2:
    greedy, try to go as most as possible and update mostEnd
    set init currentEnd when loop i reach currentEnd, step++, and update currentEnd to mostEnd
    means from i or < i, could be sure reach mostEnd, and when reach there, step++

    Time: O(n)
    Space: O(1)
     */

    public int jump2(int[] nums) {
        int jumps = 0, curEnd = 0, curMostEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curMostEnd = Math.max(curMostEnd, nums[i] + i);
            if (i == curEnd) {
                jumps++;
                curEnd = curMostEnd;
            }
        }
        return jumps;
    }

}
