package data_structure.stream;

import public_class.Interval;

import java.util.*;

public class Q352DataStreamAsDisjointIntervals {

    //Difficulty: Hard
    //TAG: LinkedIn
    //TAG: data structure

    /**
     * 352. Data Stream as Disjoint Intervals
     * Given a data stream input of non-negative integers a1, a2, ..., an, ..., summarize the numbers seen so far as a list of disjoint intervals.
     *
     * For example, suppose the integers from the data stream are 1, 3, 7, 2, 6, ..., then the summary will be:
     *
     * [1, 1]
     * [1, 1], [3, 3]
     * [1, 1], [3, 3], [7, 7]
     * [1, 3], [7, 7]
     * [1, 3], [6, 7]
     * Follow up:
     * What if there are lots of merges and the number of disjoint intervals are small compared to the data stream's size?
     */

    /*
    Solution:
    for add(), loop to proper index to insert the val, and merge if possible otherwise add new interval
     */

    class SummaryRanges {

        /** Initialize your data structure here. */
        private List<Interval> intervals;
        public SummaryRanges() {
            intervals = new LinkedList<>();
        }

        public void addNum(int val) {
            int index = 0;
            while (index < intervals.size() && intervals.get(index).end < val) {
                index++;
            }
            Interval pre = index - 1 < intervals.size() && index > 0 ? intervals.get(index - 1) : null,
                    next = index < intervals.size() ? intervals.get(index) : null;
            boolean mPre = false, mNext = false;
            if (next != null && val + 1 >= next.start) {
                next.start = Math.min(next.start, val);
                mPre = true;
            }
            if (pre != null && pre.end == val - 1) {
                pre.end = val;
                mNext = true;
            }
            if (mPre && mNext) {
                pre.end = next.end;
                intervals.remove(index);
            } else if (!mPre && !mNext) {
                intervals.add(index, new Interval(val, val));
            }
        }

        public List<Interval> getIntervals() {
            return intervals;
        }
    }

    /*
    Solution 2:
    tree map
     */

    public class SummaryRanges2 {
        TreeMap<Integer, Interval> tree;

        public SummaryRanges2() {
            tree = new TreeMap<>();
        }

        public void addNum(int val) {
            if(tree.containsKey(val)) return;
            Integer l = tree.lowerKey(val);
            Integer h = tree.higherKey(val);
            if(l != null && h != null && tree.get(l).end + 1 == val && h == val + 1) {
                tree.get(l).end = tree.get(h).end;
                tree.remove(h);
            } else if(l != null && tree.get(l).end + 1 >= val) {
                tree.get(l).end = Math.max(tree.get(l).end, val);
            } else if(h != null && h == val + 1) {
                tree.put(val, new Interval(val, tree.get(h).end));
                tree.remove(h);
            } else {
                tree.put(val, new Interval(val, val));
            }
        }

        public List<Interval> getIntervals() {
            return new ArrayList<>(tree.values());
        }
    }

}
