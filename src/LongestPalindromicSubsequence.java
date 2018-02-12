import java.util.Stack;

public class LongestPalindromicSubsequence {

    //TAG: Uber
    //TAG: 2D DP

    /**
     * 516. Longest Palindromic Subsequence
     * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

     Example 1:
     Input:

     "bbbab"
     Output:
     4
     One possible longest palindromic subsequence is "bbbb".
     Example 2:
     Input:

     "cbbd"
     Output:
     2
     One possible longest palindromic subsequence is "bb".

     */

    /**
     * Solution (Recursion):
     * Permutation all of sub sequences and check if any is palindrome, keep global max
     * Time: O(n!)
     * Space: O(n)
     */

    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) return 0;
        StringBuilder builder = new StringBuilder();
        int[] max = new int[]{0};
        longestPalindromeSubseqHelper(s, builder, max, 0);
        return max[0];
    }

    private void longestPalindromeSubseqHelper(String s, StringBuilder builder, int[] max, int index) {
        if (isPalindrome(builder)) max[0] = Math.max(max[0], builder.length());
        if (index >= s.length()) return;
        for (int i = index; i < s.length(); i++) {
            builder.append(s.charAt(i));
            longestPalindromeSubseqHelper(s, builder, max, i + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

    private boolean isPalindrome(StringBuilder builder) {
        int i = 0, j = builder.length() - 1;
        while (i < j && builder.charAt(i) == builder.charAt(j)) {
            i++;
            j--;
        }
        return i >= j;
    }

    /**
     * Solution (DP):
     * check palindrome from middle to two ends
     * Base case: dp[i][i] = 1, which means 1 char will be palindrome
     * induction rule: loop j from i + 1 to len - 1
     *      if s[i] == s[j] dp[i][j] = dp[i - 1][j + 1] + 2
     *      else dp[i][j] = Math.max(Math.max(dp[i + 1][j], dp[i][j - 1]), dp[i + 1][j - 1])
     * return right top value dp[0][len - 1]
     *
     * Time: O(n^2)
     * Space: O(n^2)
     */

    public int longestPalindromeSubseqS2(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(Math.max(dp[i + 1][j], dp[i][j - 1]), dp[i + 1][j - 1]);
            }
        }
        return dp[0][s.length() - 1];
    }



}
