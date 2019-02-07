package binary_search;

public class Q34FindFirstAndLastPositionOfElementInSortedArray {

    //Difficulty: medium
    //TAG: Uber
    //TAG: binary search

    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     * Example 2:
     *
     * Input: nums = [5,7,7,8,8,10], target = 6
     * Output: [-1,-1]
     */

    /*
    Solution:
    use binary search find first occurrence and last occurrence

    Time: O(logn)
    Space: O(1)
     */

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        res[0] = nums[right] == target ? right : -1;
        left = right;
        right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left)/2;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }
        int sec = nums[right] == target ? right : left;
        res[1] = nums[sec] == target ? sec : -1;
        return res;
    }

}
