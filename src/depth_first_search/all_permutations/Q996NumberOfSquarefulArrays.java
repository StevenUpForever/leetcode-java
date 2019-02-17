package depth_first_search.all_permutations;

import java.util.Arrays;

public class Q996NumberOfSquarefulArrays {

    //Difficulty: Hard
    //TAG: DFS

    /**
     * 996. Number of Squareful Arrays
     * Given an array A of non-negative integers, the array is squareful if for every pair of adjacent elements, their sum is a perfect square.
     *
     * Return the number of permutations of A that are squareful.  Two permutations A1 and A2 differ if and only if there is some index i such that A1[i] != A2[i].
     *
     *
     *
     * Example 1:
     *
     * Input: [1,17,8]
     * Output: 2
     * Explanation:
     * [1,8,17] and [17,8,1] are the valid permutations.
     * Example 2:
     *
     * Input: [2,2,2]
     * Output: 1
     *
     *
     * Note:
     *
     * 1 <= A.length <= 12
     * 0 <= A[i] <= 1e9
     */

    /*
    Solution:
    dfs, apply all permutation (with duplicates) solution, modify only when target could combine a perfect square
    with previous number, and then dfs to next step

    Time: O(n!)
    Space: O(n)
     */

    private int count = 0;
    public int numSquarefulPerms(int[] A) {
        Arrays.sort(A);
        boolean[] visited = new boolean[A.length];
        permute(A, visited, 0, 0);
        return count;
    }

    //Only need current length and the pre number, no need append to an array
    private void permute(int[] nums, boolean[] visited, int index, int pre) {
        if (index == nums.length) {
            count++;
            return;
        }
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                    return;
                }
                if (index == 0 || isPerfectSqaure(pre + nums[i])) {
                    visited[i] = true;
                    permute(nums, visited, index + 1, nums[i]);
                    visited[i] = false;
                }
            }
        }
    }

    private boolean isPerfectSqaure(int num) {
        double sqrt = Math.sqrt(num);
        return sqrt - Math.floor(sqrt) == 0;
    }

}
