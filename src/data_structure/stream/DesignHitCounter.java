package data_structure.stream;

import java.util.LinkedList;
import java.util.Queue;

public class DesignHitCounter {

    //Difficulty: Medium
    //TAG: Uber
    //TAG: data structure
    //TAG: stream

    /**
     * 362. Design Hit Counter
     * Design a hit counter which counts the number of hits received in the past 5 minutes.
     *
     * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.
     *
     * It is possible that several hits arrive roughly at the same time.
     *
     * Example:
     *
     * HitCounter counter = new HitCounter();
     *
     * // hit at timestamp 1.
     * counter.hit(1);
     *
     * // hit at timestamp 2.
     * counter.hit(2);
     *
     * // hit at timestamp 3.
     * counter.hit(3);
     *
     * // get hits at timestamp 4, should return 3.
     * counter.getHits(4);
     *
     * // hit at timestamp 300.
     * counter.hit(300);
     *
     * // get hits at timestamp 300, should return 4.
     * counter.getHits(300);
     *
     * // get hits at timestamp 301, should return 3.
     * counter.getHits(301);
     * Follow up:
     * What if the number of hits per second could be very large? Does your design scale?
     */

    /*
    Solution:
    Use queue to record the right amount numbers in queue, when hit getHit, make sure there's less than 300 secs in queue
     */

    class HitCounter {

        private Queue<Integer> queue;
        private int count;

        /** Initialize your data structure here. */
        public HitCounter() {
            queue = new LinkedList<>();
            count = 0;
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.poll();
                count--;
            }
            queue.offer(timestamp);
            count++;
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.poll();
                count--;
            }
            return count;
        }
    }

    /*
    Follow up:

    build
    class SecHit {
        int timeStamp;
        int hits;
    }
    count same timeStamp hits in one object, save space
     */

}
