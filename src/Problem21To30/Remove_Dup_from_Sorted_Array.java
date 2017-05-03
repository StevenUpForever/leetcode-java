package Problem21To30;

public class Remove_Dup_from_Sorted_Array {

    /**
     * 26. Remove Duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     For example,
     Given input array nums = [1,1,2],

     Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
     */

    /**
     * solution:
     * use slow and fast index, which fast will go advanced by 1 every time, the number left of slow (include slow) represent the non-repeat numbers
     * for loop the nums array once:
     *      1. if nums[slow] != nums[fast], slow++ and fast++
     *      2. if nums[slow] ==  nums[fast], only fast++ until nums[slow] != nums[fast] and then nums[++slow] = nums[fast]
     *              1. Corner case of nums[++slow] = nums[fast], if at the beginning slow == fast == 0, and then fast move by 1, slow != fast nums[++slow] = nums[fast] will make slow == fast and then set nums[slow] = nums[fast]
     */

    public int removeDuplicates(int[] nums) {
        //Need to consider about when length == 0, because return value is slow + 1 even if slow == 0 and length == 0
        if (nums == null || nums.length == 0) return 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            //Make sure slow increase first and set value, so all numbers include slow will be the non-repeat values
            if (nums[fast] != nums[slow]) nums[++slow] = nums[fast];
        }
        return slow + 1;
    }


}
