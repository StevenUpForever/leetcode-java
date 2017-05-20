package Problem61To70;

public class Sqrt_x {

    /**
     * 69. Sqrt(x)
     * Implement int sqrt(int x).

     Compute and return the square root of x.
     */

    /**
     * Solution 1: Brute force
     * loop from 1 to x as i
     *      Calculate the result of i * i until > x
     * then i - 1 is the result
     *
     * Time: O(n)
     * Space: O(1)
     */

    /**
     * Solution 2: binary search
     * First we know the scope of the result is 1...x
     * Do a post step binary search, assume has no a number i which i*i == x (may < x or > x)
     * Then we run the binary search, find the equal or most smaller number i which i * i <= x
     * return this i
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public int mySqrtS2(int x) {
        if (x == 0) return 0;
        int left = 1, right  = x;
        //Leave two numbers at last for the post step
        while (left < right - 1) {
            int mid = left + (right - left)/2;
            //use mid == x/mid instead of mid * mid == x avoid integer overflow
            if (mid == x/mid) return mid;
            else if (mid > x/mid) right = mid;
            else left = mid;
        }
        return left;
    }

    /**
     * Solution 3: Newton's method
     * Integer square root: https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
     * Newton's method: https://en.wikipedia.org/wiki/Newton%27s_method
     * Formula: x(k+1) = 1/2*(x(k) + n/x(k)) until x^2 - n == 0
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public int mySqrtS3(int x) {
        long res = x;
        //Newton's method formula
        while (res * res > x) res = (res + x/res) / 2;
        return (int) res;
    }

}
