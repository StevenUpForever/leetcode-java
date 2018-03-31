package binary_search;

public class FindPeakElement {

    //TAG: Google
    //TAG: Binary search
    //Difficulty: medium

    /**
    162. Find Peak Element
    A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
     */

    /**
     * Solution:
     * Binary search
     */

    public int findPeakElement(int[] nums) {
        return searchPeak(nums, 0, nums.length - 1);
    }

    private int searchPeak(int[] nums, int start, int end) {
        if (start == end) return start;
        int middle = (start + end)/2;
        if (nums[middle] < nums[middle + 1]) return searchPeak(nums, middle + 1, end);
        else return searchPeak(nums, start, middle);
    }

}
