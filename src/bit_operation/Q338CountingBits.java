package bit_operation;

public class Q338CountingBits {

    //Difficulty: medium
    //TAG: Apple
    //TAG: bit operation
    //TAG: dp

    /**
     * 338. Counting Bits
     * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
     *
     * Example 1:
     *
     * Input: 2
     * Output: [0,1,1]
     * Example 2:
     *
     * Input: 5
     * Output: [0,1,1,2,1,2]
     * Follow up:
     *
     * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
     * Space complexity should be O(n).
     * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
     */

    /*
    Solution:
    Due to try get result in one pass, so we try to use dp, and connect current dp[i] with previous results
    in binary, we connect the number to number >> 1 which is number/2,
    if we know how many 1s in dp[i >> 1] then we add 1 if i last bit is 1 then it's dp[i]

    Time: O(n)
    Space: O(n)
     */

    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i=1; i<=num; i++) dp[i] = dp[i >> 1] + (i & 1);
        return dp;
    }

}
