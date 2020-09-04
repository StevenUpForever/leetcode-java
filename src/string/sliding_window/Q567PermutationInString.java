package string.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class Q567PermutationInString {

    //Difficulty: Medium
    //TAG: Facebook
    //TAG: String
    //TAG: Sliding window

    /**
     * 567. Permutation in String
     * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
     * In other words, one of the first string's permutations is the substring of the second string.
     *
     *
     *
     * Example 1:
     *
     * Input: s1 = "ab" s2 = "eidbaooo"
     * Output: True
     * Explanation: s2 contains one permutation of s1 ("ba").
     * Example 2:
     *
     * Input:s1= "ab" s2 = "eidboaoo"
     * Output: False
     *
     *
     * Note:
     *
     * The input strings only contain lower case letters.
     * The length of both given strings is in range [1, 10,000].
     */

    /*
    Solution:

    sliding window filter by map

    Time: O(m + n)
    Space: O(m)
     */

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len1; i++) {
            char c = s1.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        for (int i = 0; i < len2; i++) {
            char c = s2.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) count++;
            }
            if (i >= len1) {
                char c2 = s2.charAt(i - len1);
                if (map.containsKey(c2)) {
                    map.put(c2, map.get(c2) + 1);
                    if (map.get(c2) == 1) count--;
                }
            }
            if (count == map.size()) return true;
        }
        return false;
    }

    /*
    Solution 2:
    sliding window, filter by int[26]

    Time: O(m + n)
    Space: O(1)
     */

    public boolean checkInclusion2(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }

        return false;
    }

    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }

}
