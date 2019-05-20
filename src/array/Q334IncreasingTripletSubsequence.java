package array;

public class Q334IncreasingTripletSubsequence {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: array

    /**
     * 334. Increasing Triplet Subsequence
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
     *
     * Formally the function should:
     *
     * Return true if there exists i, j, k
     * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
     * Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.
     *
     * Example 1:
     *
     * Input: [1,2,3,4,5]
     * Output: true
     * Example 2:
     *
     * Input: [5,4,3,2,1]
     * Output: false
     */

    /*
    Solution:

    assign two numbers, and update num1 or num2 when find smaller, otherwise return true
    actually we just need to know there're two numbers at num1 < num2 at sometime before and when find num3,
    we can return true, we don't need to keep num1 at left of num2 at anytime

    Time: O(n)
    Space: (1)
     */

    public boolean increasingTriplet(int[] nums) {
        int num1 = Integer.MAX_VALUE, num2 = Integer.MAX_VALUE;
        for (int num: nums) {
            if (num <= num1) num1 = num;
            else if (num <= num2) num2 = num;
            else return true;
        }
        return false;
    }

}
