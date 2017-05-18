package Problem41To50;

import java.util.*;

public class Group_Anagrams {

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

    /**
     * Solution: Optimized brute force
     * Use a HashMap which key is string, value is list contained all anagrams
     * Sort the current string in strs, and add to same string key map
     *
     * Time: O(nmlogm) n is the length of strs, m is the average length of string in strs (mlogm normally fastest way to sort the string)
     * Space: O(n) hashmap same space as strs to store strings
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
