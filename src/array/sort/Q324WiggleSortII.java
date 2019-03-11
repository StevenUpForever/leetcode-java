package array.sort;

import java.util.Arrays;

public class Q324WiggleSortII {

    //Difficulty: medium
    //TAG: Airbnb
    //TAG: array

    /**
     * 324. Wiggle Sort II
     * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
     *
     * Example 1:
     *
     * Input: nums = [1, 5, 1, 1, 6, 4]
     * Output: One possible answer is [1, 4, 1, 5, 1, 6].
     * Example 2:
     *
     * Input: nums = [1, 3, 2, 2, 3, 1]
     * Output: One possible answer is [2, 3, 1, 3, 1, 2].
     * Note:
     * You may assume all input has valid answer.
     *
     * Follow Up:
     * Can you do it in O(n) time and/or in-place with O(1) extra space?
     */

    /*
    Solution:
    Due to if we swap from start and end, the middle repeat number will not be swapped and thus not fit the rule
    we need shuffle the sorted nums by mid to 0 and end to mid
    Time: O(n)
    Space: O(n)
     */

    public void wiggleSort(int[] nums) {
        if (nums == null) return;
        int[] temp = Arrays.copyOf(nums, nums.length);
        Arrays.sort(temp);
        int m = (nums.length + 1)/2 - 1, n = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i % 2 == 0 ? temp[m--] : temp[n--];
        }
    }

}
