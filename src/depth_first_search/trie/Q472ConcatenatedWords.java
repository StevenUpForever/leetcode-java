package depth_first_search.trie;

import java.util.ArrayList;
import java.util.List;

public class Q472ConcatenatedWords {

    //Difficulty: hard
    //TAG: Amazon
    //TAG: dfs
    //TAG; trie

    /**
     * 472. Concatenated Words
     * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
     * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
     *
     * Example:
     * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
     *
     * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
     *
     * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
     *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
     * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
     * Note:
     * The number of elements of the given array will not exceed 10,000
     * The length sum of elements in the given array will not exceed 600,000.
     * All the input string will only include lower case letters.
     * The returned elements order does not matter.
     */

    /*
    Solution:

    dfs + trie

    Time: O(word * size * 2)
    Space: O(word * size * 2)
     */

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) return res;
        TrieNode root = new TrieNode();
        for (String word : words)
            addWord(word, root);
        for (String word : words) {
            if (testWord(word.toCharArray(), 0, root, 0))
                res.add(word);
        }
        return res;
    }

    private void addWord(String str, TrieNode root) {
        char[] chars = str.toCharArray();
        TrieNode cur = root;
        for (char c : chars) {
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new TrieNode();
            }
            cur = cur.next[c - 'a'];
        }
        cur.isEnd = true;
    }

    private boolean testWord(char[] chars, int index, TrieNode root, int count) {
        TrieNode cur = root;
        for (int i = index; i < chars.length; i++) {
            int next = chars[i] - 'a';
            if (cur.next[next] == null) {
                return false;
            } else if (cur.next[next].isEnd) {
                if (i == chars.length - 1) {
                    return count >= 1;
                } else if (testWord(chars, i + 1, root, count + 1)) {
                    return true;
                }
            }
            cur = cur.next[next];
        }
        return false;
    }

    class TrieNode {
        TrieNode[] next;
        boolean isEnd;
        TrieNode() {
            isEnd = false;
            next = new TrieNode[26];
        }
    }

}
