package array;

public class Q204CountPrimes {

    //Difficulty: easy
    //TAG: Apple
    //TAG: array
    //TAG: math

    /**
     * 204. Count Primes
     * Count the number of prime numbers less than a non-negative number, n.
     *
     * Example:
     *
     * Input: 10
     * Output: 4
     * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
     */

    /*
    Solution:
    key is use a n length array record any non prime number, to save verify time of each number
    when loop current number i and i is prime number, try multiple i by i * 2, i * 3 ... any number like this
    will not be prime number

    Time: O(n)
    Space: O(n)
     */

    public int countPrimes(int n) {
        int count = 0;
        boolean[] prime = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!prime[i]) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    prime[i * j] = true;
                }
            }
        }
        return count;
    }

}
