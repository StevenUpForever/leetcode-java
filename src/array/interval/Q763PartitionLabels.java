package array.interval;

import public_class.Interval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q763PartitionLabels {

    //Difficulty: medium
    //TAG: Amazon
    //TAG: array
    //TAG: interval

    /**
     * 763. Partition Labels
     * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
     *
     * Example 1:
     * Input: S = "ababcbacadefegdehijhklij"
     * Output: [9,7,8]
     * Explanation:
     * The partition is "ababcbaca", "defegde", "hijhklij".
     * This is a partition so that each letter appears in at most one part.
     * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
     * Note:
     *
     * S will have length in range [1, 500].
     * S will consist of lowercase letters ('a' to 'z') only.
     */

    /*
    Solution:

    this is actually overlap intervals of character c (first occur, last occur)

    Time: O(n + n) = O(n)
    Space: O(n)
     */

    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) return res;
        Map<Character, Interval> map = new HashMap<>();
        List<Interval> list = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (!map.containsKey(c)) {
                //Alloc new interval with start and end be i
                Interval interval = new Interval(i, i);
                map.put(c, interval);
                //new interval will be first occur of current character, which make the intervals sorted by start
                list.add(interval);
                //keep update the end of the found interval
            } else map.get(c).end = i;
        }
        Interval interval = list.get(0);
        for (Interval cur: list) {
            if (interval.end < cur.start) {
                res.add(interval.end - interval.start + 1);
                interval = cur;
            } else {
                interval.end = Math.max(interval.end, cur.end);
            }
        }
        res.add(interval.end - interval.start + 1);
        return res;
    }

}
