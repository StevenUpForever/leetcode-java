package string.palindrome;

public class PalindromicSubstrings {

    //TAG: LinkedIn
    //TAG: string
    //Difficulty: Medium

    /**
     * 647. Palindromic Substrings
     * Given a string, your task is to count how many palindromic substrings in this string.

     The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

     Example 1:
     Input: "abc"
     Output: 3
     Explanation: Three palindromic strings: "a", "b", "c".
     Example 2:
     Input: "aaa"
     Output: 6
     Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     Note:
     The input string length won't exceed 1000.
     */

    /**
     * Solution:
     * Regular brute force solution for for loop and valid substring will have 2 + 2 * 2 + ... 2 * n (due to need O(m)
     * to valid palindrome substring), O(2n^2) even if it's O(n^2) but will have 2X time than O(n^2)
     *
     * Due to palindrome, use expand from one index to two sides, need to expand from i and (i, i + 1) due to odd length
     * palindrome and even length palindrome
     * check substring twice (odd and even) will have same 2O(n^2)
     * so check odd and even palindrome together, by two boolean value, if any palindrome stop, false the boolean value
     *
     * Time: O(n^2)
     * Space: O(1)
     */

    public int countSubstrings(String s) {
        int res = 0;
        if (s == null || s.length() == 0) return res;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int l = i, r1 = i, r2 = i + 1;
            boolean continue1 = true, continue2 = true;
            //Use same left for r1 and r2
            while (l >= 0 && (r1 < chars.length || r2 < chars.length)
                    && (continue1 || continue2)) {
                //Need check if r1 over limit, due to if r2 stopped early, r1 may over the length and r2 not
                if (r1 < chars.length && chars[l] == chars[r1] && continue1) {
                    res++;
                    r1++;
                } else continue1 = false;
                if (r2 < chars.length && chars[l] == chars[r2] && continue2) {
                    res++;
                    r2++;
                } else continue2 = false;
                l--;
            }
        }
        return res;
    }

}
