package legacy_code.Problem231To240;

public class Product_of_Array_Except_Self {

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
     * Multiple previous numbers add set to current index at first loop
     * Then second loop, from end to start also multiple previous numbers add set to current index at first loop
     * at this time, we already multiple all other numbers in this index
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
