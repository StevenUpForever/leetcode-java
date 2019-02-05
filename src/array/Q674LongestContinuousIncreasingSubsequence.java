package array;

public class Q674LongestContinuousIncreasingSubsequence {

    //TAG: Facebook
    //TAG: array

    /**
     * 674. Longest Continuous Increasing Subsequence
     * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
     *
     * Example 1:
     * Input: [1,3,5,4,7]
     * Output: 3
     * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
     * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
     * Example 2:
     * Input: [2,2,2,2,2]
     * Output: 1
     * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
     * Note: Length of the array will not exceed 10,000.
     */

    /**
     * Solution:
     * First ask continuous increasing subsequence is 0,1,2,3 or increasing subarray
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int cur = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) cur++;
            else {
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        return Math.max(max, cur);
    }

}
