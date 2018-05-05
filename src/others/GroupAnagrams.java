package others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {

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
     * Solution:
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

}
