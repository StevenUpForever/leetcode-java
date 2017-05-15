package Problem41To50;

import java.util.*;

public class Permutations_II {

    /**
     * 47. Permutations II
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
     * Similar as Permutations, diff is in each loop, have a extra HashSet for each loop (represent the new HashSet on every recursion level, filter the dup number on current recursion level)
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

}
