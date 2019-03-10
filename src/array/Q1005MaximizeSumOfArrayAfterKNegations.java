package array;

import java.util.Arrays;

public class Q1005MaximizeSumOfArrayAfterKNegations {

    //Difficulty: easy
    //TAG: array

    /**
     * 1005. Maximize Sum Of Array After K Negations
     * Given an array A of integers, we must modify the array in the following way: we choose an i and replace A[i] with -A[i], and we repeat this process K times in total.  (We may choose the same index i multiple times.)
     *
     * Return the largest possible sum of the array after modifying it in this way.
     *
     *
     *
     * Example 1:
     *
     * Input: A = [4,2,3], K = 1
     * Output: 5
     * Explanation: Choose indices (1,) and A becomes [4,-2,3].
     * Example 2:
     *
     * Input: A = [3,-1,0,2], K = 3
     * Output: 6
     * Explanation: Choose indices (1, 2, 2) and A becomes [3,1,0,2].
     * Example 3:
     *
     * Input: A = [2,-3,-1,5,-4], K = 2
     * Output: 13
     * Explanation: Choose indices (1, 4) and A becomes [2,3,-1,5,4].
     *
     *
     * Note:
     *
     * 1 <= A.length <= 10000
     * 1 <= K <= 10000
     * -100 <= A[i] <= 100
     */

    /*
    Solution:
    Sort the array then we are easy flip num < 0
    Try to flip all num < 0 once and add to sum, cases after this step:
    1. K == 0 and few more num < 0
        add all to sum and return
    2. K % 2 == 0, we could flip even number of any numbers, and all nums > 0
        add all to sum
    3. K % 2 == 1, we need find a number and make it < 0, which we find the min in array
        sum -= 2 * min after add all

    Time: O(nlogn + n)
     */

    public int largestSumAfterKNegations(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        Arrays.sort(A);
        int index = 0, sum = 0, min = Math.abs(A[0]);
        //add max K numbers in positive flip
        while (index < A.length && A[index] < 0 && K > 0) {
            sum += Math.abs(A[index]);
            min = Math.min(min, Math.abs(A[index]));
            index++;
            K--;
        }
        //try to add all rest numbers (no matter < or > 0), find min in the array
        while (index < A.length) {
            sum += A[index];
            min = Math.min(min, Math.abs(A[index]));
            index++;
        }
        //If we need flip one positive number, then we already add all to sum, we need sum - 2 * min
        if (K % 2 != 0) {
            sum -= 2 * min;
        }
        return sum;
    }

}
