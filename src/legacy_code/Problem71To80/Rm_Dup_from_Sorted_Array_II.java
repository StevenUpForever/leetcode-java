package legacy_code.Problem71To80;

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
     * Solution 1:
     * Similar as 26. remove dup (Problem 21 to 30), but at this time, we need another counter to count if could reach to 2 elements, and do same slow/fast steps
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int removeDuplicatesS1(int[] nums) {
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

    /**
     * Solution 2:
     * we just keep same number twice and it's sorted array, so we could compare nums[fast] and nums[slow - 2], slow represent the first place need be replaced
     *      1. if slow < 2, no matter if nums[0] == nums[1] or nums[0] < nums[1], we needn't to change them
     *      2. otherwise nums[slow - 2] is the last start number in pair is smaller than nums[fast], if so, replace to slow index (slow is the place to replace)
     *      return slow
     */

    public int removeDuplicatesS2(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (slow < 2 || nums[fast] > nums[slow - 2]) nums[slow++] = nums[fast];
        }
        return slow;
    }

}
