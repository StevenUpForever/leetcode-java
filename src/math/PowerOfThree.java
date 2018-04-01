package math;

public class PowerOfThree {

    //TAG: Google
    //TAG: math
    //Difficulty: Easy

    /**
     * 326. Power of Three
     * Given an integer, write a function to determine if it is a power of three.

     Follow up:
     Could you do it without using any loop / recursion?

     */

    /**
     * loop solution:
     * filter number <= 0, then loop by n /= 3 just make sure n % 3 == 0
     * finally check if n == 1, due to any number that not power of 3 finally will be a non 3 related number
     *
     * Time: O(log3 N)
     * Space: O(1)
     */

    public boolean isPowerOfThree(int n) {
        if (n > 0) {
            while (n % 3 == 0) {
                n /= 3;
            }
        }
        return n == 1;
    }

    /**
     * Non loop solution:
     * due to n is int, so use max number smaller than Integer.MAX_VALUE % n check if n > 0 and the mode == 0
     * How to know the largest power of 3 smaller than Integer.MAX_VALUE, e.g.
     *
     * long res = 1;
     * while (res < Integer.MAX_VALUE) {
     *      res *= 3;
     * }
     * System.out.println(res/3);
     *
     * Time: O(1)
     * Space: O(1)
     */

    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

}
