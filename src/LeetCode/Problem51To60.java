package LeetCode;

import java.util.*;

/**
 * Created by ChengzhiJia on 6/3/16.
 */
public class Problem51To60 {

    /*
    53. Maximum Subarray
    Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.
     */
    /*
    DP problem, which M[0] = nums[0], M[i] represent the current contribute from o ... i, the keep global max to record the result
    M[i] = M[i - 1] > 0 ? M[i - 1] + nums[i] : nums[i]
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE, cur = Integer.MIN_VALUE;
        if (nums.length == 0) return result;
        for (int num: nums) {
            //If current contribute less than 0, means will decrease result for sure, so replace with num
            if (cur < 0) {
                cur = num;
            } else {
                //If contribution larger than 0, increase it
                cur = cur + num;
            }
            //However current changed, set max to result, set max value as early as possible
            result = Math.max(result, cur);
        }
        return result;
    }

    /*
    54. Spiral Matrix
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList();
        if (matrix.length == 0) return result;
        int x = 0, y = 0;
        int lenX = matrix.length, lenY = matrix[0].length;
        while (x < lenX && y < lenY) {
            for (int i = y; i < lenY; i++) result.add(matrix[x][i]);
            x++;
            for (int j = x; j < lenX; j++) result.add(matrix[j][lenY - 1]);
            lenY--;
            for (int m = lenY - 1; x < lenX && m >= y; m--) result.add(matrix[lenX - 1][m]);
            lenX--;
            for (int n = lenX - 1; y < lenY && n >= x; n--) result.add(matrix[n][y]);
            y++;
        }
        return result;
    }

    /*
    56. Merge Intervals
    Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
     */
    public List<Interval> merge(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int index = 0;
        while (index < intervals.size() - 1) {
            Interval current = intervals.get(index);
            Interval next = intervals.get(index + 1);
            if (current.end >= next.start) {
                current.start = Math.min(current.start, next.start);
                current.end = Math.max(current.end, next.end);
                intervals.remove(index + 1);
            } else index++;
        }
        return intervals;
    }

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
