package array.sort;

public class Q283MoveZeroes {

    //TAG: Facebook
    //TAG: Apple
    //TAG: array
    //Difficulty: Easy

    /**
     283. Move Zeroes
     Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

     For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

     Note:
     You must do this in-place without making a copy of the array.
     Minimize the total number of operations.
     */

    /*
     * Solution:
     1 slow pointer and 1 fast pointer go at the same direction, this will not change the original relative order, remember for loop from
     the first element not the second one, make sure slow pointer always point to the next of known non-zero element, in case when set all rest
     elements to 0 will also set the last known non-zero element

     Time: O(n)
     Space: O(1)
     */

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int n = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) nums[n++] = nums[i];
        }
        for (int j = n; j < nums.length; ++j) {
            nums[j] = 0;
        }
    }

}
