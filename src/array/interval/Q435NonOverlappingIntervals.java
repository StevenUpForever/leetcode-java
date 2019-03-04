package array.interval;

import public_class.Interval;

import java.util.Arrays;
import java.util.Comparator;

public class Q435NonOverlappingIntervals {

    //Difficulty: medium
    //TAG: Apple
    //TAG: array
    //TAG: interval

    /**
     * 435. Non-overlapping Intervals
     * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
     *
     * Note:
     * You may assume the interval's end point is always bigger than its start point.
     * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
     * Example 1:
     * Input: [ [1,2], [2,3], [3,4], [1,3] ]
     *
     * Output: 1
     *
     * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
     * Example 2:
     * Input: [ [1,2], [1,2], [1,2] ]
     *
     * Output: 2
     *
     * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
     * Example 3:
     * Input: [ [1,2], [2,3] ]
     *
     * Output: 0
     *
     * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
     */

    /*
    Solution:
    Sort the intervals by start first then if same by end
    try to count intervals by smallest end, (the larger end interval should be deleted)

    Time: O(n)
    Space: O(1)
     */

    public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start == o2.start) return o1.end - o2.end;
                else return o1.start - o2.start;
            }
        });
        int end = intervals[0].end;
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            Interval next = intervals[i];
            if (end <= next.start) {
                end = next.end;
            } else {
                count++;
                end = Math.min(end, next.end);
            }
        }
        return count;
    }

}
