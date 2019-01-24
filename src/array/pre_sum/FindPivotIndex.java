package array.pre_sum;

public class FindPivotIndex {

    //Difficulty: easy
    //TAG: Apple
    //TAG: array
    //TAG: presum

    /**
     * 724. Find Pivot Index
     * Given an array of integers nums, write a method that returns the "pivot" index of this array.
     *
     * We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.
     *
     * If no such index exists, we should return -1. If there are multiple pivot indexes, you should return the left-most pivot index.
     *
     * Example 1:
     * Input:
     * nums = [1, 7, 3, 6, 5, 6]
     * Output: 3
     * Explanation:
     * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
     * Also, 3 is the first index where this occurs.
     * Example 2:
     * Input:
     * nums = [1, 2, 3]
     * Output: -1
     * Explanation:
     * There is no index that satisfies the conditions in the problem statement.
     * Note:
     *
     * The length of nums will be in the range [0, 10000].
     * Each element nums[i] will be an integer in the range [-1000, 1000].
     */

    /*
    Solution:
    Key is the corner case:
    1. return of null, []
    2. return of [1]
    3. return of [1, 2]

    The question means sum of no boundary is 0, e.g. [1, 0], should return index 0

    then use presum array filter subarray quickly

    Time: O(n)
    Space: O(1)
     */

    public int pivotIndex(int[] nums) {
        if (nums == null) return -1;
        if (nums.length  == 1) return 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            int leftSum = i == 0 ? 0 : nums[i - 1];
            int rightSum = i == nums.length - 1 ? 0 : nums[nums.length - 1] - nums[i];
            if (leftSum == rightSum) return i;
        }
        return -1;
    }

}
