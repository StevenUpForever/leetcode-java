package math;

public class Q7ReverseInteger {

    /**
     * 7. Reverse Integer
     * Given a 32-bit signed integer, reverse digits of an integer.
     *
     * Example 1:
     *
     * Input: 123
     * Output: 321
     * Example 2:
     *
     * Input: -123
     * Output: -321
     * Example 3:
     *
     * Input: 120
     * Output: 21
     * Note:
     * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
     */

    /*
    Solution:
    Reverse number by int newRes = res * 10 + x % 10, don't need to care if x < 0 or not
    x < 0 will fit for the rule
    once new result/10 != old result, means old * 10 + mod over integer limit, then return 0
    otherwise finally return res

    Time: O(n)
    Space: O(1)
     */

    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int newRes = res * 10 + x % 10;
            if (newRes/10 != res) return 0;
            res = newRes;
            x /= 10;
        }
        return res;
    }

}
