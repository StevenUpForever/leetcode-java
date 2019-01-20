package tree.bottom_up;

import public_class.TreeNode;

public class DistributeCoinsInBinaryTree {

    //Difficulty: medium
    //TAG: tree
    //TAG: bottom up

    /**
     * 979. Distribute Coins in Binary Tree
     * Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.
     *
     * In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)
     *
     * Return the number of moves required to make every node have exactly one coin.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [3,0,0]
     * Output: 2
     * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
     * Example 2:
     *
     *
     *
     * Input: [0,3,0]
     * Output: 3
     * Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
     * Example 3:
     *
     *
     *
     * Input: [1,0,2]
     * Output: 2
     * Example 4:
     *
     *
     *
     * Input: [1,0,0,null,3]
     * Output: 4
     *
     *
     * Note:
     *
     * 1<= N <= 100
     * 0 <= node.val <= N
     */

    /*
    Solution:
    bottom up post order, for each level, we need to know:
    1. value calculation: means value contributed from left and right (< 0 means need value), plus current node.val
    then report to upper level
    2. steps from left and right, which no matter provide or need value, steps are abs(report value)

    Time: O(n)
    Space: O(n)
     */

    private int steps = 0;
    public int distributeCoins(TreeNode root) {
        distributeCoinsHelper(root);
        return steps;
    }

    private int distributeCoinsHelper(TreeNode root) {
        if (root == null) return 0;
        int left = distributeCoinsHelper(root.left);
        int right = distributeCoinsHelper(root.right);
        steps += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }

}
