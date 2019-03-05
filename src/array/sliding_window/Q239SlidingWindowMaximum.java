package array.sliding_window;

import java.util.*;

public class Q239SlidingWindowMaximum {

    //Difficulty: hard
    //TAG: Apple
    //TAG: array
    //TAG: sliding window

    /**
     * 239. Sliding Window Maximum
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
     *
     * Follow up:
     * Could you solve it in linear time?
     */

    /*
    Solution:
    Use queue to save proper current max value, keep pop oldest largest value in array

    Time: O(n)
    Space: O(n)
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return new int[0];
        //Deque is used to save the index, then is easy to compare deque.peekFirst is over k size
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            //If a larger value is coming, pop all smaller values, since newer and larger value will be the max value
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
            //Only when queue.size over k, pop oldest value
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) deque.pollFirst();
            deque.offerLast(i);
            //The current oldest large values are the valid one
            if (i >= k - 1) res[i - k + 1] = nums[deque.peekFirst()];
        }
        return res;
    }

}
