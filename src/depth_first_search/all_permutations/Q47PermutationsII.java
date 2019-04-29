package depth_first_search.all_permutations;

import java.util.*;

public class Q47PermutationsII {

    //TAG: LinkedIn
    //TAG: DFS
    //Difficulty: Medium

    /**
     * 47. Q46Permutations II
     * Given a collection of numbers that might contain duplicates, return all possible unique permutations.

     For example,
     [1,1,2] have the following unique permutations:
     [
     [1,1,2],
     [1,2,1],
     [2,1,1]
     ]
     */

    /**
     * Solution:
     * Similar as Q46Permutations, diff is in each loop, have a extra HashSet for each loop (represent the new HashSet on every recursion level, filter the dup number on current recursion level)
     *
     * The reason is because e.g. aba when 1st level swap a1 with following, at 3rd level swap a3 with following, which is same as
     * loop to a3 on 1st level
     *
     * Time: o(n!) every recursion level will skip one char in loop so O(n*(n - 1)*(n - 2)) = O(n!)
     * Space: O(n) max recursion levels + (max number of n hashSets) or (1 hashSet with max n numbers) clear on recursion tree,
     * so O(2n) = O(n)
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num: nums) list.add(num);
        permute(res, list, 0);
        return res;
    }

    private void permute(List<List<Integer>> res, List<Integer> list, int index) {
        if (index == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }
        //Define the hashSet before each recursion level start
        HashSet<Integer> set = new HashSet<>();
        for (int i = index; i < list.size(); i++) {
            if (!set.contains(list.get(i))) {
                set.add(list.get(i));
                swap(list, index, i);
                permute(res, list, index + 1);
                //Reset to previous status
                swap(list, index, i);
            }
        }
    }

    private void swap(List<Integer> list, int a, int b) {
        Integer temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    /*
    Solution 2:
    use visited[] filter dfs
    sort the array, use visited[] filter dups, due to if use set, will not try other dup even once, will add nothing in
    the final array
     */

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(res, new boolean[nums.length], new ArrayList<>(), nums);
        return res;
    }

    private void dfs(List<List<Integer>> res, boolean[] visited, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /*
            1. skip if visited[i]
            2. if current is not visited, duplicated with before and before not visited, skip, reason: if cur is
            dup with pre, but pre is visited, that's the first time add all these dups, cannot skip otherwise will add
            nothing in the list even once
             */
            if (visited[i] || (i > 0 && nums[i - 1] == nums[i] && !visited[i - 1])) continue;
            visited[i] = true;
            list.add(nums[i]);
            dfs(res, visited, list, nums);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
