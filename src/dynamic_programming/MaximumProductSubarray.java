package dynamic_programming;

public class  MaximumProductSubarray {

    //TAG: LinkedIn
    //TAG: DP
    //Difficulty: Medium

    /**
     * 152. Maximum Product Subarray
     * Find the contiguous subarray within an array (containing at least one number) which has the largest product.

     For example, given the array [2,3,-2,4],
     the contiguous subarray [2,3] has the largest product = 6.
     */

    /**
     * Solution:
     * Similar as MaximumSubarray(legacy_code.Problem51To60), diff is:
     *      1. the skip point is 0, when met 0, need to restart current product
     *      2. when met num < 0, do not skip, due to next num < 0 will make total product larger, so need another helper number to record the negative product temporarily
     *      3. cannot sync product with helper product all the time, due to result may start from some negative number like [3, -2, -3, -6, 9]
     * So we'd always keep a max product and min product (negative temporarily) separately, and product = max(max, min)
     * Finally, res = max(res, product)
     *
     * Time: O(n)
     * Space: O(1)
     */

    public int maxProduct(int[] nums) {
        //product is the result, max is update the max product currently as usual, min is due to rule 3 explained above, every negative number may be the start number of final result, so keep record a min number
        int product = nums[0], max = product, min = product;
        for (int i = 1; i < nums.length; i++) {
            /*
            Inner Math.max compared whether current nums[i] is <0 or >0, one will be the right larger number (max * nums[i], min * nums[i])
            outer Math.max is when max = 0, min = 0, product is 0, but nums[i] currently is the right max/min number to use for next time
             */
            int localMax = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            //Use old max number to calculate min, and after update the product, update the max
            min = Math.min(Math.min(max * nums[i], min * nums[i]), nums[i]);
            product = Math.max(product, localMax);
            max = localMax;
        }
        return product;
    }

}
