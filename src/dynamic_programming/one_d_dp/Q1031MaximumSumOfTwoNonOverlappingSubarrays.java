package dynamic_programming.one_d_dp;

public class Q1031MaximumSumOfTwoNonOverlappingSubarrays {

    //Difficulty: medium
    //TAG: dp

    /**
     * 1031. Maximum Sum of Two Non-Overlapping Subarrays
     * Given an array A of non-negative integers, return the maximum sum of elements in two non-overlapping (contiguous) subarrays, which have lengths L and M.  (For clarification, the L-length subarray could occur before or after the M-length subarray.)
     *
     * Formally, return the largest V for which V = (A[i] + A[i+1] + ... + A[i+L-1]) + (A[j] + A[j+1] + ... + A[j+M-1]) and either:
     *
     * 0 <= i < i + L - 1 < j < j + M - 1 < A.length, or
     * 0 <= j < j + M - 1 < i < i + L - 1 < A.length.
     *
     *
     * Example 1:
     *
     * Input: A = [0,6,5,2,2,5,1,9,4], L = 1, M = 2
     * Output: 20
     * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
     * Example 2:
     *
     * Input: A = [3,8,1,3,2,1,8,9,0], L = 3, M = 2
     * Output: 29
     * Explanation: One choice of subarrays is [3,8,1] with length 3, and [8,9] with length 2.
     * Example 3:
     *
     * Input: A = [2,1,5,6,0,9,5,0,3,8], L = 4, M = 3
     * Output: 31
     * Explanation: One choice of subarrays is [5,6,0,9] with length 4, and [3,8] with length 3.
     *
     *
     * Note:
     *
     * L >= 1
     * M >= 1
     * L + M <= A.length <= 1000
     * 0 <= A[i] <= 1000
     */

    /*
    Solution 1:

    for for loop find max sum, i loop for length L subarray
    j loop from 0 to start of L subarray and end of subarray L to end of A

    Time: O(n^2)
    Space: O(1)
     */

    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        if (L < 0 || M < 0 || L + M > A.length) return 0;
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i - 1];
        }
        int max = 0;
        for (int i = L - 1; i < A.length; i++) {
            int sum1 = A[i] - (i - L >= 0 ? A[i - L] : 0);
            for (int j = M - 1; j <= i - L; j++) {
                int sum2 = A[j] - (j - M >= 0 ? A[j - M] : 0);
                max = Math.max(max, sum1 + sum2);
            }
            for (int j = i + M; j < A.length; j++) {
                int sum2 = A[j] - A[j - M];
                max = Math.max(max, sum1 + sum2);
            }
        }
        return max;
    }

    /*
    Solution 2:

    https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278251/JavaC%2B%2BPython-O(N)Time-O(1)-Space

    DP

    loop from L + M to end

    every time find max Lmax that before at least M elements and sum of last M elements
    Also max Mmax before at least L elements and sum of last L elements

    update max

    Time: O(n)
    Space: O(1)
     */

    public int maxSumTwoNoOverlap2(int[] A, int L, int M) {
        for (int i = 1; i < A.length; ++i)
            A[i] += A[i - 1];
        int res = A[L + M - 1], Lmax = A[L - 1], Mmax = A[M - 1];
        for (int i = L + M; i < A.length; ++i) {
            Lmax = Math.max(Lmax, A[i - M] - A[i - L - M]);
            Mmax = Math.max(Mmax, A[i - L] - A[i - L - M]);
            res = Math.max(res, Math.max(Lmax + A[i] - A[i - M], Mmax + A[i] - A[i - L]));
        }
        return res;
    }



}
