package binary_search;

public class Q35SearchInsertPosition {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: binary search

    /**
     * 35. Search Insert Position
     * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
     *
     * You may assume no duplicates in the array.
     *
     * Example 1:
     *
     * Input: [1,3,5,6], 5
     * Output: 2
     * Example 2:
     *
     * Input: [1,3,5,6], 2
     * Output: 1
     * Example 3:
     *
     * Input: [1,3,5,6], 7
     * Output: 4
     * Example 4:
     *
     * Input: [1,3,5,6], 0
     * Output: 0
     */

    /*
    Solution:
    The inserted place is either i that nums[i] == target, or the smallest/first larger number than target
    so use binary search
    when mid == target, return mid
    when mid < target, skip mid: l = mid + 1
    when mid > target, do not skip mid, mid may the final result, r = mid
    finally check if nums[l] == target once, due to loop will not consider when nums.length == 1

    Time: O(logn)
    Space: O(1)
     */

    public int searchInsert(int[] nums, int target) {
        if (nums == null) return 0;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) r = mid;
            else l = mid + 1;
        }
        //Check == once due to l < r will not consider nums.length == 1
        if (nums[l] >= target) return l;
        else if (nums[r] > target) return r;
        //If r < target, then append to last, means insert at nums.length
        else return r + 1;
    }

}
