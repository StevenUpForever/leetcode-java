package math;

public class Q479LargestPalindromeProduct {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: math

    /**
     * 479. Largest Palindrome Product
     * Find the largest palindrome made from the product of two n-digit numbers.
     *
     * Since the result could be very large, you should return the largest palindrome mod 1337.
     *
     *
     *
     * Example:
     *
     * Input: 2
     *
     * Output: 987
     *
     * Explanation: 99 x 91 = 9009, 9009 % 1337 = 987
     *
     *
     *
     * Note:
     *
     * The range of n is [1,8].
     */

    /*
    Solution:
    find number from max scope to min scope, find any num append with reverse num
    e.g. 2731 append with 1372 to 27311372, then verify if a sub from sqrt(num) to max that
    num/sqrt == 0 && num / sqrt < max, which is a fit number
     */

    private long[] scope = new long[]{1,10,100,1000,10000,100000,1000000,10000000,100000000};
    long getReverse(long num, int n){
        long ret = 0;
        for (int i=1; i<=n; i++){
            ret += (num%10)*scope[n-i];
            num /= 10;
        }
        return ret;
    }

    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        long max = scope[n], min = scope[n-1];
        for (long i = max - 1; i > min; i--){
            long num = i * max + getReverse(i, n);
            for (long factor = (long)Math.sqrt(num); factor < max; factor++)
                if (num % factor==0 && num/factor < max)
                    return (int)(num%1337);
        }
        return -1;
    }

}
