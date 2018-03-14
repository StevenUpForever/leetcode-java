package array.range;

import public_class.Interval;
import java.util.Arrays;

public class MeetingRoomsII {

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
     * Solution:
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

    public int minMeetingRooms(Interval[] intervals) {
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
