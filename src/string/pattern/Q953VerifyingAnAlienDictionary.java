package string.pattern;

public class Q953VerifyingAnAlienDictionary {

    //Difficulty: easy
    //TAG: Facebook
    //TAG: String

    /**
     * 953. Verifying an Alien Dictionary
     * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
     *
     * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true
     * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
     * Example 2:
     *
     * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
     * Output: false
     * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
     * Example 3:
     *
     * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
     * Output: false
     * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
     *
     *
     * Note:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * order.length == 26
     * All characters in words[i] and order are english lowercase letters.
     */

    /*
    Solution:

    1. make chars in order as relative order in nums array
    2. compare word one pair at a time, find out new relative order

    Time: O(n + k) n is length of words, k is length of order
    Space: O(1)
     */

    public boolean isAlienSorted(String[] words, String order) {
        int[] nums = new int[26];
        for (int i = 0; i < order.length(); i++) {
            nums[order.charAt(i) - 'a'] = i;
        }
        outer: for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i], next = words[i + 1];
            int j = 0;
            for (; j < Math.min(cur.length(), next.length()); j++) {
                char c1 = cur.charAt(j), c2 = next.charAt(j);
                if (nums[c1 - 'a'] > nums[c2 - 'a']) return false;
                else if (nums[c1 - 'a'] < nums[c2 - 'a']) continue outer;
            }
            if (j < cur.length()) return false;
        }
        return true;
    }

}
