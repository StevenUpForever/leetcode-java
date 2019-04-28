package dynamic_programming.two_d_dp;

public class Q1035UncrossedLines {

    //Difficulty: medium
    //TAG: dp
    //TAG: longest common sub-sequence

    /**
     * 1035. Uncrossed Lines
     * We write the integers of A and B (in the order they are given) on two separate horizontal lines.
     *
     * Now, we may draw a straight line connecting two numbers A[i] and B[j] as long as A[i] == B[j], and the line we draw does not intersect any other connecting (non-horizontal) line.
     *
     * Return the maximum number of connecting lines we can draw in this way.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: A = [1,4,2], B = [1,2,4]
     * Output: 2
     * Explanation: We can draw 2 uncrossed lines as in the diagram.
     * We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
     * Example 2:
     *
     * Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
     * Output: 3
     * Example 3:
     *
     * Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
     * Output: 2
     *
     *
     * Note:
     *
     * 1 <= A.length <= 500
     * 1 <= B.length <= 500
     * 1 <= A[i], B[i] <= 2000
     */

    /*
    Solution:

    same to longest common sub-sequence, two dimensional dp:
    if A[i] == B[j], means could connect, and before possible lines should not cover A[i] or B[j],
    so dp[i][j] = dp[i - 1][j - 1] + 1
    else when A[i] != B[j] try max of dp[i - 1][j], dp[i][j - 1]

    Time: O(mn)
    Space: O(mn)
     */

    public int maxUncrossedLines(int[] A, int[] B) {
        int len1 = A.length, len2 = B.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

}
