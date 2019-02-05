package array.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q451SortCharactersByFrequency {

    //Difficulty: Medium
    //TAG: Uber
    //TAG: Bucket sort
    //TAG: priority queue

    /**
     * 451. Sort Characters By Frequency
     * Given a string, sort it in decreasing order based on the frequency of characters.
     *
     * Example 1:
     *
     * Input:
     * "tree"
     *
     * Output:
     * "eert"
     *
     * Explanation:
     * 'e' appears twice while 'r' and 't' both appear once.
     * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
     * Example 2:
     *
     * Input:
     * "cccaaa"
     *
     * Output:
     * "cccaaa"
     *
     * Explanation:
     * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
     * Note that "cacaca" is incorrect, as the same characters must be together.
     * Example 3:
     *
     * Input:
     * "Aabb"
     *
     * Output:
     * "bbAa"
     *
     * Explanation:
     * "bbaA" is also a valid answer, but "Aabb" is incorrect.
     * Note that 'A' and 'a' are treated as two different characters.
     */

    /*
    Solution:
    Bucket sort

    Time: O(n + n + n) = O(n)
    Space: O(2n) = O(n)
     */

    public String frequencySort(String s) {
        if (s == null) return null;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character>[] listArr = new List[s.length() + 1];
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (listArr[value] == null) {
                listArr[value] = new ArrayList<>();
            }
            listArr[value].add(key);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = listArr.length - 1; i >= 0; i--) {
            if (listArr[i] != null) {
                for (Character c : listArr[i]) {
                    for (int j = 0; j < i; j++) builder.append(c);
                }
            }
        }
        return builder.toString();
    }

    /*
    Solution:
    Priority Queue


     */

}
