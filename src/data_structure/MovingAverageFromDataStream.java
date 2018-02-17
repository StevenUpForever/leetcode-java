package data_structure;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageFromDataStream {

    //TAG: Google
    //TAG: data structure
    //TAG: sliding window
    //Difficulty: Easy

    /**
     * 346. Moving Average from Data Stream
     * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

     For example,
     MovingAverage m = new MovingAverage(3);
     m.next(1) = 1
     m.next(10) = (1 + 10) / 2
     m.next(3) = (1 + 10 + 3) / 3
     m.next(5) = (10 + 3 + 5) / 3
     */

    /**
     * Solution:
     * Use Queue to represent the sliding window, and avoid sum over flow
     */

    class MovingAverage {

        /** Initialize your data structure here. */

        long sum;
        int count, size;
        private Queue<Integer> queue;

        public MovingAverage(int size) {
            this.queue = new LinkedList<>();
            sum = 0;
            count = 0;
            this.size = size;
        }

        public double next(int val) {
            sum += val;
            count++;
            queue.offer(val);
            if (count > size) {
                Integer poll = queue.poll();
                sum -= poll;
                count--;
            }
            return (double)sum/count;
        }
    }

}
