package depth_first_search.all_subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q90SubsetsII {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: dfs

    /**
     * 90. Subsets II
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
     *
     * Note: The solution set must not contain duplicate subsets.
     *
     * Example:
     *
     * Input: [1,2,2]
     * Output:
     * [
     *   [2],
     *   [1],
     *   [1,2,2],
     *   [2,2],
     *   [1,2],
     *   []
     * ]
     */

    /*
    Solution:

    sort the array,
    Similar to subsets I, after add char, do dfs, skip all dup chars, then do another dfs

    Time: O(2^n)
    Space: O(n)
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        dfs(lists, new ArrayList<>(), 0, nums);
        return lists;
    }

    private void dfs(List<List<Integer>> lists, List<Integer> list, int index, int[] nums) {
        if (index >= nums.length) {
            lists.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[index]);
        dfs(lists, list, index + 1, nums);
        while (index < nums.length - 1 && nums[index] == nums[index + 1]) index++;
        list.remove(list.size() - 1);
        dfs(lists, list, index + 1, nums);
    }

}
