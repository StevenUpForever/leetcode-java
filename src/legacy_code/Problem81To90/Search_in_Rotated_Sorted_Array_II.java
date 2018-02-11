package legacy_code.Problem81To90;

public class Search_in_Rotated_Sorted_Array_II {

    /**
     * 81. Search in Rotated Sorted Array II
     * Follow up for "Search in Rotated Sorted Array":
     What if duplicates are allowed?

     Would this affect the run-time complexity? How and why?
     Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

     (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

     Write a function to determine if a given target is in the array.

     The array may contain duplicates.
     */

    /**
     * Solution:
     * Similar as problem 33 search in rotated array, in legacy_code.Problem31To40
     * Difference is when nums[left] == nums[right], we don't know should goes left part or right part, so the check condition should be < not <=, so in that case it means we know this part is surely to go, if left == mid == right, we move left or right to middle by 1 index until one part could go
     *
     * Time: average O(logn) worst case: O(n)
     * Space: O(1)
     */

    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right -  left)/2;
            if (nums[middle] == target) return true;
            //this case 56789123, make sure left != mid != right, so we could have a accurate part to go
            else if (nums[left] < nums[middle] || nums[middle] > nums[right]) {
                if (nums[left] <= target && nums[middle] > target) right = middle - 1;
                else left = middle + 1;
            } else if (nums[left] > nums[middle] || nums[middle] < nums[right]) {
                if (nums[right] >= target && nums[middle] < target) left = middle + 1;
                else right = middle - 1;
                //This step means left == mid == right, so move left++ or right--
            } else right--;
        }
        return false;
    }

}
