package string.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {

    //TAG: Google
    //TAG: sliding window
    //Difficulty: Hard

    /**
     * 340. Longest Substring with At Most K Distinct Characters
     *
     * Given a string, find the length of the longest substring T that contains at most k distinct characters.

     For example, Given s = “eceba” and k = 2,

     T is "ece" which its length is 3.
     */

    /**
     * Solution:
     * Similar to longest substring without repeat characters, which k == s.length
     * Sliding window problem, Use map record char -> frequency pair
     *      if not contains char or value == 0 k--, there's new distinct char
     *      at last need loop from left to i if k < 0 means need to clean some distinct chars, so that delete all
     *      left chars and update map until k >= 0
     * update global max after every loop
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        int max = 0, left = 0;
        for (int i = 0; i < chars.length; i++) {
            char cur = chars[i];
            if (!map.containsKey(cur) || map.get(cur) == 0) {
                map.put(cur, 0);
                k--;
            }
            map.put(cur, map.get(cur) + 1);
            while (k < 0 && left < i) {
                char deleteChar = chars[left];
                Integer val = map.get(deleteChar);
                if (val == 1) k++;
                map.put(deleteChar, val - 1);
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
