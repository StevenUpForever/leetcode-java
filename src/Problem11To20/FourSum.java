package Problem11To20;

import java.util.*;

public class FourSum {

    /**
     * 18. 4Sum
     * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

     Note: The solution set must not contain duplicate quadruplets.

     For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.

     A solution set is:
     [
     [-1,  0, 0, 1],
     [-2, -1, 1, 2],
     [-2,  0, 0, 2]
     ]
     */

    /**
     * Solution:
     * Similar as 3 sum, after sort the array, treat middle left and right index pair as the third number of 3sum (middle index)
     *
     * Time: O(nlogn(sort) + n^3) = O(n^3)
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) return res;
        Arrays.sort(nums);
        int left = 0;
        while (left < nums.length - 3) {
            int mid = left + 1, right = nums.length - 1;
            //Define a local nums[i] to compare to skip all duplicated nums[i]
            int tempLeft = nums[left];
            while (mid + 1 < right) {
                int tempRight = nums[right];
                //3rd loop start from left + 1 to right - 1, treat as the middle index of 3 sum
                int midLeft = mid;
                int midRight = right - 1;
                while (midLeft < midRight) {
                    int tempMidL = nums[midLeft], tempMidR = nums[midRight];
                    int curSum = tempLeft + tempMidL + tempMidR + tempRight;
                    if (curSum < target) while (midLeft < midRight && nums[midLeft] == tempMidL) midLeft++;
                    else if (curSum > target) while (midLeft < midRight && nums[midRight] == tempMidR) midRight--;
                    else {
                        res.add(Arrays.asList(tempLeft, tempMidL, tempMidR, tempRight));
                        while (midLeft < midRight && nums[midLeft] == tempMidL) midLeft++;
                        while (midLeft < midRight && nums[midRight] == tempMidR) midRight--;
                    }
                }
                while (mid + 1 < right && nums[right] == tempRight) right--;
            }
            while (left < nums.length - 3 && nums[left] == tempLeft) left++;
        }
        return res;
    }

}
