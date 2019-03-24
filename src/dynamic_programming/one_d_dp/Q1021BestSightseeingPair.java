package dynamic_programming.one_d_dp;

public class Q1021BestSightseeingPair {

    //Difficulty: medium
    //TAG: dp

    /**
     * 1021. Best Sightseeing Pair
     * Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.
     *
     * The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.
     *
     * Return the maximum score of a pair of sightseeing spots.
     *
     *
     *
     * Example 1:
     *
     * Input: [8,1,5,2,6]
     * Output: 11
     * Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
     *
     *
     * Note:
     *
     * 2 <= A.length <= 50000
     * 1 <= A[i] <= 1000
     */

    /*
    Solution:
    https://leetcode.com/problems/best-sightseeing-pair/discuss/260850/JavaC%2B%2BPython-One-Pass

    DP problem, in each loop step, find:
    max with max cur number + num, then update the max cur number, but subtract by 1 due to index move forward

    Time: O(n)
    Space: O(1)
     */

    public int maxScoreSightseeingPair(int[] A) {
        int res = 0, cur = 0;
        for (int a: A) {
            res = Math.max(res, cur + a);
            //keep -1 equals to current index - that index
            cur = Math.max(cur, a) - 1;
        }
        return res;
    }

}
