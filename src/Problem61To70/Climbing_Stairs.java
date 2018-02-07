package Problem61To70;

public class Climbing_Stairs {

    /**
     * 70. Climbing Stairs
     * You are climbing a stair case. It takes n steps to reach to the top.

     Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

     Note: Given n will be a positive integer.
     */

    /**
     * Solution: 1D DP (Code as fibonacci)
     * have a dp array, which dp[i] represent the max ways to reach i
     * think about current index i, which could be reached from i - 2, or i - 1, (after base case), so the max ways to reach i is the sum paths from i - 2 and i - 1
     * Base case: M[0] = 1, M[1] = 2 (two ways 1->1, or 2 to reach index 1)
     * Induction rule: M[i] = M[i - 2] + M[i  -1]
     *
     * Time: O(n)
     * Space: O(1) use two variables instead of dp array
     */

    public int climbStairsS1(int n) {
        if (n <= 2) return n;
        //pre represent the dp[i - 2], cur represent dp[i - 1] and in loop is dp[i]
        int pre = 1, cur = 2;
        for (int i = 2; i < n; i++) {
            //Set a temp sum and move pre and cur to next 1 index
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return cur;
    }

    /**
     * Solution 2: dfs (not good but a solution)
     * From every index, try to jump 1 or 2 steps to n
     * Base case: if index == n, reach the n, increase the count
     * recursion rule:
     *      if index < n, could finally go 1 step recursion(index + 1)
     *      if index < n - 1 could go 1 or 2 steps recursion(index + 2)
     *
     * Time: O(2^n)
     *      as for the recursion tree
     *                    0
     *                 /      \
     *                1       2
     *               / \     / \
     *              2  3    3  4
     *          (1+1)(1+2)(1+2)(2+2)
     *                  ......
     *  each level represent how many steps could go
     *  totally n levels (when all goes 1 step)
     *
     *  Space: O(n) levels of recursion tree
     */

    private int count = 0;
    public int climbStairsS2(int n) {
        climbStairsHelper(n, 0);
        return count;
    }

    private void climbStairsHelper(int n, int index) {
        if (index == n) {
            count++;
            return;
        }
        if (index < n) climbStairsHelper(n, index + 1);
        if (index < n - 1) climbStairsHelper(n, index + 2);
    }

}
