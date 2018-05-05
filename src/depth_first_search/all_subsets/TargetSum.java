package depth_first_search.all_subsets;

public class TargetSum {

    //TAG: Google
    //TAG: Facebook
    //TAG: dfs
    //TAG: dp
    //Difficulty: meidum

    /**
     * 494. Target Sum
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
     *
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     *
     * Example 1:
     * Input: nums is [1, 1, 1, 1, 1], S is 3.
     * Output: 5
     * Explanation:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * There are 5 ways to assign symbols to make the sum of nums be target 3.
     * Note:
     * The length of the given array is positive and will not exceed 20.
     * The sum of elements in the given array will not exceed 1000.
     * Your output answer is guaranteed to be fitted in a 32-bit integer.
     */

    /*
    Solution:
    it's all subsets problem, which for each num there's two condition add to result which is num and -num

    Time: O(2^n)
    Space: O(n)
     */

    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        findTargetSumWaysHelper(nums, 0, 0, S);
        return count;
    }

    private void findTargetSumWaysHelper(int[] nums, int cur, int index, int S) {
        if (index == nums.length) {
            if (cur == S) count++;
            return;
        }
        findTargetSumWaysHelper(nums, cur + nums[index], index + 1, S);
        findTargetSumWaysHelper(nums, cur - nums[index], index + 1, S);
    }

    /*
    Solution 2:
    Many calculation is duplicated, e.g. 1 + 2 + 3 and -1 + 2 + 3, 2 + 3 part is duplicated
    So just record current pos sum is enough
    https://leetcode.com/problems/target-sum/discuss/97334/Java-(15-ms)-C++-(3-ms)-O(ns)-iterative-DP-solution-using-subset-sum-with-explanation
     */

    public int findTargetSumWays2(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1);
    }

    private int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }

}
