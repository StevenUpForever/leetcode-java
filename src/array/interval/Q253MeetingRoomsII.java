package array.interval;

import public_class.Interval;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Q253MeetingRoomsII {

    //TAG: Facebook
    //TAG: Google
    //TAG: Array
    //TAG: range
    //Difficulty: Medium

    /**
     * 253. Meeting Rooms II
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

     For example,
     Given [[0, 30],[5, 10],[15, 20]],
     return 2.
     */

    /**
     * Solution 1:
     * Consider about only when one end > next.start we need a new meeting room, but when next2 is overlapped with
     * next but not with first one, we don't need another meeting room, due to the first meeting is done
     *
     * So we need track not only pre end with next start, but also the current start with pre smallest end, so that we
     * know if any pre meeting is done before cur meeting start
     * Use heap to track earliest end meeting, when cur meeting need a new room (start and end overlapped),
     * we push new meeting in heap, the heap size represent how many meeting rooms needed, when not need a meeting room
     * e.g. current start >= smallest.end, then we merge this smallest with current, represent that current is using
     * the smallest meeting room, update the end for next compare
     *
     * Time: O(nlogn)
     * Space: O(n)
     */

    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });
        queue.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            Interval poll = queue.poll();
            //We don't need a meeting room and merge them (keep occupy this room)
            if (intervals[i].start >= poll.end) {
                poll.end = intervals[i].end;
                //Otherwise we need a room and push it to the queue
            } else queue.offer(intervals[i]);
            //whether we need a room or not, we push poll back to queue
            queue.offer(poll);
        }
        return queue.size();
    }

    /**
     * Solution 2:
     * Don't need to consider every interval as a single interval but two numbers, start and end, gather all starts
     * and ends
     *
     * sort starts and ends in ascending order
     * compare start and end, if start < end, need room, room++
     * otherwise move end try to find the end > start
     *
     * Time: O(n + 2nlogn + 2n) = O(nlogn)
     * Space: O(n)
     */

    public int minMeetingRooms2(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endIndex = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endIndex]) rooms++;
            else endIndex++;
        }
        return rooms;
    }

}
