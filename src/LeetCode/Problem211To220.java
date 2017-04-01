package LeetCode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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

    /*Approach: Use min heap (kth largest), when value is larger than peek, means should put this element in this kth max heap, after all, kth max heap are all k max elements, which peak is kth largest
     */

    public int findKthLargest(int[] nums, int k) {
        if (nums.length == 0) return 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (i < k) queue.offer(nums[i]);
            else if (!queue.isEmpty() && queue.peek() < nums[i]) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.peek();
    }


//    public int findKthLargest(int[] nums, int k) {
//        if (nums.length == 0) return 0;
//        quickSort(nums, 0, nums.length - 1);
//        return nums[k - 1];
//    }
//
//    private void quickSort(int[] nums, int start, int end) {
//        if (start < end) {
//            int partition = partition(nums, start, end);
//            quickSort(nums, start, partition - 1);
//            quickSort(nums, partition + 1, end);
//        }
//    }
//
//    private int partition(int[] nums, int start, int end) {
//        int pivot = nums[end];
//        int i = start;
//        for (int j = start; j < end; j++) {
//            if (nums[j] > pivot) {
//                int temp = nums[i];
//                nums[i] = nums[j];
//                nums[j] = temp;
//                i++;
//            }
//        }
//        int temp = nums[i];
//        nums[i] = nums[end];
//        nums[end] = temp;
//        return i;
//    }

    /*
    217. Contains Duplicate
    Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

     */
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i: nums) {
            if (map.get(i) == null) {
                map.put(i, true);
            } else {
                return true;
            }
        }
        return false;
    }

}
