package array.pre_sum;

import java.util.HashMap;
import java.util.Map;

public class Q560SubarraySumEqualsK {

    //Difficulty: Medium
    //TAG: Snap
    //TAG: presum
    //TAG: array

    /**
     * 560. Subarray Sum Equals K
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
     *
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     * Note:
     * The length of the array is in range [1, 20,000].
     * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     */

    /*
    Solution:
    Use presum array to quick filter subarray sum, use two sum to quick find designated subarray sum

    Time: O(n)
    Space: O(n)
     */

    public int subarraySum(int[] nums, int k) {
        if (nums == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] += i == 0 ? 0 : nums[i - 1];
            //First check if presum[i] itself is == k
            if (nums[i] == k) count++;
            count += map.getOrDefault(nums[i] - k, 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return count;
    }

}
