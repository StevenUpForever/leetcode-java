package tree.post_order;

import public_class.TreeNode;

public class Q404SumOfLeftLeaves {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: Tree
    //TAG: post order

    /**
     * 404. Sum of Left Leaves
     * Find the sum of all left leaves in a given binary tree.
     *
     * Example:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
     */

    /*
    Solution:
    flag the current node if it's left or right,
    merge left value and right value and report to uppper level, only if current flag is left and it's a leaf node will
    add self value

    Time: O(n)
    Space: O(n)
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return sumOfLeftLeavesHelper(root, false);
    }

    private int sumOfLeftLeavesHelper(TreeNode root, boolean leftFlag) {
        if (root == null) return 0;
        int left = sumOfLeftLeavesHelper(root.left, true);
        int right = sumOfLeftLeavesHelper(root.right, false);
        return left + right + (leftFlag && root.left == null && root.right == null ? root.val : 0);
    }

}
