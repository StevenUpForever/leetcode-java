package breadth_first_search;

import java.util.*;

public class Q1048LongestStringChain {

    //Difficulty: medium
    //TAG: BFS
    //TAG: DFS

    /**
     * 1048. Longest String Chain
     * Given a list of words, each word consists of English lowercase letters.
     *
     * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
     *
     * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
     *
     * Return the longest possible length of a word chain with words chosen from the given list of words.
     *
     *
     *
     * Example 1:
     *
     * Input: ["a","b","ba","bca","bda","bdca"]
     * Output: 4
     * Explanation: one of the longest word chain is "a","ba","bda","bdca".
     *
     *
     * Note:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 16
     * words[i] only consists of English lowercase letters.
     */

    /*
    Solution:

    BFS, push all word in words into queue to bfs, try find max steps

    Time: O(n!)
    Space: O(n)
     */

    public int longestStrChain(String[] words) {
        Map<Integer, List<String>> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        for (String word: words) {
            int len = word.length();
            map.putIfAbsent(len, new ArrayList<>());
            map.get(len).add(word);
            queue.offer(word);
        }
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String poll = queue.poll();
                int len = poll.length();
                if (map.containsKey(len + 1)) {
                    for (String next: map.get(len + 1)) {
                        if (addOneLetter(poll, next)) queue.offer(next);
                    }
                }
            }
            step++;
        }
        return step;
    }

    private boolean addOneLetter(String s1, String s2) {
        int count = 0;
        for (int i = 0, j = 0; i < s1.length() && j < s2.length(); i++, j++) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (count > 0) return false;
                count++;
                i--;
            }
        }
        return true;
    }

    /*
    Solution:

    DFS
    memorized, use hashMap filter max steps in words, when do dfs and find the word in map, return map.get(word)
    instead of keep dfs

    Time: O(n)
    Space: O(n)
     */

}
