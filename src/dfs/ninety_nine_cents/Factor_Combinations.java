package dfs.ninety_nine_cents;

import java.util.*;

public class Factor_Combinations {

    //TAG: Uber
    //TAG: DFS
    //TAG: 99 cents

    /**
     * 254. Factor Combinations
     * Numbers can be regarded as product of its factors. For example,

     8 = 2 x 2 x 2;
     = 2 x 4.
     Write a function that takes an integer n and return all possible combinations of its factors.

     Note:
     You may assume that n is always positive.
     Factors should be greater than 1 and less than n.
     Examples:
     input: 1
     output:
     []
     input: 37
     output:
     []
     input: 12
     output:
     [
     [2, 6],
     [2, 2, 3],
     [3, 4]
     ]
     input: 32
     output:
     [
     [2, 16],
     [2, 2, 8],
     [2, 2, 2, 4],
     [2, 2, 2, 2, 2],
     [2, 4, 4],
     [4, 8]
     ]
     */

    /**
     * Solution: dfs
     * due to the solution array is sorted list, so every time, when we found a number i which n % i == 0, we could add this i to current list, then recursion the n = n/i, start = i, to make sure next number is larger than i
     *
     */

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        getFactorsHelper(res, new ArrayList<>(), n, 2);
        return res;
    }

    private void getFactorsHelper(List<List<Integer>> res, List<Integer> list, int n, int start) {
        //Need to remove 1 and n itself as factors
        if (n <= 1) {
            //if n == 2, the list will only have [2], but 2 is n itself, factors should at least be two, so skip the list
            if (list.size() > 1) res.add(new ArrayList<>(list));
            return;
        }
        //i could be equal to n, due to n will reduced to n/i until 1, so i must have one time equals to n so n/i == 0 to combine a valid list
        for (int i = start; i <= n; i++) {
            if (n % i == 0) {
                list.add(i);
                getFactorsHelper(res, list, n/i, i);
                list.remove(list.size() - 1);
            }
        }
    }

}
