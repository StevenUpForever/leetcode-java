package dynamic_programming.one_d_dp;

public class LongestIncreasingSubsequence {

    //Difficulty: medium
    //TAG: Apple
    //TAG: DP

    /**
     * 300. Longest Increasing Subsequence
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     *
     * Example:
     *
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     * Note:
     *
     * There may be more than one LIS combination, it is only necessary for you to return the length.
     * Your algorithm should run in O(n2) complexity.
     * Follow up: Could you improve it to O(n log n) time complexity?
     */

    /*
    Solution:
    dp
    use dp array represent max subsequency length of current nums[i]
    when loop from 0 ... len - 1, find dp[0, i - 1] find any nums[j] < nums[i], then dp[j] is possible ascending
    subsequency appended before nums[i], find max of dp[j], assign to dp[i] and dp[i++]
    then max = max(max, dp[i])

    Time: O(n^2)
    Space: O(n)
     */

    public int lengthOfLIS(int[] nums) {
        if (nums == null) return 0;
        int[] dp = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j], dp[i]);
                }
            }
            dp[i]++;
            max = Math.max(max, dp[i]);
        }
        return max;
    }



}
