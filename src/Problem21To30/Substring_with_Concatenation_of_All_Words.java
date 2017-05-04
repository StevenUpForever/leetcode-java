package Problem21To30;

import java.util.*;

public class Substring_with_Concatenation_of_All_Words {

    /**
     * 30. Substring with Concatenation of All Words
     * You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

     For example, given:
     s: "barfoothefoobarman"
     words: ["foo", "bar"]

     You should return the indices: [0,9].
     (order does not matter).
     */

    /**
     * Solution:
     * be aware that the word in words is the same length, but s may not combine with word length words, so sliding window solution may not work
     * for loop the s, increase by 1 every time
     * use a hashmap to record the frequency of word
     * from each index, try to valid all possible words after it to see is could make a anagram
     *
     * Time: O(n^2) = O(n - 1) + O(n - 2) + ... + O(1) = O(n(n + 1)/2)
     * Space: O(m) m represent the length of words
     */

    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return list;
        //Same length for every word
        int sigLen = words[0].length();
        int totalLen = sigLen * words.length;
        if (totalLen > s.length()) return list;
        HashMap<String, Integer> map = new HashMap<>();
        for (String str: words) {
            Integer cur = map.get(str);
            if (cur == null) map.put(str, 1);
            else map.put(str, cur + 1);
        }
        //Make sure i still could have a total length subString to iterative
        for (int i = 0; i <= s.length() - totalLen; i++) {
            HashMap<String, Integer> cloneMap = new HashMap<>();
            cloneMap.putAll(map);
            int temp = i, count = 0;
            //Be aware of the while condition, make sure temp could go the last word possible in current anagram
            while (temp <= i + totalLen - sigLen) {
                String curSubstr = s.substring(temp, temp + sigLen);
                Integer cur = cloneMap.get(curSubstr);
                if (cur != null && cur > 0) {
                    cloneMap.put(curSubstr, cur - 1);
                    //Only when cur == 1, means in the current step, the word verify in map is 0
                    if (cur == 1) count++;
                    temp += sigLen;
                } else break;
            }
            if (count == cloneMap.size()) list.add(i);
        }
        return list;
    }

    /**
     * ****** When s are combined with same length word as in words ******
     * Solution:
     * Key point is all words in words array are of the same length
     * Similar as find anagram in string, treat word in words as one character (due to same length)
     * define two index, which the left is left index of the first word in the window, right is the left index of the last word in the window, have a hashMap to record the frequency
     *
     * Time: O(n) n represent the length of s
     * Space: O(m) m represent the length of words
     */

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return list;
        //Same length for every word
        int sigLen = words[0].length();
        int totalLen = sigLen * words.length;
        if (totalLen > s.length()) return list;
        HashMap<String, Integer> map = new HashMap<>();
        for (String str: words) {
            Integer cur = map.get(s);
            if (cur == null) map.put(str, 1);
            else map.put(str, cur + 1);
        }
        int count = 0;
        for (int i = 0; i <= s.length() - sigLen; i += sigLen) {
            String rSubstr = s.substring(i, i + sigLen);
            Integer rStrNum = map.get(rSubstr);
            if (rStrNum != null) {
                map.put(rSubstr, rStrNum - 1);
                if (rStrNum == 1) count++;
            }
            if (i >= totalLen) {
                String lSubStr = s.substring(i - totalLen, i - totalLen + sigLen);
                Integer lStrNum = map.get(lSubStr);
                if (lStrNum != null) {
                    map.put(lSubStr, lStrNum + 1);
                    if (lStrNum == 0) count--;
                }
            }
            if (count == map.size()) list.add(i - totalLen + sigLen);
        }
        return list;
    }

    /**
     * ****** When word in words array are may not the same length ******
     * Solution:
     * Sliding window & valid concat DP problem
     * First, valid substring.length == sum of length in words array, so we set a window start from 0 length with this totalLength
     * for each time the sliding window move right by 1, run a concat validation helper function (dynamic programming) to validate current window, and if so, add current start index to result array
     *
     * Time: O(n^2) when dp algorithm length is s.length is the worst case, n represent the length of s
     * Space: O(n) dp array used every time, largest is the s.length
     */

    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) return list;
        int len = 0;
        for (String str: words) len += str.length();
        if (len > s.length()) return list;
        int l = 0, r = len - 1;
        while (r < s.length()) {
            //If the window is valid from concat the words in array, add the current left index
            if (validConcat2(s, l, r, words)) list.add(l);
            l++;
            r++;
        }
        return list;
    }

    /**
     * DP algorithm to validate current substring could be concat with words in array or not
     * Base case: M[0] = false, "" cannot concat from strings in words array (except the empty words array)
     * Induction rule:
     *      if words.contains(s[l, r]) ||
     *             for (i in l...r) (dp[i] && words.contains[s[i...r]) dp[i] = true
     * return the last boolean of dp array
     *
     * Time: O(n^2) n represent the current part length from l to r
     * Space: O(n) dp array to record the current substring vailidation
     */

    private boolean validConcat2(String s, int l, int r, String[] words) {
        //Set a dp array to represent the current substring validation
        boolean[] valid = new boolean[r - l + 1];
        //Use hashMap is faster than words.contains()
        HashMap<String, Integer> map = new HashMap<>();
        //Add word frequency into the hashMap
        for (String str: words) {
            Integer cur = map.get(str);
            if (cur == null) map.put(str, 1);
            else map.put(str, cur + 1);
        }
        for (int i = l; i <= r; i++) {
            String totalSubStr = s.substring(l, i + 1);
            Integer totalNum = map.get(totalSubStr);
            //If current substring is already valid, set dp value to true
            //If there's at least one more word in the hashMap
            if (totalNum != null && totalNum > 0) {
                valid[i - l] = true;
                Integer num = map.get(totalSubStr);
                map.put(totalSubStr, num - 1);
            } else {
                //If current substring cannot be immediately be concat by words in array, verify each substring in the current substring from the record in dp array, if left part is valid in array and right part is valid though word array, this total substring is valid, so set dp value to true
                for (int j = l; j < i; j++) {
                    String curSubStr = s.substring(j + 1, i + 1);
                    Integer curNum = map.get(curSubStr);
                    if (valid[j - l] && curNum != null && curNum > 0) {
                        valid[i - l] = true;
                        map.put(curSubStr, curNum - 1);
                        break;
                    }
                }
            }
        }
        return valid[valid.length - 1];
    }

}
