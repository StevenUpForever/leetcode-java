package dynamic_programming.two_d_dp;

public class Q97InterleavingString {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: dfs
    //TAG: dp

    /**
     * 97. Interleaving String
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     *
     * Example 1:
     *
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     * Example 2:
     *
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * Output: false
     */

    /*
    Solution 1:
    dfs, recursion to all steps that s1.charAt(i) == s3.charAt(index) || s1.charAt(j) == s3.charAt(index)

    Time: O(2^s3.length)
    Space: O(s3.length)
     */

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        return dfs(s1, 0, s2, 0, s3, 0);
    }

    private boolean dfs(String s1, int i, String s2, int j, String s3, int index) {
        if (index == s3.length()) return true;
        boolean res = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(index)) {
            res |= dfs(s1, i + 1, s2, j, s3, index + 1);
        }
        if (j < s2.length() && s2.charAt(j) == s3.charAt(index)) {
            res |= dfs(s1, i, s2, j + 1, s3, index + 1);
        }
        return res;
    }

    /*
    Solution: dp
    two dimensional dp
    base case: dp[0][0] = true means "" and "" matches ""
    induction rule: dp[i][j] means if s3.substring(i + j - 1) matches s1.sub(i) and s2.sub(j)
    check if current char c1 and c2 matches c3 in s3, which c3 = s3.charAt(i + j - 1)
    Note that if c1 != c3, do not match dp[i][j] with dp[i - 1][j], same to s2

    Time: O(s1*s2)
    Space: O(s1*s2)
     */

    public boolean isInterleave2(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) return false;
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        for (int i = 0; i < len1 + 1; i++) {
            for (int j = 0; j < len2 + 1; j++) {
                if (i == 0 && j == 0) dp[i][j] = true;
                else {
                    char c3 = s3.charAt(i + j - 1);
                    if (i > 0 && c3 == s1.charAt(i - 1)) dp[i][j] |= dp[i - 1][j];
                    if (j > 0 && c3 == s2.charAt(j - 1)) dp[i][j] |= dp[i][j - 1];
                }
            }
        }
        return dp[len1][len2];
    }

}
