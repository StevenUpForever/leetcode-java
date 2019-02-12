package array.interval;

import public_class.Interval;
import java.util.*;

public class Q56MergeIntervals {

    //TAG: Google
    //TAG: Facebook
    //TAG: Microsoft
    //TAG: LinkedIn
    //TAG: Snap
    //TAG: Apple
    //TAG: array
    //Difficulty: Medium

    /**
     * 56. Merge Intervals
     * Given a collection of intervals, merge all overlapping intervals.

     For example,
     Given [1,3],[2,6],[8,10],[15,18],
     return [1,6],[8,10],[15,18].
     */

    /*
     * Solution 1:
     * Sort List by start, and compare/merge all ends
     *      if cur.end >= next.start
     *          means cur and next could be merge, then the new interval.end is the max(cur.end, next.end)
     *          delete the next
     *          the compare current to the 3rd next to current (2nd after delete the 2nd)
     *      else index++
     *
     * Time: if intervals is kinds of ArrayList, Due to delete the 2nd if merge, will move all behind intervals to pre 1 index, so O(n - 1) + O(n - 2) + ... + O(1) = O(n ^ 2)
     * If intervals is kinds of LinkedList O(n)
     * Space: O(1)
     */

    public List<Interval> mergeS1(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int index = 0;
        while (index < intervals.size() - 1) {
            Interval current = intervals.get(index), next = intervals.get(index + 1);
            if (current.end >= next.start) {
                current.end = Math.max(current.end, next.end);
                intervals.remove(index + 1);
            } else index++;
        }
        return intervals;
    }

    /*
     * Solution 2:
     * Alloc new array to store new interval, (avoid when deleting element in array, extra time complexity move all behind ones to front)
     * Keep record of a pre interval, compare with each interval in intervals
     * if the cur is the first (means pre == null), or cur.start > pre.end (cannot merge anymore) add cur to result list, and cur to pre for next compare
     * else set pre.end = cur.end (pre is referenced the last interval in result array, so if the last interval in intervals is merged to pre one, it's already merged into the last interval in result list, no need for a post step)
     *
     * Time: O(n)
     * Space: O(1)
     */

    public List<Interval> mergeS2(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> res = new ArrayList<>();
        Interval pre = null;
        for (Interval interval: intervals) {
            //If interval is the first or cannot merged with the pre one
            if (pre == null || interval.start > pre.end) {
                res.add(interval);
                //Pre is set to the current interval also the last one in res, when after res.add(interval)
                pre = interval;
            } else if (pre.end < interval.end) {
                pre.end = interval.end;
            }
        }
        return res;
    }

}
