package dynamic_programming.one_d_dp;

public class LongestTurbulentSubarray {

    //Difficulty: medium
    //TAG: array
    //TAG: DP

    /**
     * 978. Longest Turbulent Subarray
     * A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:
     *
     * For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
     * OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
     * That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
     *
     * Return the length of a maximum size turbulent subarray of A.
     *
     *
     *
     * Example 1:
     *
     * Input: [9,4,2,10,7,8,8,1,9]
     * Output: 5
     * Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
     * Example 2:
     *
     * Input: [4,8,12,16]
     * Output: 2
     * Example 3:
     *
     * Input: [100]
     * Output: 1
     *
     *
     * Note:
     *
     * 1 <= A.length <= 40000
     * 0 <= A[i] <= 10^9
     */

    /*
    Solution:

    similar to max ascending subarray, diff is the order need to be different between first two numbers and later two
    numbers

    when met same number, or wrong order e.g. A[i] == A[i + 1] or A[i - 1] < A[i] < A[i + 1], then reset length and update
    max
    otherwise increase current length

    Time: O(n)
    Space: O(1)
     */

    public int maxTurbulenceSize(int[] A) {
        if (A == null) return 0;
        int maxLen = 1, curLen = 1;
        /*
        flag staus:
        -1 descending order
        0 same number
        1 ascending order
         */
        int flag = 1;
        for (int i = 0; i < A.length - 1; i++) {
            //Current flag that will compare with global flag and update later on
            int tempFlag;
            if (A[i + 1] > A[i]) tempFlag = 1;
            else if (A[i + 1] < A[i]) tempFlag = -1;
            else tempFlag = 0;
            if (i == 0 || (tempFlag != 0 && tempFlag == 0 - flag)) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                /*
                When flag == 0 that means same number, A[i] == A[i + 1] reset start to A[i + 1]
                otherwise means order is same for A[i - 1] A[i] A[i + 1], need reset start to A[i]
                 */
                curLen = tempFlag == 0 ? 1 : 2;
            }
            flag = tempFlag;
        }
        return Math.max(curLen, maxLen);
    }

}
