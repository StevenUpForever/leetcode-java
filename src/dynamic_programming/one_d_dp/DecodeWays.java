package dynamic_programming.one_d_dp;

public class DecodeWays {

    //Facebook
    //TAG: Uber
    //TAG: DFS
    //TAG: DP
    //Difficulty: Medium

    /**
     * 91. Decode Ways
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:

     'A' -> 1
     'B' -> 2
     ...
     'Z' -> 26
     Given an encoded message containing digits, determine the total number of ways to decode it.

     For example,
     Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

     The number of ways decoding "12" is 2.
     */

    /**
     * Solution 1: permutation
     * Permutation all possible combinations of the string s, recursion tree like
     *              1234
     *           /        \
     *         A234      L34
     *        /   \      / \
     *      AB34 AW4   LC4 no-more
     *      ......
     * count all possible combinations and return the count
     *
     * Time: O(2^n)
     * Space: O(n) recursion tree levels
     */

    /**
     * Solution 2: DP
     * M[i] represent the max decode ways for substring to i (due to number, from back then start)
     * Base case:
     *      M[len] = 1 (initial step)
     *      M[len -1] = s[len - 1] == '0' ? 1 : 0 (if first char is '0' no matched character, otherwise 1 - 9 is A - I)
     * Induction rule:
     *      if s[i + 1] + s[i + 2] > 26, there's only one way to separate it, so M[i] = M[i + 1]
     *      otherwise have two conditions,
     *          when treat s[i + 1] + s[i + 2] as one part, it's same as > 26, M[i] = M[i + 1]
     *          when separate to two parts, treat M[i + 1] as the one part, so use M[i + 2], so use M[i + 1] + M[i + 2]
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        //Prepare for loop that dp[i + 1] will not over scope, when i = len - 1
        dp[len]  = 1;
        dp[len - 1] = chars[len - 1] != '0' ? 1 : 0;
        for (int i = len - 2; i >= 0; i--) {
            //No extra step for s[i + 1] + s[i + 2] > 26, as under this condition, M[i] = M[i + 1]
            /*
            Otherwise,
                if <= 26, has two ways of combinations, so dp[i] = dp[i + 1] + dp[i + 2]
                else dp[i] = dp[i + 1]
             */
            if (chars[i] != '0')
                dp[i] = (chars[i] - '0') * 10 + chars[i + 1] - '0' <= 26 ? dp[i + 1] + dp[i + 2] : dp[i + 1];
        }
        return dp[0];
    }

}
