package tree.post_order;

import public_class.TreeNode;

public class Q1022SumOfRootToLeafBinaryNumbers {

    //Difficulty: easy
    //TAG: tree
    //TAG: post order

    /**
     * 1022. Sum of Root To Leaf Binary Numbers
     * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
     *
     * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
     *
     * Return the sum of these numbers.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [1,0,1,0,1,0,1]
     * Output: 22
     * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
     *
     *
     * Note:
     *
     * The number of nodes in the tree is between 1 and 1000.
     * node.val is 0 or 1.
     * The answer will not exceed 2^31 - 1.
     */

    /*
    Solution:

    add a new value by << 1 then check if it's the leaf node (left == right)

    if so, return current value, otherwise return (left + right) % mod

    Time: O(n)
    Space: O(n)
     */



    private int mod = 1000000007;
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int val) {
        if (root == null) return 0;
        int next = (val * 2 + root.val) % mod;
        return root.left == root.right ? next : (dfs(root.left, next) + dfs(root.right, next))%mod;
    }

}
