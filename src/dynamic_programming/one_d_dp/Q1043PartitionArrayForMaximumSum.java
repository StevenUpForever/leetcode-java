package dynamic_programming.one_d_dp;

public class Q1043PartitionArrayForMaximumSum {

    //Difficulty: medium
    //TAG: dp

    /**
     * 1043. Partition Array for Maximum Sum
     * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.  After partitioning, each subarray has their values changed to become the maximum value of that subarray.
     *
     * Return the largest sum of the given array after partitioning.
     *
     *
     *
     * Example 1:
     *
     * Input: A = [1,15,7,9,2,5,10], K = 3
     * Output: 84
     * Explanation: A becomes [15,15,15,9,10,10,10]
     *
     *
     * Note:
     *
     * 1 <= K <= A.length <= 500
     * 0 <= A[i] <= 10^6
     */

    /*
    Solution:

    dp problem, dp[i] represent max sum by partition until index i

    induction rule: j represent partition size, then j from 1...K, but need check i >= j - 1 due to cannot over i
    find max number in 1...K, and max * j is sum of max partition dp[j], dp[i] = max(dp[i - j] + max * j)

    Time: O(nk)
    Space: O(n)
     */

    public int maxSumAfterPartitioning(int[] A, int K) {
        int[] dp = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            int max = 0;
            for (int j = 1; j <= K && i >= j - 1; j++) {
                max = Math.max(max, A[i - j + 1]);
                dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + max * j);
            }
        }
        return dp[A.length - 1];
    }

}
