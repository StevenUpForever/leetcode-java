package string.anagram;

import java.util.*;

public class Q49GroupAnagrams {

    //TAG: Facebook
    //TAG: Amazon
    //TAG: Uber
    //TAG: Others
    //Difficulty: Medium

    /**
     * 49. Group Anagrams
     * Given an array of strings, group anagrams together.

     For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     Return:

     [
     ["ate", "eat","tea"],
     ["nat","tan"],
     ["bat"]
     ]
     Note: All inputs will be in lower-case.
     */

    /*
     * Solution 1:
     * Anagram problem, if there's one anagram, or all compare to one, could use HashMap or int[26/256] depends on String
     * is ASCII only or unicode
     * This problem need to check O^2 time by map_set/int[] method, so:
     * sort every string, then put in a map<String, List<String>>
     *
     * Time: O(n * nlog(n)) = O(n^2log(n))
     * Space: O(n) if every string has no anagram
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = String.valueOf(chars);
            if (!map.containsKey(newStr)) {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(newStr, list);
            } else {
                map.get(newStr).add(str);
            }
        }
        return new ArrayList<>(map.values());
    }

    /*
    Solution 2:
    Sort string could optimized, which can count frequency of each char in string, append count by insert # or
    other special symbol between a-z and compare with this count string

    Time: O(n*k)
    Space: O(n*k)
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> ans = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }

}
