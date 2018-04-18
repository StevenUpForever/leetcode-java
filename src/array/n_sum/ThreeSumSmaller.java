package array.n_sum;

import java.util.Arrays;

public class ThreeSumSmaller {

    //TAG: Google
    //TAG: array
    //Difficulty: medium

    /**
     * 259. 3Sum Smaller
     * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     *
     * For example, given nums = [-2, 0, 1, 3], and target = 2.
     *
     * Return 2. Because there are two triplets which sums are less than 2:
     *
     * [-2, 0, 1]
     * [-2, 0, 3]
     * Follow up:
     * Could you solve it in O(n2) runtime?
     */

    /**
     * Solution 1:
     * brute force
     * for for for loop
     * O(n^3)
     */

    /**
     * Solution 2:
     * 1. sort the array, sorted array contains more operations to do
     * 2. When i and j is fixed at current step, going to move k, just need to know the max number of k,
     * so that all number between j and max is valid tuple
     * So do binary search of k in [j + 1, end] find largest smaller than target - i - j
     *
     * Time: O(n^2logn)
     */

    /**
     * Solution 3:
     * for the max tuple that approach target, we can start k from end to start
     * for current i:
     *      loop j from i + 1 to k, k from len - 1 to j
     *      if sum < target, all combines after j is valid (because k is the largest and valid), count += k - j
     *      move j next
     *      if not, move k--
     *      any way, current loop, j and k will go over [i + 1, end]
     *
     * time: O(nlogn) + O(n^2) = O(n^2)
     * Space: O(1)
     */

    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    count += k - j;
                    j++;
                } else k--;
            }
        }
        return count;
    }

}
