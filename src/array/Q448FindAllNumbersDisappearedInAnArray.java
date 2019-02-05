package array;

import java.util.ArrayList;
import java.util.List;

public class Q448FindAllNumbersDisappearedInAnArray {

    //TAG: Google
    //TAG: array
    //Difficulty: Easy

    /**
     * 448. Find All Numbers Disappeared in an Array
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

     Find all the elements of [1, n] inclusive that do not appear in this array.

     Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

     Example:

     Input:
     [4,3,2,7,8,2,3,1]

     Output:
     [5,6]
     */

    /**
     * Solution: From leetcode
     * Mark numbers at it's proper location (may not be the same number) to neg number to mark it as found
     * *** e.g. when found 2, mark the num[1] as neg number which num[1] may not equals to 2, but just mark as neg ***
     * then loop second time, when any number is not neg number, e.g. num[5] > 0 means 6 is not set and found, add
     * it to list
     *
     * Time: O(2n) = O(n)
     * Space: O(1)
     */

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        for (int i = 0; i < nums.length; i++) {
            /*
            Mark each number at it proper location, e.g. 1 should be at index 0, and mark it negative number
            to mark it as found
             */
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) nums[index] = -nums[index];
        }
        //Loop array again, if any number is not negative, means it's not set, and i + 1 is the missing number
        /*
        Be aware the number at number[i] is not i - 1 itself, but mark it neg number to represent found i - 1 number
         */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) list.add(i + 1);
        }
        return list;
    }

}
