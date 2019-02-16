package string.palindrome;

public class Q5LongestPalindromicSubstring {

    //Difficulty: medium
    //TAG: Airbnb
    //TAG: Apple
    //TAG: string
    //TAG: palindrome

    /**
     * 5. Longest Palindromic Substring
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     *
     * Example 1:
     *
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * Example 2:
     *
     * Input: "cbbd"
     * Output: "bb"
     */

    /*
    Solution:

    loop the s, find longest palindrome start from i,i or i,i+1 and expand to two sides, update maxLen and start index
    if possible
    return substring from start to start + maxLen

    Time: O(n^2)
    Space: O(1)
     */

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int maxLen = 1, start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = maxLenOfPalindrome(s, i, i);
            if (len > maxLen) {
                maxLen = len;
                start = i - len/2;
            }
            len = maxLenOfPalindrome(s, i, i + 1);
            if (len > maxLen) {
                maxLen = len;
                start = i - len/2 + 1;
            }
        }
        return s.substring(start, start + maxLen);
    }

    private int maxLenOfPalindrome(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return j - i - 1;
    }

}
