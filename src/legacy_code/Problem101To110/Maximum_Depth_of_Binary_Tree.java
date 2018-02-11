package legacy_code.Problem101To110;

import public_class.TreeNode;

public class Maximum_Depth_of_Binary_Tree {

    /**
     * 104. Maximum Depth of Binary Tree
     * Given a binary tree, find its maximum depth.

     The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     */

    /**
     * Solution:
     * recursion to left sub problem and right sub problem
     * find the max of left and right result
     * +1 when recursion to next level
     *
     * Time: O(n)
     * Space: O(n) if tree is not balanced and all nodes on one branch
     */

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

}
