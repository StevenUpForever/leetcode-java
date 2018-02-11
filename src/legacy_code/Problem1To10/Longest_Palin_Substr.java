package legacy_code.Problem1To10;

public class Longest_Palin_Substr {

    /**
     * 5. Longest Palindromic Substring
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

     Example:

     Input: "babad"

     Output: "bab"

     Note: "aba" is also a valid answer.
     Example:

     Input: "cbbd"

     Output: "bb"
     */

    /**
     * Solution:
     * Iterative all characters in string
     *      1. for each character, start from current char, expand to left and right, ensure current str is a palindrome
     *      2. update the global max
     *
     * Time: worst case is when "aaaaaa", each character could go fully the shorter side (left, right)
     *       1 + 2 + ... n/2 = O(n/2(n/2 + 1)/2) = O(n^2)
     *       ***consider about odd length palindrome string like "aba" and event length palindrome string like "aa", need to verify two conditions for each character, it's O(2n^2)
     * Space: O(1)
     */

    //Sometimes global variable is better than pass to many parameters in helper function
    private int left = 0, right = 0, len = 0;

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //Need to check odd length and even length palindrome string
            palindromeHelper(i, i, chars);
            palindromeHelper(i, i + 1, chars);
        }
        return String.valueOf(chars, left, right - left + 1);
    }

    private void palindromeHelper(int l, int r, char[] chars) {
        while (l >= 0 && r < chars.length && chars[l] == chars[r]) {
             l--;
             r++;
         }
         if (r - l - 1 > len) {
             len = r - l - 1;
             left = l + 1;
             right = r - 1;
         }
    }

}
