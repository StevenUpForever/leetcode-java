package math;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: data structure

    /**
     * 295. Find Median from Data Stream
     * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
     *
     * For example,
     * [2,3,4], the median is 3
     *
     * [2,3], the median is (2 + 3) / 2 = 2.5
     *
     * Design a data structure that supports the following two operations:
     *
     * void addNum(int num) - Add a integer number from the data stream to the data structure.
     * double findMedian() - Return the median of all elements so far.
     *
     *
     * Example:
     *
     * addNum(1)
     * addNum(2)
     * findMedian() -> 1.5
     * addNum(3)
     * findMedian() -> 2
     *
     *
     * Follow up:
     *
     * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
     * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
     */

    /*
    Solution:
    use two heap to track data flow median, one for first half, max heap, one for second half, min heap
    first insert number into proper heap, for num <= max.peek then insert to max heap or num >= min.peek insert into min
    heap
    max.heap will be sure < min.heap
    then adjust two heaps, make sure small.size == max.size or small.size == max.size + 1, in order to return small.peek
    always when total sequence size is odd number
     */

    class MedianFinder {

        private PriorityQueue<Integer> small;
        private PriorityQueue<Integer> large;

        /** initialize your data structure here. */
        public MedianFinder() {
            large = new PriorityQueue<>();
            small = new PriorityQueue<>(Collections.reverseOrder());
        }

        public void addNum(int num) {
            if (small.isEmpty() || num <= small.peek()) small.offer(num);
            else large.offer(num);
            if (small.size() > large.size() + 1) large.offer(small.poll());
            else if (small.size() < large.size()) small.offer(large.poll());
        }

        public double findMedian() {
            int size = small.size() + large.size();
            if (size == 0) return 0.0;
            else if (size % 2 != 0) return (double) small.peek();
            else return (small.peek() + large.peek())/2.0;
        }
    }

}
