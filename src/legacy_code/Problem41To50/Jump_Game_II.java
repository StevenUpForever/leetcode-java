package legacy_code.Problem41To50;

public class Jump_Game_II {

    /**
     * 45. Jump Game II
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.

     Each element in the array represents your maximum jump length at that position.

     Your goal is to reach the last index in the minimum number of jumps.

     For example:
     Given array A = [2,3,1,1,4]

     The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

     Note:
     You can assume that you can always reach the last index.
     */

    /**
     * Solution 1:
     * DP problem, dp start from the end, because we already know the we at the last index, we need 0 step to end
     * Base case: M[len - 1] = 0, dp direction is the same as nums's, and last index will have 0 steps to the end
     * Induction rule: M[i] represent the min steps to the end when start from current i index
     *      1. if nums[i] == 0, means not possible goes from here to the end, so M[i] = Int.MAX
     *      2. if nums[i] + i >= len - 1, means could goto end immediately, so M[i] = 1, step
     *      3. else for loop dp array from i to i ... nums[i] + i, find the min step in this scope
     *              1. if the min value is Int.MAX, M[i] = Int.MAX
     *              2. otherwise, M[i] = minValue + 1
     * At the end, check the M[0] value
     *      1. if Int.MAX, return -1 as error value
     *      2. otherwise return M[0]
     *
     * Time: O(1) + O(2) + O(n - 1) = O(n^2)
     * Space: O(n)
     */

    public int jumpS1(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            //If nums[i] == 0, not possible to reach the end from i
            if (nums[i] == 0) dp[i] = Integer.MAX_VALUE;
            else if (nums[i] + i >= nums.length - 1) dp[i] = 1;
            else {
                int minValue = Integer.MAX_VALUE;
                //Find the min step in steps which start from i could reach
                for (int j = i + 1; j <= nums[i] + i; j++) {
                    minValue = Math.min(minValue, dp[j]);
                    //If find the smallest possible value 1, break
                    if (minValue == 1) break;
                }
                dp[i] = minValue == Integer.MAX_VALUE ? minValue : minValue + 1;
            }
        }
        return dp[0] == Integer.MAX_VALUE ? -1 : dp[0];
    }

    /**
     * Solution 2:
     * Get idea from legacy_code, try to explain
     * keep track of the farthest distance could run nums[i] + 1 every time (curMostEnd), the farthest distance at this jump (curEnd), the jumps result
     * except the last number in nums (the last index step is 0), for loop the nums
     *      1. keep updating the curMostEnd every time
     *      2. record the curEnd when i met this, may not the best reach, but must have record one jump within this curEnd
     *              1. update jumps
     *              2. update curEnd to new most recent curMostEnd, which already updated before curEnd
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int jumpS2(int[] nums) {
        int jumps = 0, curEnd = 0, curMostEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curMostEnd = Math.max(curMostEnd, nums[i] + i);
            if (i == curEnd) {
                jumps++;
                curEnd = curMostEnd;
            }
        }
        return jumps;
    }

}
