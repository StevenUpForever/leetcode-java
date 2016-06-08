package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ChengzhiJia on 6/7/16.
 */
public class Problem71To80 {

    /*
    78. Subsets
    Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        int len = 1 << nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if (((1<<j) & i) != 0) list.add(nums[j]);
            }
            result.add(list);
        }
        return result;
    }

}
