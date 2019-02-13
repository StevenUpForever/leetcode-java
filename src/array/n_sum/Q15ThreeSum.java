package array.n_sum;

import java.util.*;

public class Q15ThreeSum {

    //TAG: Facebook
    //TAG: Microsoft
    //TAG: Amazon
    //TAG: Apple
    //TAG: array
    //TAG: n_sum
    //Difficulty: Medium

    /**
     * 15. 3Sum
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

     Note: The solution set must not contain duplicate triplets.

     For example, given array S = [-1, 0, 1, 2, -1, -4],

     A solution set is:
     [
     [-1, 0, 1],
     [-1, -1, 2]
     ]
     */

    /*
     * Solution:
     * 1. Sort the array in ascending order, need to filter the duplicate number, easier to do this in sorted array
     * 2. set left index i, right index j and middle index m, outer loop of i start from 0 to last third number
     *      1. for each outer loop, start m = i + 1, j = len - 1, to find triplet number equal to 0, each step skip duplicated elements
     * 3. skip duplicated nums[i] and repeat step 2, when find triplet add to list
     *
     * Time: O(nlogn(sort) + n^2) = O(n^2) no matter how middle and right index move, they iterative all numbers right of left index once, it's O(n - 1) + O(n - 2) + ... + O(1) = O(n^2)
     * Space: O(n/3) = O(n) if all numbers could combine a triplet
     *
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) return res;
        Arrays.sort(nums);
        int i = 0;
        while (i < nums.length - 2) {
            int m = i + 1, j = nums.length - 1;
            //Define a local nums[i] to compare to skip all duplicated nums[i]
            int temp1 = nums[i];
            //If left index number is larger than 0, due to sorted array, no need to compare next triplets
            if (temp1 > 0) break;
            while (m < j) {
                int temp2 = nums[m], temp3 = nums[j];
                int curSum = temp1 + temp2 + temp3;
                if (curSum < 0) while (m < j && nums[m] == temp2) m++;
                else if (curSum > 0) while (j > m && nums[j] == temp3) j--;
                else {
                    res.add(Arrays.asList(nums[i], nums[m], nums[j]));
                    //If equal to 0, add current triplet and skip duplicated m and j to find next pair of m and j depends on the same i
                    //Be aware of if use nums[m] == nums[m + 1], if use this, at last the m is at the last duplicated m number, which need extra m++ and j-- to skip last duplicated number
                    while (m < j && nums[m] == temp2) m++;
                    while (j > m && nums[j] == temp3) j--;
                }
            }
            while (i < nums.length - 2 && nums[i] == temp1) i++;
        }
        return res;
    }

}
