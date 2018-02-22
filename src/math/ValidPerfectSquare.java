package math;

public class ValidPerfectSquare {

    //TAG: LinkedIn
    //TAG: math
    //Difficulty: Easy

    /**
     * 367. Valid Perfect Square
     * Given a positive integer num, write a function which returns True if num is a perfect square else False.

     Note: Do not use any built-in library function such as sqrt.

     Example 1:

     Input: 16
     Returns: True
     Example 2:

     Input: 14
     Returns: False
     */

    /**
     * Solution:
     * Same as Sqrt_x (legacy_code.Problem61To70), binary reduction, just verify last step if res*res is exactly equals to num
     *
     * 1, 3, 5, 7 ... sequence sum will be a perfect number
     *
     * Time: O(Sqt(n))
     * Space: O(1)
     */

    public boolean isPerfectSquareS1(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /**
     * Binary search
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public boolean isPerfectSquareS2(int num) {
        int low = 1, high = num;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    /**
     * Newton method
     *
     * https://en.wikipedia.org/wiki/Newton%27s_method
     */

    public boolean isPerfectSquare(int num) {
        long res = num;
        while (res * res > num)
            res = (res + num/res) / 2;
        return res * res == num;
    }

}
