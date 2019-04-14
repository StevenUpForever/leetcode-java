package dynamic_programming.one_d_dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q1027LongestArithmeticSequence {

    //Difficulty: medium
    //TAG: dp

    /**
     * 1027. Longest Arithmetic Sequence
     * Given an array A of integers, return the length of the longest arithmetic subsequence in A.
     *
     * Recall that a subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
     *
     *
     *
     * Example 1:
     *
     * Input: [3,6,9,12]
     * Output: 4
     * Explanation:
     * The whole array is an arithmetic sequence with steps of length = 3.
     * Example 2:
     *
     * Input: [9,4,7,2,10]
     * Output: 3
     * Explanation:
     * The longest arithmetic subsequence is [4,7,10].
     * Example 3:
     *
     * Input: [20,1,15,3,10,5,8]
     * Output: 4
     * Explanation:
     * The longest arithmetic subsequence is [20,15,10,5].
     *
     *
     * Note:
     *
     * 2 <= A.length <= 2000
     * 0 <= A[i] <= 10000
     */

    /*
    Solution:

    DP problem, dp[i] is a Map<Integer, Integer>, which key represent all possible diff between cur - A[pre]
    value is the max length

    every loop keep update global max

    Time: O(n^2)
    Space: O(n)
     */

    public int longestArithSeqLength(int[] A) {
        List<Map<Integer, Integer>> list = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            Map<Integer, Integer> newMap = new HashMap<>();
            list.add(newMap);
            for (int j = i - 1; j >= 0; j--) {
                Map<Integer, Integer> curMap = list.get(j);
                int diff = A[i] - A[j];
                newMap.put(diff,
                        Math.max(newMap.getOrDefault(diff, 0),
                                curMap.getOrDefault(diff, 1) + 1));
                max = Math.max(max, newMap.get(diff));
            }
        }
        return max;
    }

}
