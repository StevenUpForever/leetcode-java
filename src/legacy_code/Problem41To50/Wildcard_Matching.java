package legacy_code.Problem41To50;

public class Wildcard_Matching {

    /**
     * 44. Wildcard Matching
     * Implement wildcard pattern matching with support for '?' and '*'.

     '?' Matches any single character.
     '*' Matches any sequence of characters (including the empty sequence).

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "*") → true
     isMatch("aa", "a*") → true
     isMatch("ab", "?*") → true
     isMatch("aab", "c*a*b") → false
     */

    /**
     * Solution:
     * DP problem, 2 dimensional dp problem, set a matrix(s.len + 1, p.len + 1) include which when no characher in s or p
     * Base case: M[0][0] = true, "" and "" matches
     * Induction rule:
     *      1. If p[j] == '*', M[i][j] = M[i - 1][j] || M[i - 1][j - 1] || M[i][j - 1]
     *              A. if '*' == "" M[i][j] = M[i, j - 1], s[i] match p[j] equals s[i] match p[j - 1]
     *              B. if '*' == s[i] or '?' M[i][j] = M[i - 1, j - 1], same as condition 1
     *              C. if '*' == one or more char sequence in s, M[i][j] = M[i - 1, j], due to current '*', M[i - 1][j] also have A B C conditions, which may match M[i - 2][j] recursively M[i - k][j] which '*' matches length k substring in s
     *      2. If s[i] == p[j] || p[j] == '?' M[i][j] = M[i - 1][j - 1]
     *      3. If s[i] != p[j] && p[j] != '?' M[i][j] = false, the last character of current substring s and p will not possible be match
     * return M[row - 1][col - 1]
     *
     * Time: O(mn) which m represent length of s, n represent length of p
     * Space: O(mn) dp matrix
     */

    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0 || j == 0) {
                    //"" matches ""
                    if (i == 0 && j == 0) dp[i][j] = true;
                    //When i == 0, and first j characters are '*', dp[i][j] = true, after first p[j] != '*', are false
                    else if (i == 0 && p.charAt(j - 1) == '*') dp[i][j] = dp[i][j - 1];
                } else {
                    //Explained in induction rule
                    if (p.charAt(j - 1) == '*') dp[i][j] = dp[i - 1][j] || dp[i - 1][j - 1] || dp[i][j - 1];
                    else if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
