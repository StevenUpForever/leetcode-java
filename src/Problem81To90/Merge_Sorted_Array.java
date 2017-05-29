package Problem81To90;

import java.util.LinkedList;
import java.util.Queue;

public class Merge_Sorted_Array {

    /**
     * 88. Merge Sorted Array
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

     Note:
     You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
     */

    /**
     * Solution 1:
     * Use a new array to merge nums1 and nums2, then copy all numbers in this array to nums1
     *
     * Time: O(m + n) merge + O(m + n) copy = O(m + n)
     * Space: O(m + n)
     */

    public void mergeS1(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m + n];
        int i = 0, j = 0, index = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) temp[index++] = nums1[i++];
            else temp[index++] = nums2[j++];
        }
        while (i < m) temp[index++] = nums1[i++];
        while (j < n) temp[index++] = nums2[j++];
        for (int k = 0; k < temp.length; k++) nums1[k] = temp[k];
    }

    /**
     * Solution 2:
     * We could use a smaller helper space, merge nums2 to nums1 immediately, save time when copy all numbers in temp array to nums1
     * Use queue to save the numbers which need to temporarily removed from nums1
     * every time, loop the nums1 and nums2
     *      if queue is not empty, compare nums2 with queue.poll until queue is empty (all numbers in queue should be smaller than numbers in current nums1)
     *      else compare nums2 with nums2
     *
     * Time: O(m + n)
     * Space: O(m)
     */

    //Not brief code but easy to understood
    public void mergeS2(int[] nums1, int m, int[] nums2, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int i = 0, j = 0;
        //Compare shorter part of m or n numbers
        while (i < m && j < n) {
            if (queue.isEmpty()) {
                if (nums1[i] > nums2[j]) {
                    queue.offer(nums1[i]);
                    nums1[i] = nums2[j++];
                }
                i++;
            } else {
                //queue.peek() must smaller than nums1[i], so compare queue and nums2[j], and remember push nums1[i] into queue
                if (queue.peek() <= nums2[j]) {
                    queue.offer(nums1[i]);
                    nums1[i++] = queue.poll();
                } else {
                    queue.offer(nums1[i]);
                    nums1[i++] = nums2[j++];
                }
            }
        }
        //Two conditions left, may i < m or j < n, need to consider together with queue
        while (!queue.isEmpty() && j < n) {
            if (queue.peek() <= nums2[j]) nums1[i++] = queue.poll();
            else nums1[i++] = nums2[j++];
        }
        while (!queue.isEmpty() && i < m) {
            queue.offer(nums1[i]);
            nums1[i++] = queue.poll();
        }
        //Clear the queue and nums2
        while (!queue.isEmpty()) nums1[i++] = queue.poll();
        while (j < n) nums1[i++] = nums2[j++];
    }

    /**
     * Solution 3:
     * If sort from left to right, then need extra spaces for temp numbers from nums1 to insert, so we could consider sort the nums1 from the end, as from the end we could use the extra spaces immediately, then we don't need to maintain extra spaces
     * And as sorted numbers already use extra spaces at the end, so when i index moved into < m part in nums1, all behind numbers already sorted, so current nums1 space are available spaces
     *
     * Time: O(m + n)
     * Space: O(1)
     */

    public void mergeS3(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0)
            //As sort from end to start, so order is from ascending to descending
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        while (j >= 0) nums1[index--] = nums2[j--];
    }

}
