package tree.bottom_up;

import public_class.TreeNode;

public class BalancedBinaryTree {

    //TAG: tree
    //TAG: bottom up
    //difficulty: easy

    /**
     * 110. Balanced Binary Tree
     * Given a binary tree, determine if it is height-balanced.
     *
     * For this problem, a height-balanced binary tree is defined as:
     *
     * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
     *
     * Example 1:
     *
     * Given the following tree [3,9,20,null,null,15,7]:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * Return true.
     *
     * Example 2:
     *
     * Given the following tree [1,2,2,3,3,null,null,4,4]:
     *
     *        1
     *       / \
     *      2   2
     *     / \
     *    3   3
     *   / \
     *  4   4
     * Return false.
     */

    /*
    Solution 1:
    dfs from up to down, compare if abs(height(root.left) - height(root.right)) <= 1

    Time: O(2^n)
    Space: O(n)
     */

    /*
    Solution 2:
    bottom up, use post order or same as call back stack, will not need visited every node many times but just once
    in helper function, if left height and right height abs diff is > 1 return Int.MAX so any next call back will
    keep return Int.MAX, otherwise, return max(left, right) + 1 (be aware of the res is Int.MAX, cannot + 1)

    Time: O(n)
    Space: O(n)
     */

    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) < Integer.MAX_VALUE;
    }

    private int isBalancedHelper(TreeNode root) {
        if (root == null) return 0;
        int left = isBalancedHelper(root.left);
        int right = isBalancedHelper(root.right);
        if (Math.abs(left - right) > 1) return Integer.MAX_VALUE;
        int max = Math.max(left, right);
        return max == Integer.MAX_VALUE ? max : max + 1;
    }

}
