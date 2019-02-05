package array.n_sum;

import java.util.Arrays;

public class Q16ThreeSumClosest {

    //TAG: array
    //Difficulty: Medium

    /**
     * 16. 3Sum Closest
     * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

     For example, given array S = {-1 2 1 -4}, and target = 1.

     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
     */

    /**
     * Solution:
     * Similar as 3sum, difference is keep a global min sum when equal to number immediately return this number
     *
     * Time: O(nlogn(sort) + n^2) = O(n^2) no matter how middle and right index move, they iterative all numbers right of left index once, it's O(n - 1) + O(n - 2) + ... + O(1) = O(n^2)
     * Space: O(1)
     */

    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        Arrays.sort(nums);
        //Define a global result and target difference
        int i = 0, res = 0, diff = Integer.MAX_VALUE;
        while (i < nums.length - 2) {
            int m = i + 1, j = nums.length - 1;
            //Define a local nums[i] to compare to skip all duplicated nums[i]
            int temp1 = nums[i];
            while (m < j) {
                int temp2 = nums[m], temp3 = nums[j];
                int curSum = temp1 + temp2 + temp3;
                if (diff > Math.abs(target - curSum)) {
                    diff = Math.abs(target - curSum);
                    res = curSum;
                }
                if (curSum < target) while (m < j && nums[m] == temp2) m++;
                else if (curSum > target) while (j > m && nums[j] == temp3) j--;
                    //If equal to target, the best answer is the target, no need to consider result of triplets even there's other number equal to this
                else return target;
            }
            while (i < nums.length - 2 && nums[i] == temp1) i++;
        }
        return res;
    }

}
