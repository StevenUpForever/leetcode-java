package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by c0j00cs on 3/15/17.
 */
public class Problem81To90 {

    /*
    88. Merge Sorted Array
    Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
     */
    /*
    Approach: Use queue to store the no place element to put at back places
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int i = 0, j = 0;
        while (i < m && j < n) {
            //If queue is not empty and is smaller than j, so push the cur and poll the peek element to cur, due to queue is must smaller than i
            if (!queue.isEmpty() && queue.peek() <= nums2[j]) {
                queue.offer(nums1[i]);
                nums1[i] = queue.poll();
            } else if (nums1[i] > nums2[j]){
                //If j is smaller, store in queue and replace by j
                queue.offer(nums1[i]);
                nums1[i] = nums2[j++];
             }
            i++;
        }
        //Post steps
        while (!queue.isEmpty() && j < n) {
            nums1[i++] = queue.peek() < nums2[j] ? queue.poll() : nums2[j++];
        }
        while (!queue.isEmpty()) {
            if (i < m) queue.offer(nums1[i]);
            nums1[i++] = queue.poll();
        }
        while (j < n) {
            nums1[i++] = nums2[j++];
        }
    }
}
