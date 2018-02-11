package legacy_code.Problem31To40;

public class Search_for_a_Range {

    /**
     * 34. Search for a Range
     * Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

     Your algorithm's runtime complexity must be in the order of O(log n).

     If the target is not found in the array, return [-1, -1].

     For example,
     Given [5, 7, 7, 8, 8, 10] and target value 8,
     return [3, 4].
     */

    /**
     * Solution:
     * sorted array, find the scope of one integer, so run two binary search, one find the left occurrence of this integer and another binary search find the right one
     *
     * Time: O(2logn) = O(logn)
     * Space: O(1)
     */

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        return new int[]{binarySearchHelper(nums, target, true), binarySearchHelper(nums, target, false)};
    }

    private int binarySearchHelper(int[] nums, int target, boolean findLeft) {
        int left = 0, right = nums.length - 1;
        //Need to have post step to determine the scope of integer, so need to make left and right two different integer
        while (left < right - 1) {
            int mid = left + (right - left)/2;
            //Don't know if current number (if == target) is the final number we want, so cannot skip this number
            if (findLeft) {
                if (nums[mid] >= target) right = mid;
                else left = mid;
            } else {
                if (nums[mid] <= target) left = mid;
                else right = mid;
            }
        }
        //Post step, when find left occurrence, priority is left > right > -1, when find right, right > left > -1
        if (findLeft) {
            if (nums[left] == target) return left;
            else if (nums[right] == target) return right;
        } else {
            if (nums[right] == target) return right;
            else if (nums[left] == target) return left;
        }
        return -1;
    }

}
