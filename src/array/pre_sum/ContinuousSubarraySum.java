package array.pre_sum;

import java.util.*;

public class ContinuousSubarraySum {

    //TAG: Facebook
    //TAG: array
    //Difficulty: medium

    /**
     * 523. Continuous Subarray Sum
     * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
     *
     * Example 1:
     * Input: [23, 2, 4, 6, 7],  k=6
     * Output: True
     * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
     * Example 2:
     * Input: [23, 2, 6, 4, 7],  k=6
     * Output: True
     * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
     * Note:
     * The length of the array won't exceed 10,000.
     * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
     */

    /*
    Key point is, any subarray need be multiple of k, so two sum may not the solution for this problem, due to two sum
    will need exact k

    and array of numbers need at least 2
     */

    /*
    Solution 1:
    presum array
    add to a presum array, loop presum array at the same time, find if any subarray sum is multiple of k
    loop the presum array, not 0 to len - 1, but -1 to len - 2, due to new sum itself need check, and if use
    len - 1, sum - presum will be num[i] itself, which not at least 2 numbers

    Time: O(n^2)
    Space: O(n)
     */

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return k == 0;
        List<Integer> preSums = new ArrayList<>();
        preSums.add(nums[0]);
        //loop from 1 make sure at least 2 numbers
        for (int i = 1; i < nums.length; i++) {
            int sum = nums[i] + preSums.get(preSums.size() - 1);
            for (int j = 0; j < preSums.size(); j++) {
                //loop use -1 to size - 2
                int subArrSum = j - 1 < 0 ? sum : sum - preSums.get(j - 1);
                if ((k == 0 && subArrSum == k) || (k != 0 && subArrSum % k == 0))
                    return true;
            }
            preSums.add(sum);
        }
        return false;
    }

    /*
    Solution 2:
    From leetcode
    Key point is: in a presum array, if presum[0, i] % k == presum[0, j] % k, which i < j, that means
    presum[i, j] % k == 0, the mode value must in 0, i scope

    So we could put just mode value in a hashMap, due to not know how many multiple by k, but mode value
    is fixed
    for current sum % k if any mode value is appeared before, means we found i, j just check if j - i > 1

    Time: O(n)
    Space: O(min(n, k))
     */

    public boolean checkSubarraySum2(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //If sum is just multiple by k, just make sure it's not the first sum (contains only first number), and if k == 0
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum = sum % k;
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1) return true;
            } else map.put(sum, i);
        }
        return false;
    }

}
