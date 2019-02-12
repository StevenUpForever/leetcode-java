package greey;

public class Q55JumpGame {

    //Difficulty: medium
    //TAG: Apple
    //TAG: Greedy
    //TAG: DP

    /**
     * 55. Jump Game
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     *
     * Each element in the array represents your maximum jump length at that position.
     *
     * Determine if you are able to reach the last index.
     *
     * Example 1:
     *
     * Input: [2,3,1,1,4]
     * Output: true
     * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
     * Example 2:
     *
     * Input: [3,2,1,0,4]
     * Output: false
     * Explanation: You will always arrive at index 3 no matter what. Its maximum
     *              jump length is 0, which makes it impossible to reach the last index.
     */

    /*
    Solution 1:
    Greedy, try to get max distance at every step, if current step is not what max could reach return false
    otherwise update max, and if max can over nums.length return true immediately

    Time: O(n)
    Space: O(1)
     */

    public boolean canJump(int[] nums) {
        if (nums == null) return true;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) return false;
            max = Math.max(max, nums[i] + i);
            if (max >= nums.length - 1) return true;
        }
        return true;
    }

    /*
    Solution 2:
    DP, base case nums[len - 1] must be true
    so loop from len - 1 to 0, when nums[i] + i could reach len - 1 or after, dp[i] = true
    else if nums[i] != 0 means could walk at least one step, loop from [j + 1, nums[i] + 1], find first true
    means nums[i] could reach to that nums[j] and that nums[j] could reach to end
    update nums[i] = true
    finally dp[0] is the result

    Time: O(n^2)
    Space: O(n)
     */

    public boolean canJump2(int[] nums) {
        if (nums == null) return true;
        boolean[] dp = new boolean[nums.length];
        dp[dp.length - 1] = true;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] + i >= nums.length - 1) dp[i] = true;
            else if (nums[i] != 0) {
                for (int j = i + 1; j <= nums[i] + i; j++) {
                    if (dp[j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];
    }

}
