package Problem21To30;

public class Divide_Two_Integers {

    /**
     * 29. Divide Two Integers
     * Divide two integers without using multiplication, division and mod operator.
     If it is overflow, return MAX_INT.
     */

    /**
     * Solution 1:
     * Use add, assume current add count is n, keep dividend and divisor positive numbers and keep a flag to represent the result is a positive or negative number
     *      1. when add n times is larger than dividend, return n - 1
     *      2. when add n times is equal to dividend, return n
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int divideS1(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        int flag = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        long divd = Math.abs(dividend);
        long divs = Math.abs(divisor);
        long cur = divisor;
        int n = 1;
        while (cur < divd) {
            cur += divs;
            n++;
        }
        return flag * (cur == divd ? n : n - 1);
    }

    /**
     * Solution 2:
     * bit shift is similar as multiple, 2 << 1 == 2 * 2 == 4, << 1 is faster than + 1
     * loop until divd (decrease) is equal or smaller than divs
     *      Multiple divd and multiple by 2 each time (shift << 1) until divs smaller than divd
     *      minus current added divs from divd
     *      add current added multiple to result
     *  be aware of when result (long type) is larger than Int.MAX, otherwise use result value itself
     *
     *  Time: O((logn)^2)
     *  Space: O(1)
     */

    public int divideS2(int dividend, int divisor) {
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) return Integer.MAX_VALUE;
        int flag = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        //Be aware of the long type in Math.abs() method, Math.abs(Integer.MIN_VALUE) = -2147483648, Math.abs((long)Integer.MIN_VALUE) = -2147483648
        long divd = Math.abs((long) dividend);
        long divs = Math.abs((long) divisor);
        //Set result temporarily to long, it may integer overflow when result should be Int.min and now it's 2147483648
        long res = 0;
        while (divd >= divs) {
            long temp = divs, multiple = 1;
            //divd >= (temp << 1) make sure when end the loop, temp is smaller or equal
            //Inner loop is make a temp smaller or equal to divd, because it's not increase by divs, but increase by itself as << 1, so it may not properly divided once, need to go rest of number and do << 1
            /*
            For example, when divd == 26, divs == 2
            when first time the inner loop done, divs = 16, 32 will over 26, this time divd = 26 - 16 = 10
            next loop finally divs = 8, divd = 10 - 8 = 2
            Third loop will done
             */
            while (divd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            divd -= temp;
            res += multiple;
        }
        if (res > Integer.MAX_VALUE) return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        return (int)(flag == 1 ? res : -res);
    }

}
