package legacy_code;

import java.util.*;

/**
 * Created by ChengzhiJia on 6/5/16.
 */
public class Problem251To260 {

    /*
    252. Meeting Rooms
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
     */
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }

    /*
    253. Meeting Rooms II
    Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
     */
    /*
    Approach: every time find smallest room to add new meeting if valid, sort room by min heap, then return heap's size
     */
    /*
    ****************tricky solution from Leetcode******************
    *
    * Work for OC, sort start and end, compare each start by end, if current smallest start is larger than end, no room needed, otherwise room++
    *
    public int minMeetingRooms(Interval[] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for(int i=0; i<intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        for(int i=0; i<starts.length; i++) {
            if(starts[i]<ends[endsItr])
                rooms++;
            else
                endsItr++;
        }
        return rooms;
    }
     */
    public class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
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
            Interval cur = queue.poll();
            if (intervals[i].start >= cur.end) cur.end = intervals[i].end;
            else queue.offer(intervals[i]);
            queue.offer(cur);
        }
        return queue.size();
    }

    /*
    257. Binary Tree Paths
    Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
     */
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        if (root == null) return result;
        pathSearch(result, "", root);
        return result;
    }

    void pathSearch(ArrayList<String> result, String str, TreeNode node) {
        if (!str.equals("")) str += "->";
        str += node.val;
        if (node.left == null && node.right == null) {
            result.add(str);
        } else {
            if (node.left != null) pathSearch(result, str, node.left);
            if (node.right != null) pathSearch(result, str, node.right);
        }
    }

}
