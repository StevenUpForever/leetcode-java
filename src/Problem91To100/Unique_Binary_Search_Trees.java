package Problem91To100;

public class Unique_Binary_Search_Trees {

    /**
     * 96. Unique Binary Search Trees
     * Given n, how many structurally unique bst's (binary search trees) that store values 1...n?

     For example,
     Given n = 3, there are a total of 5 unique bst's.

     1         3     3      2      1
     \       /     /      / \      \
     3     2     1      1   3      2
     /     /       \                 \
     2     1         2                 3
     */

    /**
     * Solution: DP
     * for loop the i in 1...n, i as the root value, all possible trees are leftTree * rightTree, the left tree root value is 1...i - 1, right tree root is i + 1 ... n
     * Base case: DP[0] = 1, empty tree, DP[1] = 1, 1 root tree
     * Induction rule: as described above DP[i] = DP[i - 1] * DP[n - i]
     * result = DP[0] * DP[n - 1] + DP[1] * DP[n - 2] + ... + DP[n - 1] * DP[0]
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int numTrees(int n) {
        int dp[] = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

}
