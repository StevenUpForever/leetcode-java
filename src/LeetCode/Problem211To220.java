package LeetCode;

/**
 * Created by ChengzhiJia on 7/14/16.
 */
public class Problem211To220 {
    /*
    215. Kth Largest Element in an Array
    Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0) return 0;
        quickSort(nums, 0, nums.length - 1);
        return nums[k - 1];
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start < end) {
            int partition = partition(nums, start, end);
            quickSort(nums, start, partition - 1);
            quickSort(nums, partition + 1, end);
        }
    }

    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start;
        for (int j = start; j < end; j++) {
            if (nums[j] > pivot) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
        }
        int temp = nums[i];
        nums[i] = nums[end];
        nums[end] = temp;
        return i;
    }

}
