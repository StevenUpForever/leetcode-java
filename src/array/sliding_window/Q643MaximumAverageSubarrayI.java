package array.sliding_window;

public class Q643MaximumAverageSubarrayI {

    //Difficulty: easy
    //TAG: Google
    //TAG: array
    //TAG: sliding window

    /**
     * 643. Maximum Average Subarray I
     * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.
     *
     * Example 1:
     *
     * Input: [1,12,-5,-6,50,3], k = 4
     * Output: 12.75
     * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
     *
     *
     * Note:
     *
     * 1 <= k <= n <= 30,000.
     * Elements of the given array will be in the range [-10,000, 10,000].
     */

    /*
    Solution:
    Sliding window and find max sum
    finally return max/k

    Time: O(n)
    Space: O(1)
     */

    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || k < 1 || k > nums.length) return 0.0;
        int sum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k - 1) {
                //update max once met k values, and if over k values delete the left edge
                if (i >= k) sum -= nums[i - k];
                max = Math.max(max, sum);
            }
        }
        return (double)max/k;
    }

}
