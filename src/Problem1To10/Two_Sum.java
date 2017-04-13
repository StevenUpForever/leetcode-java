package Two_Sum;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by c0j00cs on 4/13/17.
 */
public class Two_Sum {
    /**
    1. Two Sum
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
     */

    //Solution 1: Brute force high time, low space

    public int[] twoSumS1(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) return res;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return res;
    }

    //Solution 2: HashMap high space, low time

    public int[] twoSumS2(int[] nums, int target) {
        int[] res = new int[2];
        if (nums.length == 0) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int resNum = target - nums[i];
            if (map.containsKey(resNum)) return new int[]{i, map.get(resNum)};
            else map.put(nums[i], i);
        }
        return res;
    }

}
