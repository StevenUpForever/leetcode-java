public class PerfectSquares {

    //TAG: Google
    //TAG: DP
    //Difficulty: medium

    /**
     * 279. Perfect Squares
     * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

     For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
     */

    /**
     * Solution:
     * Due to need min number of perfect square numbers, DP may a good solution
     *
     * Base case: dp[0] = 1;
     * induction rule: dp[i] represent least number of perfect square numbers of i
     * which loop i from 1 to n
     *      loop j from 1 to i which increment is j * j
     *      dp[i] = min(dp[i], dp[i - j * j] + 1)
     * return dp[len - 1]
     *
     * Time: O(n^2)
     * Space: O(n)
     */

    public int numSquares(int n) {
        /*
        initial dp with length n + 1 so dp[i] will reference to i, and default values are Int.MAX, so could
        keep updating min
         */
        int dp[] = new int[n + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        //Initial dp[0] == 0 indicate that this number is not existed
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            /*
            Need make j * j <= i so when j * j == 1 dp[i] could just be 1
            Check i is a perfect square number is a better choice (could just skip) but need more code for this
             */
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[dp.length - 1];
    }

}
