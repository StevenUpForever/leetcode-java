package tree.post_order;

import public_class.TreeNode;

public class DiameterOfBinaryTree {

    //TAG: Google
    //TAG: Facebook
    //TAG: tree
    //TAG: bottom up
    //Difficulty: Easy

    /**
     * 543. Diameter of Binary Tree
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     *
     * Example:
     * Given a binary tree
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     *
     * Note: The length of path between two nodes is represented by the number of edges between them.
     */

    /**
     * Solution:
     * Going to need max length from left subtree and right subtree, l + r + 1 is current max length until current
     * node as root, update max length, return max left or right + 1 to above level
     *
     * Time: O(n)
     * Space: O(n)
     */

    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return res;
        diameterOfBinaryTreeHelper(root);
        return res - 1;
    }

    private int diameterOfBinaryTreeHelper(TreeNode root) {
        if (root == null) return 0;
        int left = diameterOfBinaryTreeHelper(root.left);
        int right = diameterOfBinaryTreeHelper(root.right);
        res = Math.max(res, left + right + 1);
        return Math.max(left, right) + 1;
    }

}
