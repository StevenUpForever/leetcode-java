package Problem91To100;

import PublicClass.TreeNode;
import java.util.*;

public class Validate_Binary_Search_Tree {

    /**
     * 98. Validate Binary Search Tree
     * Given a binary tree, determine if it is a valid binary search tree (BST).

     Assume a BST is defined as follows:

     The left subtree of a node contains only nodes with keys less than the node's key.
     The right subtree of a node contains only nodes with keys greater than the node's key.
     Both the left and right subtrees must also be binary search trees.
     Example 1:
     2
     / \
     1   3
     Binary tree [2,1,3], return true.
     Example 2:
     1
     / \
     2   3
     Binary tree [1,2,3], return false.
     */

    /**
     * Solution 1: recursion
     * have a min value and max value for current level, if value over scope return false, otherwise recursion to next left subtree and right subtree
     * Base case: if root == null return true means touch the end, all nodes validated (current branch), then it's true
     * recursion rule: when recursion the left tree, max = curVal - 1, right tree: min = curval + 1, if any node over the scope return false
     *
     * Time: O(n) n represent number of nodes in BST
     * Space: O(logn)
     */

    public boolean isValidBSTS1(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long min, long max) {
        if (root == null) return true;
        //Use long type number to avoid if root.val == Int.max or min, but initially min and max are Int.min, Int.max
        if (root.val <= min || root.val >= max) return false;
        //If any return is false, total value is false
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }

    /**
     * Solution 2: iterative
     * do a in order traversal of this binary tree, which depends on the feature of BST, in order traversal is a ascending order
     * Keep a pre node, update when cur node goes right side (in order place)
     * Compare pre node value and cur value, if pre >= cur return false
     *
     * Time: O(n)
     * Space: O(n) if only left tree, and all will push into stack
     */

    public boolean isValidBSTS2(TreeNode root) {
        //Logic explained at Binary_Tree_Inorder_Traversal Solution 2 Problem91To100
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val >= root.val) return false;
            pre = root;
            root = root.right;
        }
        return true;
    }

}
