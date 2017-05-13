package Problem41To50;

import java.util.HashMap;

public class First_Missing_Positive {

    /**
     * 41. First Missing Positive
     * Given an unsorted integer array, find the first missing positive integer.

     For example,
     Given [1,2,0] return 3,
     and [3,4,-1,1] return 2.

     Your algorithm should run in O(n) time and uses constant space.
     */

    /**
     * Solution: (Reference from LeetCode)
     * 1. skip all valid number & <= 0 number & larger than nums.length number, the result must within nums.length scope
     * 2. for other numbers swap it to it's index
     * 3. for loop the current array, find the first number which not the same as its index and return
     *
     * Time: O(2n) = O(n)
     * Space: O(1)
     */

    public int firstMissingPositive(int[] nums) {
        int res = 0;
        while (res < nums.length) {
            //When number not necessary or over limit to swap, move to next
            if (nums[res] == res + 1 || nums[res] <= 0 || nums[res] > nums.length) res++;
            //If number need to be swap to its right place, swap and keep at here for next loop
            else if (nums[nums[res] - 1] != nums[res]) swap(nums, res, nums[res] - 1);
            //When the right index number is the right number and current is also this right number, no need to swap (avoid infinite loop)
            else res++;
        }
        res = 0;
        while (res < nums.length && nums[res] == res + 1) res++;
        //Return the number not index, res is index so return res + 1
        return res + 1;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
