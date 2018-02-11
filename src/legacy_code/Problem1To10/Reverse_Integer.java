package legacy_code.Problem1To10;

public class Reverse_Integer {

    /**
     * 7. Reverse Integer
     * Reverse digits of an integer.

     Example1: x = 123, return 321
     Example2: x = -123, return -321

     click to show spoilers.

     Note:
     The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.

     Hide Company Tags
     */

    /**
     * Solution 1:
     * Keep a flag to indicate the positive/negative number
     * keep a Long type result, it will not over limit even the largest bits number by Integer length, and do post step if Long result is out of Integer scope
     *
     * Time: O(len(num))
     * Space: O(1)
     */

    public int reverseS1(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        return (int)result;
    }

    /**
     * Solution 2:
     * If cannot use a longer number type, use int instead, but need to verify if result is met the Int.max or Int.min
     * because when number is over Int.max, Int.max + 1 == Int.min, Int.min - 1 = Int.max, so currently newRes /10 != oldRes (before * 10)
     *
     * Time: O(len(num))
     * Space: O(1)
     */

    public int reverseS2(int x) {
        int result = 0;
        while (x != 0) {
            int newRes = result * 10 + x % 10;
            if (newRes / 10 != result) return 0;
            result = newRes;
            x /= 10;
        }
        return result;
    }

}
