package LeetCode;

import java.util.*;

/**
 * Created by ChengzhiJia on 6/3/16.
 */
public class Problem51To60 {
    /*
    57. Insert Interval
    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
     */
    public class Interval {
        int start, end;
        Interval( int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<Interval>();
        int index = 0;
        while (index < intervals.size() && intervals.get(index).end < newInterval.start) result.add(intervals.get(index++));
        while (index < intervals.size() && intervals.get(index).start <= newInterval.end) {
            newInterval = new Interval(Math.min(intervals.get(index).start, newInterval.start), Math.max(intervals.get(index).end, newInterval.end));
            index++;
        }
        result.add(newInterval);
        while (index < intervals.size()) result.add(intervals.get(index++));
        return result;
    }

}
