package legacy_code.Problem51To60;

public class Jump_Game {

    /**
     * 55. Jump Game
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.

     Each element in the array represents your maximum jump length at that position.

     Determine if you are able to reach the last index.

     For example:
     A = [2,3,1,1,4], return true.

     A = [3,2,1,0,4], return false.
     */

    /**
     * Approach:
     * Simplify one of Jump_Game_II (legacy_code.Problem41To50)
     */

    /**
     * Solution 1:
     * DP problem, dp start from the end, because we already know the we at the last index, we need 0 step to end
     * Base case: M[len - 1] = true, dp direction is the same as nums's, and last index will have 0 steps to the end
     * Induction rule: M[i] represent the possibility to the end when start from current i index
     *      1. if nums[i] == 0, means not possible goes from here to the end, so M[i] = false
     *      2. if nums[i] + i >= len - 1, means could goto end immediately, so M[i] = true, step
     *      3. else for loop dp array from i to i ... nums[i] + i, find the min step in this scope
     *              1. if met no value equals to true, this is false
     *              2. otherwise, M[i] = true
     * At the end, check the M[0] value
     *
     * Time: O(1) + O(2) + O(n - 1) = O(n^2)
     * Space: O(n)
     */

    public boolean canJumpS1(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            //If nums[i] == 0, not possible to reach the end from i
            if (nums[i] == 0) dp[i] = false;
            else if (nums[i] + i >= nums.length - 1) dp[i] = true;
            else {
                //Find the min step in steps which start from i could reach
                for (int j = i + 1; j <= nums[i] + i; j++) {
                    if (dp[j]) dp[i] = true;
                }
            }
        }
        return dp[0];
    }

    /**
     * Solution 2:
     * for loop the nums once
     * keep track of the farthest distance could run nums[i] + 1 every time (curMostEnd), and make sure i smaller than nums.length and max to maxDistance
     * That's the max index index i could reach at
     * Finally check if i is over the last index, if so could jump from the start to the end
     *
     * Time: O(n)
     * Space: O(1)
     */

    public boolean canJumpS2(int[] nums) {
        int maxDistance = 0, i = 0;
        while (i < nums.length && i <= maxDistance) {
            maxDistance = Math.max(maxDistance, i + nums[i]);
            i++;
        }
        return i == nums.length;
    }

}
