package math;

public class Q507PerfectNumber {

    //Difficulty: easy
    //TAG: math

    /**
     * 507. Perfect Number
     * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
     *
     * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
     * Example:
     * Input: 28
     * Output: True
     * Explanation: 28 = 1 + 2 + 4 + 7 + 14
     * Note: The input number n will not exceed 100,000,000. (1e8)
     */

    /*
    Solution:

    base case if num == 1 return false
    loop from 2 to sqrt(num) add all divider and divisors to sum
    if sum > num return false immediately

    finally after loop check if sum == num (due to sum may smaller than num)

    Time: O(sqrt(num))
    Space: O(1)
     */

    public boolean checkPerfectNumber(int num) {
        if (num < 1) return false;
        int sum = 1;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                sum += i + num/i;
            }
            if (sum > num) return false;
        }
        return sum == num;
    }

}
