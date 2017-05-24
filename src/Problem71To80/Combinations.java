package Problem71To80;

import java.util.*;

public class Combinations {

    /**
     * 77. Combinations
     * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

     For example,
     If n = 4 and k = 2, a solution is:

     [
     [2,4],
     [3,4],
     [2,3],
     [1,2],
     [1,3],
     [1,4],
     ]
     */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
//        combineS1Helper(res, new ArrayList<>(), 1, k, n);
        combineS2Helper(res, new ArrayList<>(), 0, 0, k, n);
        return res;
    }

    /**
     * Solution 1: DFS
     * Generate all subsets, and if final subset's length is k, add to final list
     *
     * Time: O(2^n)
     * Space: O(C(k, n))
     */

    private void combineS1Helper(List<List<Integer>> res, List<Integer> list, int index, int k, int n) {
        if (index == n + 1) {
            if (list.size() == k) res.add(new ArrayList<>(list));
            return;
        }
        list.add(index);
        combineS1Helper(res, list, index + 1, k, n);
        //Reset list to previous status
        list.remove(list.size() - 1);
        combineS1Helper(res, list, index + 1, k, n);
    }

    /**
     * Solution 1: Optimized DFS
     * All subsets will generate subset not only k length, so waste some calculate time
     * We could have another recursion way, from different start, add all rest values until add k values
     *
     * Time: O(n! (k height))
     * Space: O(C(k, n))
     */

    private void combineS2Helper(List<List<Integer>> res, List<Integer> list, int start, int index, int k, int n) {
        if (index == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < n; i++) {
            list.add(i + 1);
            combineS2Helper(res, list, i + 1, index + 1, k, n);
            list.remove(list.size() - 1);
        }
    }

}
