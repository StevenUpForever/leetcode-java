package array;

import java.util.ArrayList;
import java.util.List;

public class Q1002FindCommonCharacters {

    //Difficulty: Easy
    //TAG: array

    /**
     * 1002. Find Common Characters
     * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
     *
     * You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: ["bella","label","roller"]
     * Output: ["e","l","l"]
     * Example 2:
     *
     * Input: ["cool","lock","cook"]
     * Output: ["c","o"]
     *
     *
     * Note:
     *
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     * A[i][j] is a lowercase letter
     */

    /*
    Solution:
    an array saved current common chars and occurrence
    loop the string str in A, every time intersection the current chars -> occurrence with common array
    at last the common array is the array

    Time: O(n)
    Space: O(26)
     */

    public List<String> commonChars(String[] A) {
        List<String> ans = new ArrayList<>();
        int[] count = new int[26];
        for (String a : A) {
            int[] cur = new int[26];
            for (char c : a.toCharArray()) { cur[c - 'a']++; }
            for (int i = 0; i < 26; ++i) { count[i] = Math.min(cur[i], count[i]); }
        }
        for (int i = 0; i < 26; ++i) {
            while (count[i]-- > 0) { ans.add("" + (char)(i + 'a')); }
        }
        return ans;
    }

}
