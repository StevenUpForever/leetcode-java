package breadth_first_search;

import java.util.*;

public class Q269AlienDictionary {

    //Difficulty: Hard
    //TAG: Airbnb
    //TAG: BFS

    /**
     * 269. Alien Dictionary
     * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
     *
     * Example 1:
     *
     * Input:
     * [
     *   "wrt",
     *   "wrf",
     *   "er",
     *   "ett",
     *   "rftt"
     * ]
     *
     * Output: "wertf"
     * Example 2:
     *
     * Input:
     * [
     *   "z",
     *   "x"
     * ]
     *
     * Output: "zx"
     * Example 3:
     *
     * Input:
     * [
     *   "z",
     *   "x",
     *   "z"
     * ]
     *
     * Output: ""
     *
     * Explanation: The order is invalid, so return "".
     * Note:
     *
     * You may assume all letters are in lowercase.
     * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
     * If the order is invalid, return an empty string.
     * There may be multiple valid order of letters, return any one of them is fine.
     */

    /*
    Solution:
    1. Only compare orders vertically
    2. compare two strings each time, skip same chars, when c1 != c2 compare and skip current two string compare
    when one char has nothing to compare vertically, ignore it (not mean this char has higher priority)

    Use one hashMap to save all lower priority chars of current char
    Use another hashMap save all chars degree, +1 every time when find a char higher than it
    Finally use BFS, add degree 0 chars to queue, every time decrease degree of poll char followings

    assume words length n, average word length m
    then Time: O(mn + mn + mn) = O(mn)
    Space: O(mn)
     */

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        Map<Character, Set<Character>> following = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        for (String word: words) {
            for (Character c: word.toCharArray()) {
                degree.put(c, 0);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i], next = words[i + 1];
            for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
                char c1 = cur.charAt(j), c2 = next.charAt(j);
                if (c1 != c2) {
                    if (!following.containsKey(c1)) following.put(c1, new HashSet<>());
                    if (following.get(c1).add(c2)) {
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    //If compared two diff chars, then next char needed compare anymore
                    break;
                }
            }
        }
        Queue<Character> queue = new LinkedList<>();
        for (Character c: degree.keySet()) {
            if (degree.get(c) == 0) queue.offer(c);
        }
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            Character poll = queue.poll();
            builder.append(poll);
            if (following.containsKey(poll)) {
                for (Character value: following.get(poll)) {
                    degree.put(value, degree.get(value) - 1);
                    if (degree.get(value) == 0) queue.offer(value);
                }
            }
        }
        System.out.println(builder);
        return builder.length() == degree.keySet().size() ? builder.toString() : "";
    }

}
