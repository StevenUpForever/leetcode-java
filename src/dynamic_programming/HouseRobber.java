package dynamic_programming;

public class HouseRobber {

    //TAG: LinkedIn
    //TAG: DP
    //Difficulty: Easy

    /**
     * 198. House Robber
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

     Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
     */

    /**
     * Solution: DP
     * Basically just need to know two status, whether the previous one is done, if not, we can do this one, and if yes, we need to keep track of the best value and skip this one
     * So need 2 values, preDone, preNot, which whether pre done or not, use preNot to record the most updated max value, and preDone = preNot + nums[i] which means only if pre is not done, we could do this one by add nums[i]
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int rob(int[] nums) {
        int preDone = 0, preNot = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = preNot;
            preNot = Math.max(preDone, preNot);
            preDone = temp + nums[i];
        }
        //At last we lack of one step to track updated value to preNot, need compare one more time
        return Math.max(preDone, preNot);
    }

}
