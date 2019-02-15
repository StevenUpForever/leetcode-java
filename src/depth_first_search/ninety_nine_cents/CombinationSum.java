package depth_first_search.ninety_nine_cents;

import java.util.*;

public class CombinationSum {

    //TAG: Uber
    //TAG: Airbnb
    //TAG: Apple
    //TAG: DFS
    //TAG: 99 cents
    //Difficulty: Medium

    /**
     * 39. Combination Sum
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

     The same repeated number may be chosen from C unlimited number of times.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:
     [
     [7],
     [2, 2, 3]
     ]
     */

    /*
     * Solution: recursion
     * recursion step:
     *      Base case: when the value is equal to target or smaller but add one more current value will over target, then return
     *      recursion rule:
     *          keep track of current rest of value (target - current added)
     *          try to add different numbers of current value into current value, better from large to small values
     * recursion tree:
     *                               ""
     *                  / / / / /       \ \ \ \
     * candidates[0] * 1 2 3  4 5  ......        <= target
     *                   / / / / /       \ \ \ \
     * candidates[1] * 1 2 3  4 5  ......        <= target
     *              ......
     * candidates[len - 1] * 1 2 3  4 5  ......  <= target
     *
     * Time: O(m ^ n)
     *      m represent the max count needed which is target/candidates[0] (smallest value)
     *      n represent the length of candidates array
     * Space: O(m)
     *      m represent the max count needed which is target/candidates[0] (smallest value), also as the max levels of recursion tree
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationRecursion(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void combinationRecursion(int[] candidates, int index, int valueLeft, List<Integer> list, List<List<Integer>> res) {
        //If have last largest value not filled, it may over target limit, need manually check the validation
        if (index == candidates.length - 1) {
            //If could fit with numbers of largest value, this recursion result is a correct one
            if (valueLeft % candidates[candidates.length - 1] == 0) {
                list.add(valueLeft / candidates[candidates.length - 1]);
                List<Integer> curRes = new ArrayList<>();
                //Add actual values to a new array and add to result
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i); j++) curRes.add(candidates[i]);
                }
                res.add(curRes);
                //Reset to previous step, because all recursion substrees use the same list, need to prepare for next recursion
                list.remove(list.size() - 1);
            }
            return;
        }
        //loop all possible numbers of current values
        for (int i = 0; i <= valueLeft/candidates[index]; i++) {
            list.add(i);
            combinationRecursion(candidates, index + 1, valueLeft - candidates[index] * i, list, res);
            //reset to previous status
            list.remove(list.size() - 1);
        }
    }

}
