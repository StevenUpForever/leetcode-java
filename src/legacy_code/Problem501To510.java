package legacy_code;

/**
 * Created by ChengzhiJia on 3/25/17.
 */
public class Problem501To510 {
    /*
    507. Perfect Number
    We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.

Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
Example:
Input: 28
Output: True
Explanation: 28 = 1 + 2 + 4 + 7 + 14
Note: The input number n will not exceed 100,000,000. (1e8)
     */
    public boolean checkPerfectNumber(int num) {
        if (num <= 0) return false;
        int res = 0;
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                res += i;
                if (i != num/i) res += num/i;
            }
        }
        return res == 2 * num;
    }
}
