package dynamic_programming.two_d_dp;

public class RegularExpressionMatching {

    //TAG: Google
    //TAG: Facebook
    //TAG: Uber
    //TAG: 2D DP
    //Difficulty: Hard

    /**
     * 10. Regular Expression Matching
     * Implement regular expression matching with support for '.' and '*'.

     '.' Matches any single character.
     '*' Matches zero or more of the preceding element.

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "a*") → true
     isMatch("aa", ".*") → true
     isMatch("ab", ".*") → true
     isMatch("aab", "c*a*b") → true
     */

    /**
     *
    Solution:
     2D DP question, the sample matrix is as below:
        ""  c   *   a   *   b
     "" T   F   T   F   T   F
     a  F   F   T   T   T   F
     a  F   F   T   T   T   F
     b  F   F   T   F   T   T

     which M[0][0] = true, "" is matched with "", M[i][j] represent s.subString[0, i] is matched with p.subString[0, j] or not
     1. base case:
        when s is ""
            when current char is *, use pre matrix value i - 1 as current value
            otherwise is false
        when p is ""
            s is true only if when s is "" too, otherwise is false
    1.  if s[i] == p[j] || p[j] == '.', this i and j will match, dp[i][j] = dp[i - 1][j - 1]
    2.  if p[j] == '*', be aware that * represent any number of pre node, itself do not represent a character, there's multiple conditions
        1. if p[j - 1] != '.' && s[i] != p[j - 1] it means s[i] and  p[j - 1] can not match, dp[i][j] = dp[i][j - 1]
        2. else
            1. dp[i][j] = dp[i][j - 1]   a* when * is 1
            2. dp[i][j] = dp[i][j - 2]   a* when * is 0
            3. dp[i][j] = dp[i - 1][j]   a* when * is larger than 1, it's like a for loop, for every m < i, if s[m] could match at least p[j - 1], means this * works increase by 1, dp[i + 1][j] result is induction from result[i][j] if j == *
        dp[i][j] = case 1 || case 2 || case 3
     3. return the right bottom corner value of dp matrix as the result

     Time: O(mn)
     Space: O(mn)
     */

    public boolean isMatch(String s, String p) {
        char[] char1 = s.toCharArray(), char2 = p.toCharArray();
        boolean[][] dp = new boolean[char1.length + 1][char2.length + 1];
        //When s == "" and p == "" is matched, value is true
        dp[0][0] = true;
        //Pre steps when s == "" but p is not "", when current p is '*', and pre value is true, current matrix value is true, otherwise false
        for (int i = 0; i < char2.length; i++) {
            if (char2[i] == '*' && dp[0][i-1]) dp[0][i + 1] = true;
        }
        //i and j start from 1 to matrix length (s + 1, p + 1), no need to check matrix[i][0], it means s is not "" and p is "", all value should be false
        for (int i = 1; i <= char1.length; i++) {
            for (int j = 1; j <= char2.length; j++) {
                    //If current character matched or p is '.', use value from left top corner [i - 1, j - 1]
                    if (char1[i - 1] == char2[j - 1] || char2[j - 1] == '.') dp[i][j] = dp[i - 1][j - 1];
                    //Otherwise when character is '*', have 3 conditions as explained above
                    else if (char2[j - 1] == '*') {
                        if (j == 1) dp[i][j] = true;
                        else if (char2[j - 2] != char1[i - 1] && char2[j - 2] != '.') dp[i][j] = dp[i][j - 2];
                        else dp[i][j] = dp[i - 1][j] || dp[i][j - 1] || dp[i][j - 2];
                    }
            }
        }
        return dp[char1.length][char2.length];
    }

}
