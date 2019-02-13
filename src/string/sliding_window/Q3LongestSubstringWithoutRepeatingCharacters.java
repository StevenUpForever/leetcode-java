package string.sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q3LongestSubstringWithoutRepeatingCharacters {

    //Difficulty: medium
    //TAG: Apple
    //TAG: sliding window

    /**
     * 3. Longest Substring Without Repeating Characters
     * Given a string, find the length of the longest substring without repeating characters.
     *
     * Example 1:
     *
     * Input: "abcabcbb"
     * Output: 3
     * Explanation: The answer is "abc", with the length of 3.
     * Example 2:
     *
     * Input: "bbbbb"
     * Output: 1
     * Explanation: The answer is "b", with the length of 1.
     * Example 3:
     *
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */

    /*
    Solution 1:
    Use set filter visited char, if met a visited char c, loop i and remove set char until set not contains c
    at this time, i is at right place, but need put c back to set due to a new visited char c1 already here

    Time: O(n^2)
    Space: O(n)
     */

    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        Set<Character> set = new HashSet<>();
        int i = 0, maxLen = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!set.add(c)) {
                maxLen = Math.max(maxLen, j - i);
                while (i < j && set.contains(c)) {
                    set.remove(s.charAt(i));
                    i++;
                }
                //Put the c back due to c visited again and need to filter next dup c
                set.add(c);
            }
        }
        return Math.max(maxLen, s.length() - i);
    }

    /*
    Solution 2:
    Use hashMap, only record most recent visited char and index
    when met a visited char calculate the max length and replace with new recent index in map

    Time: O(n)
    Space: O(n)
     */

    public int lengthOfLongestSubstring2(String s) {
        if (s.length()==0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

}
