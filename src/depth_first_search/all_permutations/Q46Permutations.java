package depth_first_search.all_permutations;

import java.util.*;

public class Q46Permutations {

    //TAG: LinkedIn
    //TAG: Apple
    //TAG: DFS
    //Difficulty: Medium

    /**
     * 46. Q46Permutations
     * Given a collection of distinct numbers, return all possible permutations.

     For example,
     [1,2,3] have the following permutations:
     [
     [1,2,3],
     [1,3,2],
     [2,1,3],
     [2,3,1],
     [3,1,2],
     [3,2,1]
     ]
     */

    /*
     * Approach:
     * recursion problem, swap or add diff char into new list every time, recursion tree as below
     *                         abc
     *                      /   \     \
     *                   a(bc) b(ac) c(ab)
     *               /    |      /  \
     *              ab(c) ac(b)ba(c) bc(a)
     *              /
     *             abc      ......
     */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num: nums) list.add(num);
        permuteS1(res, list, 0);
//        permuteS2(res, new ArrayList<>(), nums);
        return res;
    }

    /*
     * Solution 1:
     * Have a list which contains all numbers in nums, swap diff pair of chars every time to make diff order of list
     * *** make sure every diff pair chars exchange once and only once ***
     * Base case: when index == nums.len, means swap all pairs for current for loop, add to res
     * recursion rule:
     *      1. for i = current index to the end, swap index and i
     *      2. recursive to index + 1 level
     *      3. reset to previous status
     *
     * Time: O(n!) every recursion level will skip one char in loop so O(n*(n - 1)*(n - 2)) = O(n!)
     * Space: O(n)
     */

    private void permuteS1(List<List<Integer>> res, List<Integer> list, int index) {
        if (index == list.size()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < list.size(); i++) {
            swap(list, index, i);
            permuteS1(res, list, index + 1);
            //Reset to previous status
            swap(list, index, i);
        }
    }

    private void swap(List<Integer> list, int a, int b) {
        Integer temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }

    /*
     * Solution 2:
     * Have a empty list, and nums in recursion, every time add a new char (not been appear in list), and recursive next level
     * every loop will loop all numbers in nums to current recursion index in list (by list.add() will make index + 1)
     *
     * Time: O(n!^2) by have list.contains()in every level
     * Space: O(n)
     */

    private void permuteS2(List<List<Integer>> res, List<Integer> list, int[] nums) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                permuteS2(res, list, nums);
                list.remove(list.size() - 1);
            }
        }
    }

}
