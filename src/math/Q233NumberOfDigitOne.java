package math;

public class Q233NumberOfDigitOne {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: array
    //TAG: math
    //TODO: https://leetcode.com/problems/number-of-digit-one/discuss/64390/AC-short-Java-solution

    /**
     * 233. Number of Digit One
     * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
     *
     * Example:
     *
     * Input: 13
     * Output: 6
     * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
     */

    public int countDigitOne(int n) {
        int count = 0;

        for (long k = 1; k <= n; k *= 10) {
            long r = n / k, m = n % k;
            // sum up the count of ones on every place k
            count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }

        return count;
    }

}
