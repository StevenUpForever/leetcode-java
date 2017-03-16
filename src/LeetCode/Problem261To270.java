package LeetCode;

/**
 * Created by c0j00cs on 3/15/17.
 */
public class Problem261To270 {
    /*
    268. Missing Number
    Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
     */
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int num: nums) {
            res += num;
        }
        int len = nums.length;
        return len*(len + 1)/2 - res;
    }
}
