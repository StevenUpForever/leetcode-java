package recursion;

import java.util.HashMap;

public class WordPatternII {

    //TAG: Uber
    //TAG: recursion
    //Difficulty: Hard

    /**
     * 291. Word Pattern II
     * Given a pattern and a string str, find if str follows the same pattern.

     Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

     Examples:
     pattern = "abab", str = "redblueredblue" should return true.
     pattern = "aaaa", str = "asdasdasdasd" should return true.
     pattern = "aabb", str = "xyzabcxzyabc" should return false.
     Notes:
     You may assume both pattern and str contains only lowercase letters.
     */

    /**
     * Solution:
     * Use recursion, base case when str is null or patter is null return true
     * use HashMap to record current pattern char -> substring in str
     * recursion rule: recursion by diff substrings(0, i) -> substring(i, j) when met the current character c in pattern string,
     * check if next any substring start from i could math the current c -> string, or put new string for c
     * Corner case: when index not enough for next substring match with c or string not equal
     *
     * Time: O(n^n) total n levels when pattern 1 char each level and length diff chars in pattern string
     * Space: O(n)
     */

    public boolean wordPatternMatch(String pattern, String str) {
        //Map is using to check current recursion pattern char -> string pair
        return wordPatternMatchHelper(pattern, str, new HashMap<>(), 0, 0);
    }

    private boolean wordPatternMatchHelper(String pattern, String str, HashMap<Character, String> map, int indexPa, int indexStr) {
        //Only if when pattern index and str index all at the length, match true
        if (indexPa == pattern.length() && indexStr == str.length()) return true;
        //else if anyone over limit first, cannot match
        else if (indexPa == pattern.length() || indexStr == str.length()) return false;
        char patternChar = pattern.charAt(indexPa);
        //When find existed string in map, check if index over limit -> string is equal -> recursion check next index + pattern.length
        if (map.containsKey(patternChar)) {
            String patternStr = map.get(patternChar);
            return indexStr + patternStr.length() <= str.length() &&
                    patternStr.equals(str.substring(indexStr, indexStr + patternStr.length())) &&
                    wordPatternMatchHelper(pattern, str, map, indexPa + 1, indexStr + patternStr.length());

        } else {
            /*
            When not found the pattern in map
            1. check if any pattern string is same but with diff pattern char, which means two diff pattern chars cannot
            have the same string, continue to next loop
            2. put this new string with pattern
             */
            boolean res = false;
            for (int i = indexStr; i < str.length(); i++) {
                String newStr = str.substring(indexStr, i + 1);
                boolean shouldContinue = false;
                for (Character key: map.keySet()) {
                    if (map.get(key).equals(newStr)) {
                        shouldContinue = true;
                        break;
                    }
                }
                if (!shouldContinue) {
                    map.put(patternChar, newStr);
                    res |= wordPatternMatchHelper(pattern, str, map, indexPa + 1, i + 1);
                    map.remove(patternChar);
                }
            }
            return res;
        }
    }

}
