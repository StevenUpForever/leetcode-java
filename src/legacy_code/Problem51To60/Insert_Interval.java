package legacy_code.Problem51To60;

import public_class.Interval;
import java.util.*;

public class Insert_Interval {

    /**
     * 57. Insert Interval
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

     You may assume that the intervals were initially sorted according to their start times.

     Example 1:
     Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

     Example 2:
     Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

     This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     */

    /**
     * Solution:
     * As the intervals non-overlapped and sorted by start time
     * for loop the intervals from the start
     *      keep adding to list, find the last interval that end < newInterval.start, move to next, begin the merge
     *      Use the min(cur.start, newInterval.start) as the start
     *      loop next few intervals until the one which cur.start > newInterval.end
     *              every time use max(cur.end, newInterval.end) as the end
     *      Add this merged newInterval and rest of all intervals to list
     *
     * Time: O(n)
     * Space: O(n) size of array list
     */

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int index = 0;
        while (index < intervals.size() && intervals.get(index).end < newInterval.start) {
            res.add(intervals.get(index));
            index++;
        }
        while (index < intervals.size() && intervals.get(index).start <= newInterval.end) {
            //Merge intervals with newInterval, min start as the start, max end as the end
            newInterval.start = Math.min(newInterval.start, intervals.get(index).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(index).end);
            index++;
        }
        //Add newInterval to res after merge
        res.add(newInterval);
        //Add rest of intervals to list
        while (index < intervals.size()) {
            res.add(intervals.get(index));
            index++;
        }
        return res;
    }

}
