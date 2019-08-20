package string;

public class Q1163LastSubstringInLexicographicalOrder {

    //Difficulty: Hard
    //TAG: String
    //TAG: Lexicographical order

    /**
     * 1163. Last Substring in Lexicographical Order
     * Given a string s, return the last substring of s in lexicographical order.
     *
     *
     *
     * Example 1:
     *
     * Input: "abab"
     * Output: "bab"
     * Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
     * Example 2:
     *
     * Input: "leetcode"
     * Output: "tcode"
     *
     *
     * Note:
     *
     * 1 <= s.length <= 4 * 10^5
     * s contains only lowercase English letters.
     */

    /*
    Solution:

    find substring with max prefix chars, when find same prefix, need compare from the start char, e.g.
    set up temp result
    abcabc
    when find a, temp = a, due to no bigger string yet
    when find b, temp = b, due to substring start with b will larger than start with a
    c same thing
    when find a, keep index = 2, c
    ...
    until find index = 5, c
    need compare index = 2 with 5, ca... > c then result is substring from index = 2 cabc

    one skip case: when find dup chars e.g. aaabc, don't need compare each a, skip all them due to first char in repeat
    chars will be ideal result in these sequence

    Time: O(n)
    Space: O(1)
     */

    public String lastSubstring(String s) {
        int max = -1, index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (i > 0 && c == s.charAt(i - 1)) continue;
            int cur = c - 'a';
            if (cur > max) {
                index = i;
                max = cur;
            } else if (cur == max) {
                int x = index, y = i;
                while (y < s.length()) {
                    char c1 = s.charAt(x), c2 = s.charAt(y);
                    if (c1 == c2) {
                        x++;
                        y++;
                    } else {
                        if (c1 < c2) index = i;
                        break;
                    }
                }
            }
        }
        return s.substring(index);
    }

}
