package recursion;

public class PowXN {

    //Difficulty: Medium
    //TAG: Google
    //TAG: LinkedIn
    //TAG: Math
    //TAG: Apple
    //TAG: Recursion

    /**
     * 50. Pow(x, n)
     * Implement pow(x, n).
     */

    /*
     * Solution 1: recursion
     * Base case: if n == 0, return 1, if n == 1, return x
     * recursion rule: recursively divd n by 2 each time, and x^n = x^n/2 * x^n/2
     *      when n < 0 && n % 2 != 0, multiple by 1/x, when n > 0 && n % 2 != 0 multiple by x
     *
     * Time: O(logn)
     * Space: O(logn)
     */

    public double myPowS1(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;
        double recurValue = myPowS1(x, n/2);
        if (n % 2 == 0) return recurValue * recurValue;
        /*
        if n % 2 == 0, recursion will fall back when n == 1, go this step
                if n < 0, recurValue will became 1/x when n == 1, else fall back steps n % 2 == 0, but recurValue already 0 < value < 1, so value * value will still 0 < value < 1
         */
        else return n < 0 ? recurValue * recurValue * 1/x : recurValue * recurValue * x;
    }

    /**
     * Solution 2: Iterative
     * Use while loop instead of recursion method, will decrease space
     *
     * Time: O(logn)
     * Space: O(1)
     */

    public double myPowS2(double x, int n) {
        if (x == 0) return 1;
        //In case than n is Int.min, cannot use -n as opposite number
        long longN = Math.abs((long)n);
        double res = 1;
        while (longN > 0) {
            //Same as n & 1 == 1
            /*
            if n is odd number, this step will run when begin and longN reduced to 1
            if n is even number, this step will run when longN == 1
             */
            if (n % 2 != 0) res *= x;
            x *= x;
            //Same as longN >>= 1
            longN /= 2;
        }
        return n < 0 ? 1/res : res;
    }

}
