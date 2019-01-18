package depth_first_search.ninety_nine_cents;

import java.util.Arrays;

public class CoinChange {

    //Difficulty: Medium
    //TAG: Airbnb
    //TAG: DFS
    //TAG: DP


    /**
     * 322. Coin Change
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
     *
     * Example 1:
     *
     * Input: coins = [1, 2, 5], amount = 11
     * Output: 3
     * Explanation: 11 = 5 + 5 + 1
     * Example 2:
     *
     * Input: coins = [2], amount = 3
     * Output: -1
     * Note:
     * You may assume that you have an infinite number of each kind of coin.
     */

    /*
    Solution:
    1. optimized recursion
    99 cent problem, key is when add current number of coins will over global min count, then return, keep going even if
    find a solution, it cannot be a min step

    Time: O(n ^ (amount / min))
    Space: O(n)
     */

    private int minCount = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        if (coins == null || amount < 0) return 0;
        Arrays.sort(coins);
        coinChangeHelper(coins, coins.length - 1, amount, 0);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }

    private void coinChangeHelper(int[] coins, int index, int amount, int count) {
        if (index < 0 || amount <= 0) {
            if (amount == 0) {
                minCount = Math.min(minCount, count);

            }
            return;
        }
        //try to fill the largest coin first
        for (int i = amount/coins[index]; i >= 0; i--) {
            //optimize, when next steps will exactly over min no matter find a solution or not, exit this recursion branch
            if (count + i >= minCount) return;
            coinChangeHelper(coins, index - 1, amount - coins[index] * i, count + i);
        }
    }

    /*
    Solution:
    2. DP

    Time: O(mn)
    Space: O(m)
     */

    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }



}
