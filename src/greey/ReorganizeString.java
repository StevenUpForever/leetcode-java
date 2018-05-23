package greey;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {

    //TAG: Google
    //TAG: priority queue
    //TAG: Greedy
    //Difficulty: medium

    /**
     * 767. Reorganize String
     * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
     *
     * If possible, output any possible result.  If not possible, return the empty string.
     *
     * Example 1:
     *
     * Input: S = "aab"
     * Output: "aba"
     * Example 2:
     *
     * Input: S = "aaab"
     * Output: ""
     * Note:
     *
     * S will consist of lowercase letters and have length in range [1, 500].
     */

    /*
    Solution:
    because current most char will be most possible to overflow, so greedy try to append current most count char
    use priorityQueue to save char,count pair and sort by descending count

    Time: O(n) map + O(nlogn) pq + O(nlogn) poll = O(nlogn)
    Space: O(n) map + O(n)pq = O(n)
     */

    public String reorganizeString(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char cur = S.charAt(i);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (map.get(cur) > (S.length() + 1)/2) return "";
        }
        PriorityQueue<CharCount> queue = new PriorityQueue<>(new Comparator<CharCount>() {
            @Override
            public int compare(CharCount o1, CharCount o2) {
                return o2.count - o1.count;
            }
        });
        for (Character key: map.keySet()) queue.offer(new CharCount(key, map.get(key)));
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            CharCount poll = queue.poll();
            if (builder.length() == 0 || builder.charAt(builder.length() - 1) != poll.c) {
                builder.append(poll.c);
                poll.count--;
                if (poll.count > 0) queue.offer(poll);
            } else if (!queue.isEmpty()) {
                CharCount secPoll = queue.poll();
                builder.append(secPoll.c);
                secPoll.count--;
                if (secPoll.count > 0) queue.offer(secPoll);
                queue.offer(poll);
            }
        }
        return builder.toString();
    }

    class CharCount {
        char c;
        int count;
        public CharCount(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

}
