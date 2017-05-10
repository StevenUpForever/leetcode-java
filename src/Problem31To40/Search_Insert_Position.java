package Problem31To40;

public class Search_Insert_Position {

    /**
     * 35. Search Insert Position
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

     You may assume no duplicates in the array.

     Here are few examples.
     [1,3,5,6], 5 → 2
     [1,3,5,6], 2 → 1
     [1,3,5,6], 7 → 4
     [1,3,5,6], 0 → 0
     */

    /**
     * Solution:
     * Binary search, when find target, return its index
     * do the post step type binary search, do left left smaller than target and right larger than target, then return right (right index is the index where to insert the target number
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) right = mid;
            else left = mid;
        }
        //Corner case, if all numbers smaller than target, left is the right index
        //As test case in LeetCode, [1, 3], 1, return 0, mean if left is equal to target, insert at left of left
        if (nums[left] >= target) return left;
        //If right smaller than target, the index is smaller + 1, is right + 1
        else if (nums[right] < target) return right + 1;
        return right;
    }

}
