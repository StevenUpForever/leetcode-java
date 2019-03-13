package tree.post_order;

import public_class.TreeNode;

public class MaximumDepthOfBinaryTree {

    //TAG: Uber
    //TAG: LinkedIn
    //TAG: Tree
    //TAG: bottom up
    //Difficulty: Easy

    /**
     * 104. Maximum Depth of Binary Tree
     * Given a binary tree, find its maximum depth.

     The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     */

    /**
     * Solution:
     * recursion to left sub problem and right sub problem
     * find the max of left and right result when bottom to up
     * +1 when recursion back to upper level
     *
     * Time: O(n)
     * Space: O(n) if tree is not balanced and all nodes on one branch
     */

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
