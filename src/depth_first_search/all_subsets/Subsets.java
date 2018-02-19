package depth_first_search.all_subsets;

import java.util.*;

public class Subsets {

    //TAG: Uber
    //TAG: DFS
    //Difficulty: Medium

    /**
     * 78. Subsets
     * Given a set of distinct integers, nums, return all possible subsets.

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

    /**
     * Solution: depth_first_search
     * Base case: if recursion index == nums.length, add copy of list to result list
     * recursion rule: for a recursion tree, each level has two status, add the number or not, so we have the recursion tree as below:
     *              ""
     *             /  \
     *            1   ""
     *           / \ / \
     *         12  1 2  ""
     *         ......
     *
     * Time: O(2^n) n is length of nums
     * Space: O(2^n) numbers of possible subsets
     */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
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
        subsetsHelper(res, list, index + 1, nums);
    }

}
