import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlienDictionary {

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

     */

    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        Map<Character, Set<Character>> following = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i], next = words[i + 1];
            for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
                char c1 = cur.charAt(j), c2 = next.charAt(j);
                if (c1 != c2) {

                }
            }
        }
    }

}
