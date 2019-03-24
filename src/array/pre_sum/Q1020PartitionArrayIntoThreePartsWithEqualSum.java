package array.pre_sum;

public class Q1020PartitionArrayIntoThreePartsWithEqualSum {

    //Difficulty: Easy
    //TAG: Array

    /**
     * 1020. Partition Array Into Three Parts With Equal Sum
     * Given an array A of integers, return true if and only if we can partition the array into three non-empty parts with equal sums.
     *
     * Formally, we can partition the array if we can find indexes i+1 < j with (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
     *
     *
     *
     * Example 1:
     *
     * Input: [0,2,1,-6,6,-7,9,1,2,0,1]
     * Output: true
     * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
     * Example 2:
     *
     * Input: [0,2,1,-6,6,7,9,-1,2,0,1]
     * Output: false
     * Example 3:
     *
     * Input: [3,3,6,5,-2,2,5,1,-9,4]
     * Output: true
     * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
     *
     *
     * Note:
     *
     * 3 <= A.length <= 50000
     * -10000 <= A[i] <= 10000
     */

    /*
    Solution:
    make preSum array, find the sum, if sum % 3 != 0, it cannot separate to 3 parts, return false
    loop preSum array again, if find 1/3 2/3 3/3 of sum, increase parts, if parts could finally over 3, then return true

    Time: O(n)
    Space: O(1)
     */

    public boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 3) return false;
        for (int i = 1; i < A.length; i++) {
            A[i] += A[i - 1];
        }
        int total = A[A.length - 1], parts = 1;
        if (total % 3 != 0) return false;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == total / 3 * parts) parts++;
        }
        return parts == 4;
    }

}
