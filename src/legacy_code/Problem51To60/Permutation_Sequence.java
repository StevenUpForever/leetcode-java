package legacy_code.Problem51To60;

import java.util.*;

public class Permutation_Sequence {

    /**
     * 60. Permutation Sequence
     * The set [1,2,3,…,n] contains a total of n! unique permutations.

     By listing and labeling all of the permutations in order,
     We get the following sequence (ie, for n = 3):

     "123"
     "132"
     "213"
     "231"
     "312"
     "321"
     Given n and k, return the kth permutation sequence.

     Note: Given n will be between 1 and 9 inclusive.
     */

    /**
     * Soltion 1: depth_first_search
     * generate all permutations of array [1...n], and return the array[k]
     *
     * Time: O(n!) for all permutations
     * Space: O(n!) (permutations array) + O(n) (recursion stack) = O(n!)
     */

    /**
     * Solution 2:
     * For a number n, could divid into n groups of (n - 1) permutations, like 3 could group by 1permutation(2,3) + 2permutation(1,3) + 3permutation(1,2)
     * The idea is after divide by the number of rest group permutations, say (n - 1)!, we left 1, 2, 3 array, and the k / (n-1)! result is the index of 1,2,3, then add to string (as need to smallest order)
     * Go the divide and conquer step, the second position is after divide (n - 2)!, search the right index in n - 1 array
     * k goes to the mold value after divide the factors[i]
     * So first we need one number list to store all numbers from 1 to n, for search the number to append to string
     * Another factors array to store all factor result from 1 to n - 1 (first step is divide (n-1)! to have a 1,2,3 array)
     *
     * Time: O(n) for loop * O(n) list delete & search = O(n^2)
     * Space: O(n) nums array + O(n) factors array  = O(n)
     */

    public String getPermutation(int n, int k) {
        //If linkedList, proficient in deleting nums.remove(index); but more time on searching nums.get(index)
        //If arrayList, proficient in searching nums.get(index), but more time on deleteing nums.remove(index)
        //So whichever list is chosen, extra O(n) time will have
        List<Integer> nums = new LinkedList<>();
        for (int i = 1; i <= n; i++) nums.add(i);
        int[] facts = new int[n];
        facts[0] = 1;
        //Add factor from facts[2], due to when begin loop at n, need to divide (n-1)! to have (1, 2, 3) array, so, facts[len - 1] need to be (n-1)!
        for (int i = 1; i < n; i++) facts[i] = i * facts[i - 1];
        //As the k / facts[i - 1] value is index, need be start from 0
        k--;
        StringBuilder res = new StringBuilder();
        for (int i = n; i >= 1; i--) {
            //Divide value k / facts[i - 1] is the index in (1, 2, 3) array
            int index = k / facts[i - 1];
            res.append(nums.get(index));
            //Divide and conquer step, need n became n - 1 (same as i goes by 1), array always start from 0
            nums.remove(index);
            //k set to remaining value after divide (current n - 1)!
            k %= facts[i - 1];
        }
        return res.toString();
    }

}
