package array.majority;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {

    //Difficulty: medium
    //TAG: Uber
    //TAG: array

    /**
     * 229. Majority Element II
     * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
     *
     * Note: The algorithm should run in linear time and in O(1) space.
     *
     * Example 1:
     *
     * Input: [3,2,3]
     * Output: [3]
     * Example 2:
     *
     * Input: [1,1,1,3,3,2,2,2]
     * Output: [1,2]
     */

    /*
    Solution:

    basically find two possible nums > 1/2, finally count which one is real > 1/3 and add to list

    Time: O(n)
    Space: O(1)
     */

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        int num1 = nums[0], num2 = nums[0], count1 = 0, count2 = 0;
        for (int num: nums) {
            if (num == num1) count1++;
            else if (num == num2) count2++;
            else if (count1 == 0) {
                num1 = num;
                count1 = 1;
            } else if (count2 == 0) {
                num2 = num;
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int num: nums) {
            if (num == num1) count1++;
            //Need else if, since initially num1 = num2 = nums[0], if they are same finally, list may like [1, 1]
            else if (num == num2) count2++;
        }
        if (count1 > nums.length/3) list.add(num1);
        if (count2 > nums.length/3) list.add(num2);
        return list;
    }

}
