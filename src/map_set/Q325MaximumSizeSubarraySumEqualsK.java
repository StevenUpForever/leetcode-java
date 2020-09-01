package map_set;

import java.util.HashMap;
import java.util.Map;

public class Q325MaximumSizeSubarraySumEqualsK {

    //TAG: Facebook
    //TAG: map_set
    //TAG: n sum
    //TAG: array
    //Difficulty: Medium

    /**
     * 325. Maximum Size Subarray Sum Equals k
     * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

     Note:
     The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

     Example 1:
     Given nums = [1, -1, 5, -2, 3], k = 3,
     return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

     Example 2:
     Given nums = [-2, -1, 2, 1], k = 1,
     return 2. (because the subarray [-1, 2] sums to 1 and is the longest)

     Follow Up:
     Can you do it in O(n) time?
     */

    /*
     * Solution:
     * Use pre sum array
     * *** similar to two sum ***
     * Use map record the pre sum
     * add num to sum,
     *  1. if find sum == k, then max == current i
     *  2. if map.containsKey(sum - i) max = max(max, i - map.get(sum - k))
     *  else record pre sum in map
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0, max = 0;
        //key is pre sum, value is end index
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //i is index + 1 is size
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }

}
