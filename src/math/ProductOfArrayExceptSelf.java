package math;

public class ProductOfArrayExceptSelf {

    //TAG: LinkedIn
    //TAG: Math
    //TAG: array
    //Difficulty: Medium

    /**
     * 238. Product of Array Except Self
     * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

     Solve it without division and in O(n).

     For example, given [1,2,3,4], return [24,12,8,6].

     Follow up:
     Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
     */

    /**
     * Solution:
     * Think about the problem, cannot use divide, so we cannot multiple all numbers together and divide nums[i]
     * Solve in O(n), so cannot for for loop multiple all numbers but nums[i] together, need to record previous somewhere
     * solve with constant space, so cannot use HashMap which key is num[i] value is multiple res
     *
     * All above are valid solutions if no restriction
     *
     * So in another way, for a number num[i] we could multiple num[0...i-1] and num[i+1...] together and put in current
     * index i, to do this, we can multiple previous res (dp[i - 1]) * num[i - 1] into dp[i] so dp[i] will be all multiple
     * results of num[0..i-1]
     * then loop from end to start again do the same thing, dp[i] will contains multiple num[i + 1...n]
     * so finally, we got result dp[]
     *
     * Time: O(2n) = O(n)
     * Space: O(1) as (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
     */

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            res[i + 1] = res[i] * nums[i];
        }
        //Need to calculate product from small to large from end to start
        int product = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            product *= nums[i + 1];
            res[i] *= product;
        }
        return res;
    }

}
