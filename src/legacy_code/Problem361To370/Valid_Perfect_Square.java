package legacy_code.Problem361To370;

public class Valid_Perfect_Square {

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
     */

    public boolean isPerfectSquare(int num) {
        long res = num;
        while (res * res > num)
            res = (res + num/res) / 2;
        return res * res == num;
    }

}
