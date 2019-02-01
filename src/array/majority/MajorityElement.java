package array.majority;

public class MajorityElement {

    //Difficulty: Easy
    //TAG: Google
    //TAG: array

    /**
     * 169. Majority Element
     * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
     *
     * You may assume that the array is non-empty and the majority element always exist in the array.
     *
     * Example 1:
     *
     * Input: [3,2,3]
     * Output: 3
     * Example 2:
     *
     * Input: [2,2,1,1,1,2,2]
     * Output: 2
     */

    /*
    Solution:
    Save a num as nums[0] assume it's the one > 1/2, then when met the same count++
    when different count--, when count == 0 reassign num = nums[i]

    finally the num must be the one > 1/2, if there exist one

    Time: O(n)
    Space: O(1)
     */

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int num = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == num) count++;
            else count--;
            if (count == 0) {
                num = nums[i];
                count = 1;
            }
        }
        return num;
    }

}
