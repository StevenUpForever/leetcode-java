package binary_search;

public class Q69SqrtX {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: binary search

    /**
     * 69. Sqrt(x)
     * Implement int sqrt(int x).
     *
     * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
     *
     * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
     *
     * Example 1:
     *
     * Input: 4
     * Output: 2
     * Example 2:
     *
     * Input: 8
     * Output: 2
     * Explanation: The square root of 8 is 2.82842..., and since
     *              the decimal part is truncated, 2 is returned.
     */

    /*
    Solution:
    Binary search from 0 to x, find equal mid = x/mid or largest smaller mid < x/mid

    Time: O(log(x))
    Space: O(1)
     */

    public int mySqrt(int x) {
        if (x == 0) return 0;
        int l = 0, r = x;
        while (l < r - 1) {
            int mid = l + (r - l)/2;
            if (mid == x/mid) return mid;
            else if (mid > x/mid) r = mid - 1;
            else l = mid;
        }
        return r <= x/r ? r : l;
    }

}
