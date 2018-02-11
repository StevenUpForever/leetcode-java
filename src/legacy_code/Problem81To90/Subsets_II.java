package legacy_code.Problem81To90;

import java.util.*;

public class Subsets_II {

    /**
     * 90. Subsets II
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.

     Note: The solution set must not contain duplicate subsets.

     For example,
     If nums = [1,2,2], a solution is:

     [
     [2],
     [1],
     [1,2,2],
     [2,2],
     [1,2],
     []
     ]
     */

    /**
     * Solution:
     * Similar as Subsets from legacy_code.Problem71To80, for the recursion tree, the left subtree (add current number) is the same,
     * difference is the right subtree, which the dup numbers already added to set from left subtree, so right subtree need to skip these numbers
     *
     * Time: O(2^n)
     * Space: O(2^n)
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subsetsHelper(res, new ArrayList<>(), 0, nums);
        return res;
    }

    private void subsetsHelper(List<List<Integer>> res, List<Integer> list, int index, int[] nums) {
        if (index == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        subsetsHelper(res, list, index + 1, nums);
        list.remove(list.size() - 1);
        //For current level, left subtree already add dup numbers, so for right subtree, need to skip these dup numbers
        while (index < nums.length - 1 && nums[index + 1] == nums[index]) index++;
        subsetsHelper(res, list, index + 1, nums);
    }

}
