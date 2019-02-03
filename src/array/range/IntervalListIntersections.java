package array.range;

import public_class.Interval;

import java.util.*;

public class IntervalListIntersections {

    //Difficulty: medium
    //TAG: range
    //TAG: priority queue

    /**
     * 986. Interval List Intersections
     * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
     *
     *
     * Note:
     *
     * 0 <= A.length < 1000
     * 0 <= B.length < 1000
     * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
     */

    /*
    Solution 1:
    We can treat all intervals in A and B together in a priority queue, and find all overlaps

    Time: O(aloga + blogb + a + b)
    Space: O(a + b)
     */

    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || B == null) return new Interval[0];
        PriorityQueue<Interval> pq = new PriorityQueue<>((o1, o2) -> (o1.start - o2.start));
        Collections.addAll(pq, A);
        Collections.addAll(pq, B);
        List<Interval> list = new ArrayList<>();
        Interval temp = null;
        while (!pq.isEmpty()) {
            Interval poll = pq.poll();
            if (temp == null || temp.end < poll.start) {
                temp = poll;
            } else {
                list.add(new Interval(poll.start, Math.min(temp.end, poll.end)));
                temp = new Interval(poll.start, Math.max(temp.end, poll.end));
            }
        }
        Interval[] intervals = new Interval[list.size()];
        for (int i = 0; i < intervals.length; i++) intervals[i] = list.get(i);
        return intervals;
    }

    /*
    Solution 2:
    two pointers
    1. Skip related intervals when current two intervals A[i] B[j] not met
    2. when met:
        2.1 add Interval(maxStart, minEnd) to list
        2.2 skip the first ended interval

    Time: O(n)
    Space: O(1)
     */

    public Interval[] intervalIntersection2(Interval[] A, Interval[] B) {
        if (A == null || B == null) return new Interval[0];
        List<Interval> list = new ArrayList<>();
        for (int i = 0, j = 0; i < A.length && j < B.length;) {
            if (A[i].end < B[j].start) i++;
            else if (B[j].end < A[i].start) j++;
            else {
                list.add(new Interval(Math.max(A[i].start, B[j].start), Math.min(A[i].end, B[j].end)));
                if (A[i].end < B[j].end) i++;
                else j++;
            }
        }
        Interval[] res = new Interval[list.size()];
        for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
        return res;
    }

}
