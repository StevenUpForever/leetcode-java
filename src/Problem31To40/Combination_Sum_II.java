package Problem31To40;

import java.util.*;

public class Combination_Sum_II {

    /**
     * 40. Combination Sum II
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

     Each number in C may only be used once in the combination.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     A solution set is:
     [
     [1, 7],
     [1, 2, 5],
     [2, 6],
     [1, 1, 6]
     ]
     */

    /**
     * Approach:
     * As the result is sorted, so first better to sort the candidate array instead of sort the result arrays
     * this is like a similar all subsets problem, so one solution is to combine all subsets, and evaluate of any subsets is equal to target, if so, add this subset to the final list
     * Another solution is also a recursion one, but optimized, no need to generate all possible subsets, treat target as pivot (minus current number in cand array in each recursion step), if target == 0, is the right subset, even no need to recursion to next level
     */

    /**
     * Solution 1: all subsets similar (recursion)
     * base case: when the subsets sum is equal to target, add to final list
     * recursion rule: enum all subsets, which for the recursion tree, every level represent if current character will add to subset or not (every character do have two status in all subsets problem, one is add to subset, one is not add to)
     *      Skip the duplicated start number (while loop) of current recursion step before going through right subtree
     * recursion tree ([10, 1, 2, 7, 6, 1, 5]):
     *                                  []
     *                               /      \
     *  Add 10 or not              [10]     []
     *                            /    \    / \
     *  Add 1 or not           [10,1] [10][1] []
     *                              ......
     *
     * Time: O(2^n) n represent the length of candidates array
     * Space: O(logn) max height of recursion tree
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //Due to result need be sorted, so sort array at first
        Arrays.sort(candidates);
//        combinationRecursionS1(candidates, target, 0, new ArrayList<>(), res);
        combinationRecursionS2(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void combinationRecursionS1(int[] candidates, int target, int index, List<Integer> list, List<List<Integer>> res) {
        if (index == candidates.length) {
            int sum = 0;
            for (int i = 0; i < list.size(); i++) sum += list.get(i);
            if (sum == target) res.add(new ArrayList<>(list));
            return;
        }
        list.add(candidates[index]);
        combinationRecursionS1(candidates, target, index + 1, list, res);
        //Reset the status to previous node when going to right subtree
        list.remove(list.size() - 1);
        //Skip all start number same as current level of recursion tree
        while (index < candidates.length - 1 && candidates[index + 1] == candidates[index]) index++;
        combinationRecursionS1(candidates, target, index + 1, list, res);
    }

    /**
     * Solution 2: optimized recursion (Similar as Combination Sum, similar as all permutation but not combine all characters)
     * Due to all subsets will generate many sets until the end even this subset will over target, all subsets will generate all possible subsets no matter what is the current sum
     * Optimize: make target as the pivot (Similar as combination sum), every time decrease target to a value minus by current number in cand array
     *      Base case: until target == 0, no less, no more, it's the right subset, even no need to recursion to next number for current subset
     *      recursion rule: for loop all numbers from current recursion index to the end
     *          1. first skip all dup numbers to avoid dup subsets
     *          2. add current number, and recursion to next step by target - num
     *          3. reset to previous status and recursion the right subtree
     *
     *  Time: O(n!) every level will have at least one number skipped from array
     *  Space: o(n) if sum of all numbers equal to target number
     */

    private void combinationRecursionS2(int[] candidates, int target, int index, List<Integer> list, List<List<Integer>> res) {
        if (target <= 0) {
            if (target == 0) res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            /*
            recursion arguments:
                target became target = cand[i] represent the current rest value
                index became i + 1, go one direction of cand array, if became index + 1, will have all permutations, next recursion index will less than current level i
             */
            combinationRecursionS2(candidates, target - candidates[i], i + 1, list, res);
            //Reset the status to previous node when going to right subtree
            list.remove(list.size() - 1);
        }
    }

}
