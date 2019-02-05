package array.interval;

import public_class.Interval;

import java.util.Arrays;
import java.util.Comparator;

public class Q252MeetingRooms {

    //TAG: Facebook
    //TAG: array
    //Difficulty: Easy

    /**
     * 252. Meeting Rooms
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
     *
     * For example,
     * Given [[0, 30],[5, 10],[15, 20]],
     * return false.
     */

    /*
     * Solution:
     * Sort intervals by start so we could compare one factor, when two intervals can merge, (first.end > second.start)
     * then cannot attend both, return false, otherwise set local interval to current in array for next compare
     *
     * if all compared, then return true
     *
     * Time: O(nlogn + n)
     * Space: O(1)
     */

    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        Interval first = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < first.end) return false;
            first = intervals[i];
        }
        return true;
    }

}
