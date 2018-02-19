package depth_first_search.all_subsets;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {

    //TAG: LinkedIn
    //TAG: DFS
    //TAG: all subsets
    //Difficulty: Medium

    /**
     * 698. Partition to K Equal Sum Subsets
     * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

     Example 1:
     Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     Output: True
     Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
     Note:

     1 <= k <= len(nums) <= 16.
     0 < nums[i] < 10000.
     */

    /**
     * Solution:
     * One solution may sort the array, and loop from left and right to middle, but possible solution
     * may not be consequent numbers
     *
     * Use DFS, use visit array to filter numbers already added, recursively add new number in nums,
     * when curSum == totalSum/k, recursion to next part which k--
     *
     * Time: O(n!)
     * Space: O(n)
     */

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int n: nums)sum += n;
        //If nums cannot partition to k parts
        if(k <= 0 || sum % k != 0) return false;
        boolean[] visited = new boolean[nums.length];
        return canPartitionHelper(nums, visited, 0, k, 0, 0, sum/k);
    }

    private boolean canPartitionHelper(int[] nums, boolean[] visited, int start, int k, int curSum, int index, int target){
        if(k == 1)return true;
        /*
        Index is using to e.g. target == 0, there's -1, 1 in nums, which could added to 0, but curSum is originally 0,
        need to make sure number of numbers in one partition larger than 0
         */
        if(curSum == target && index > 0)
            return canPartitionHelper(nums, visited, 0, k - 1, 0, 0, target);
        for(int i = start; i < nums.length; i++){
            if(!visited[i]){
                visited[i] = true;
                if(canPartitionHelper(nums, visited, i + 1, k, curSum + nums[i], index++, target))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }

}
