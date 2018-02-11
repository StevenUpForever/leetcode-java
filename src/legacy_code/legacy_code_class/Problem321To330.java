package legacy_code.legacy_code_class;

import java.util.HashMap;

/**
 * Created by ChengzhiJia on 2/5/17.
 */
public class Problem321To330 {

    /*
    325. Maximum Size Subarray Sum Equals k
    Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

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
    First approach: permutation all possible subarray sum and find the largest length of result equal to k
    Time: O(n^2)
    Space: O(1)
     */
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) {
                max = Math.max(max, 1);
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    /*
    Second approach:
    use concept of sum[j...i] = sum[i] - sum[j], and two sum concept
    Use HashMap store all existed elements sum as key, index as value, check current sum equal to k or sum - k key existed
    (indicate there's part sum from some start index could have sum equal to k), put key value pair if not existed
    Time: O(n)(+ O(n) containsKey() API)
    Space: O(n)
     */
    public int maxSubArrayLen2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int max = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            //If sum == k, i should be largest index currently, and no need to watch the existed key in map, like [1, 0, 0, 0], 1
            if (sum == k) max = i + 1;
            //map.containsKey(sum - k) is key point, sum - k means part sum from uncertain start equals to k
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }

}
