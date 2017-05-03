package Problem21To30;

public class Remove_Element {

    /**
     * 27. Remove Element
     * Given an array and a value, remove all instances of that value in place and return the new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     The order of elements can be changed. It doesn't matter what you leave beyond the new length.

     Example:
     Given input array nums = [3,2,2,3], val = 3

     Your function should return length = 2, with the first two elements of nums being 2.
     */

    /**
     * solution:
     * Similar as 'move 0s to the end' problem, use slow pointer and fast pointer,fast move forward by 1 every time, slow moves only when fast is not the val, slow represent all numbers not equal to val (NOT include slow)
     *      ****** Why NOT include slow, because we always know about val, slow just need to occupy the place going to replace with non-val, so not include slow (Different with remove dup but leave one problem) ******
     * ignore set all rest of value to val of right of slow as move 0s problem
     */

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) nums[slow++] = nums[fast];
        }
        return slow;
    }


}
