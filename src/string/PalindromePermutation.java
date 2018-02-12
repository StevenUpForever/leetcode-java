package string;

import java.util.HashMap;
import java.util.Map;

public class PalindromePermutation {

    //TAG: Uber
    //TAG: String

    /**
     * 266. Palindrome Permutation
     * Given a string, determine if a permutation of the string could form a palindrome.

     For example,
     "code" -> False, "aab" -> True, "carerac" -> True.
     */

    /**
     * Solution:
     * Go over s, when frequency is odd number 1, add a global number++, if the frequency is 1, minus it by 1 to 0,
     * and decrease global number--
     * at last, check if global number is smaller than 1 (kind like aba is palindrome)
     * Use HashMap to filter the frequency, due to not say it's lowercase or letter/number only, may unicode
     *
     * Time: O(n)
     * Space: O(n)
     */

    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!map.containsKey(cur) || map.get(cur) == 0) {
                map.put(cur, 1);
                res++;
            } else {
                map.put(cur, 0);
                res--;
            }
        }
        return res <= 1;
    }

}
