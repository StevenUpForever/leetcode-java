package priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {

    //TAG: Facebook
    //TAG: Microsoft
    //TAG: Amazon
    //TAG: Apple

    /**
     * 215. Kth Largest Element in an Array
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
     *
     * Example 1:
     *
     * Input: [3,2,1,5,6,4] and k = 2
     * Output: 5
     * Example 2:
     *
     * Input: [3,2,3,1,2,4,5,5,6] and k = 4
     * Output: 4
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ array's length.
     */

    /*
    Solution 1:
    Sort the array, and return len - 1 - kth value
    Time: O(nlogn)
    Space: O(1)
     */

    /*
    Solution 2:
    min heap, size k
    loop array
        1. when < k, add to heap
        2. when > k, compare with peek, if elm > peek, pop peek and push elm
    finally return peek

    Time: O(nlogk)
    Space: O(k)
     */

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (i < k) queue.offer(nums[i]);
            else if (!queue.isEmpty() && nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.isEmpty() ? 0 : queue.peek();
    }

}
