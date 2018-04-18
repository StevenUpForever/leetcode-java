public class LongestSubstringMostTwoDistinctChars {

    //TAG: Google
    //TAG: array
    //TAG: Hard

    /**
     * 159. Longest Substring with At Most Two Distinct Characters
     * Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
     *
     * For example, Given s = “eceba”,
     *
     * T is "ece" which its length is 3.
     */

    /**
     * Solution:
     * 1. Could do a general way as leetcode 340
     * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
     *
     * 2. Due to just two distinctChars, map is not necessary
     * Instead, we need 3 pointers, i is loop the string, represent the last index
     * m represent the start index of current longest string with 2 distinct chars
     * n represent the last index of repeat chars
     *
     */

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2) return s.length();
        /*
        n start with -1, so it will not belongs to any chars
        will explain later in for loop
         */
        int m = 0, n = -1, i, len = 0;
        for (i = 1; i < s.length(); i++) {
            //skip dup chars
            if (s.charAt(i) != s.charAt(i - 1)) {
                /*
                Here the i represent the 3rd dup char since n, so n initially is -1 otherwise, at first time,
                i is 2nd dup char, and it will not be right
                 */
                if (n >= 0 && s.charAt(i) != s.charAt(n)) {
                    //update length and update start index, which start after 3rd dup char
                    len = Math.max(len, i - m);
                    m = n + 1;
                }
                //at here, i is met the 2nd char currently, and set n here for move m
                n = i - 1;
            }
        }
        return Math.max(i - m, len);
    }

}
