package Problem71To80;

public class Rm_Dup_from_Sorted_Array_II {

    /**
     * 80. Remove Duplicates from Sorted Array II
     * Follow up for "Remove Duplicates":
     What if duplicates are allowed at most twice?

     For example,
     Given sorted array nums = [1,1,1,2,2,3],

     Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
     */

    /**
     * Solution:
     * Similar as 26. remove dup (Problem 21 to 30), but at this time, we need another counter to count if could reach to 2 elements, and do same slow/fast steps
     */

    public int removeDuplicates(int[] nums) {
        int slow = 0, count = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] == nums[slow] && count < 2) {
                count++;
                /*
                Even if fast == slow, need to copy ++slow with fast, so that will make sure all number will continue appear twice, slow and fast may not next by 1, and slow already copied one number, so need to copy another one from fast to slow + 1
                for example, 11113345, when slow = 1, fast = 3, next step is 1131345 which slow = 2, fast = 4, at this time, we need to copy the 2nd 3 to the right of first 3, otherwise, it became 113435 which the length will be wrong
                 */
                nums[++slow] = nums[fast];
            } else if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
                count = 1;
            }
        }
        //Slow is the last index of valid substring (include slow) so slow + 1
        return slow + 1;
    }

}
