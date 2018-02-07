package Problem121To130;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Word_Ladder {

    /**
     * 127. Word Ladder
     * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

     Only one letter can be changed at a time.
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     return its length 5.

     Note:
     Return 0 if there is no such transformation sequence.
     All words have the same length.
     All words contain only lowercase alphabetic characters.
     You may assume no duplicates in the word list.
     You may assume beginWord and endWord are non-empty and are not the same.
     UPDATE (2017/1/20):
     The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to get the latest changes.
     */

    /**
     * Solution 1: depth_first_search
     * Try to modify each possible word and recursion to next level,
     *      1. find the end word then update global max with current level
     *      2. haven't find the end word, return
     * If last dis keep the origin Int.MAX value, set to 0, otherwise return dis
     *
     * Time: O(n!)
     * Space: O(n)
     */

    private int dis = Integer.MAX_VALUE;
    public int ladderLengthS1(String beginWord, String endWord, List<String> wordList) {
        ladderLengthHelper(beginWord, endWord, wordList, new HashSet<>(), 0);
        return dis == Integer.MAX_VALUE ? 0 : dis;
    }

    private void ladderLengthHelper(String beginWord, String endWord, List<String> wordList, HashSet<String> set, int level) {
        if (beginWord == null) return;
        if (beginWord.equals(endWord)) {
            dis = Math.min(dis, level + 1);
            return;
        }
        for (String word: wordList) {
            if (!set.contains(word) && modifyOnce(beginWord, word)) {
                set.add(word);
                ladderLengthHelper(word, endWord, wordList, set, level + 1);
                set.remove(word);
            }
        }
    }

    private boolean modifyOnce(String beginWord, String endWord) {
        char[] chars1 = beginWord.toCharArray(), chars2 = endWord.toCharArray();
        int diff = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) diff++;
        }
        return diff <= 1;
    }

    /**
     * Solution 2: BFS
     * Depends on solution 1, we have something to improve:
     *      1. depth_first_search works, but in some specific case, when wordList is very large depth_first_search takes much more time, BFS will be a good choice, when at some level find the endWord, could immediately return current level
     *      2. every time we check if could modify word once, and for loop the word all, but if we could for loop once the current word and add all possible modifications in wordsList into next level, that will be better when word.length is very long
     * ***Although wordsList changed to list, we'd better change it to HashSet so that as inner loop could check wordList.contains(target) faster***
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int ladderLengthS2(String beginWord, String endWord, List<String> wordList) {
        //Pre step to change list to set to increase contains method performance
        HashSet<String> wordSet = new HashSet<>();
        for (String word: wordList) wordSet.add(word);
        if (!wordSet.contains(endWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        int dis = 1;
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int m = 0; m < size; m++) {
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char cur = chars[i];
                        chars[i] = c;
                        String target = String.valueOf(chars);
                        if (endWord.equals(target)) return dis + 1;
                        if (!visited.contains(target) && wordSet.contains(target)) {
                            queue.offer(target);
                            visited.add(target);
                        }
                        //Reset string status after temp modification
                        chars[i] = cur;
                    }
                }
            }
            dis++;
        }
        return 0;
    }

    /**
     * Solution 3: Two way BFS from leetCode
     * Depends on solution 2, if wordsList very large, if queue became very large, we need to loop the queue again and again
     * So if we could use more efficient way to check we find endWord (by HashSet or HashMap)
     * Then we have two sets, begin and end, every time, try to start from smaller set to larger one
     * Whether start path find the end path or end path find start path, we could combine a full path, better than loop the larger queue again and again
     *
     * Time: O(n/2) = O(n) but run time faster
     * Space: O(n)
     */

    public int ladderLengthS3(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>();
        for (String word: wordList) wordSet.add(word);
        if (!wordSet.contains(endWord)) return 0;
        HashSet<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        int len = 1;
        HashSet<String> visited = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                HashSet<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            HashSet<String> temp = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        //If end path set (may beginSet at this time) contains target, it means we connected to the end, we find the end word, return the distance
                        if (endSet.contains(target)) return len + 1;
                        if (!visited.contains(target) && wordSet.contains(target)) {
                            temp.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            //Same as poll queue of all current level nodes
            beginSet = temp;
            //Whether start path or end path increase, increase len
            len++;
        }
        return 0;
    }

}
