package bit_operation;

public class PowerOfTwo {

    //TAG: Google
    //TAG: Recursion
    //TAG: Bit operation
    //Difficulty: Easy

    /**
     * 231. Power of Two
     * Given an integer, write a function to determine if it is a power of two.

     Credits:
     Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.
     */

    /**
     * Solution 1:
     * keep divide the number by 2 until 1 or some number which num % 2 != 0
     *
     * Time: O(logn)
     * Space: O(1)
     */

    /**
     * Solution 2:
     * bit operation, due if power by 2, it should be 10, 100, 1000, 10000 etc..., so num - 1 is 01, 011, 0111, 01111 etc... which means num & num - 1 == 0
     *
     * Time: O(1)
     * Space: O(1)
     */

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

}
