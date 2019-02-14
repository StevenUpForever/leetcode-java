package string.sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q30SubstringWithConcatenationOfAllWords {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: sliding window

    /**
     * 30. Substring with Concatenation of All Words
     * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
     *
     * Example 1:
     *
     * Input:
     *   s = "barfoothefoobarman",
     *   words = ["foo","bar"]
     * Output: [0,9]
     * Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
     * The output order does not matter, returning [9,0] is fine too.
     * Example 2:
     *
     * Input:
     *   s = "wordgoodgoodgoodbestword",
     *   words = ["word","good","best","word"]
     * Output: []
     */

    /*
    Solution:
    Try every substring(0...len - words.totalLen) and use map filter every string part
    when could match all, add the start index to list

    Time: O(n^2)
    Space: O(n)
     */

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return list;
        //Same length for every word
        int sigLen = words[0].length();
        int totalLen = sigLen * words.length;
        if (totalLen > s.length()) return list;
        Map<String, Integer> map = new HashMap<>();
        for (String str: words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (int i = 0; i <= s.length() - totalLen; i++) {
            Map<String, Integer> cloneMap = new HashMap<>(map);
            int temp = i, count = 0;
            while (temp <= i + totalLen - sigLen) {
                String curSubstr = s.substring(temp, temp + sigLen);
                if (cloneMap.getOrDefault(curSubstr, 0) > 0) {
                    cloneMap.put(curSubstr, cloneMap.get(curSubstr) - 1);
                    if (cloneMap.get(curSubstr) == 0) count++;
                    temp += sigLen;
                } else break;
            }
            if (count == cloneMap.size()) list.add(i);
        }
        return list;
    }

}
