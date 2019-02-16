package string;

public class Q28ImplementStrStr {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: String

    /**
     * 28. Implement strStr()
     * Implement strStr().
     *
     * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
     *
     * Example 1:
     *
     * Input: haystack = "hello", needle = "ll"
     * Output: 2
     * Example 2:
     *
     * Input: haystack = "aaaaa", needle = "bba"
     * Output: -1
     * Clarification:
     *
     * What should we return when needle is an empty string? This is a great question to ask during an interview.
     *
     * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
     */

    /*
    Solution:
    loop the haystack, match the substring and find the first index
    be care of several corner cases

    Time: O(n^2)
    Space: O(1)
     */

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        int len1 = haystack.length(), len2 = needle.length();
        if (len1 < len2) return -1;
        else if (needle.length() == 0) return 0;
        for (int i = 0; i <= len1 - len2; i++) {
            if (haystack.substring(i, i + len2).equals(needle)) return i;
        }
        return -1;
    }

}
