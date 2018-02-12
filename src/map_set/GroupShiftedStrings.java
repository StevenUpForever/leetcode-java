package map_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {

    //TAG: Uber
    //TAG: HashMap
    //Difficulty: Medium

    /**
     * 249. Group Shifted Strings
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

     "abc" -> "bcd" -> ... -> "xyz"
     Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

     For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
     A solution is:

     [
     ["abc","bcd","xyz"],
     ["az","ba"],
     ["acef"],
     ["a","z"]
     ]
     */

    /**
     * Solution:
     * Convert each string to a first letter a based string, like bcd -> abc, zy -> az
     * put same convert string key into map
     * append all values of map to result list
     *
     * Time: O(m*n) total chars of all strings
     * Space: O(m) if every key has only one string
     */

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str: strings) {
            String key = convertStr(str);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            List<String> val = map.get(key);
            val.add(str);
            map.put(key, val);
        }
        return new ArrayList<>(map.values());
    }

    private String convertStr(String str) {
        int shift = str.charAt(0) - 'a';
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int ascVal = c - shift < 97 ? c + 26 - shift : c - shift;
            builder.append((char)ascVal);
        }
        return builder.toString();
    }

}
