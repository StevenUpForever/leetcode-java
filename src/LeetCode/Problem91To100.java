package LeetCode;

/**
 * Created by ChengzhiJia on 3/15/17.
 */
public class Problem91To100 {
    /*
    98. Validate Binary Search Tree
    Given a binary tree, determine if it is a valid binary search tree (BST).

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
    public boolean isValidBST(TreeNode root) {
        //Use long as min and max value, because besides the recursion verify steps, about initial steps, Integer min and max is valid
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long min, long max) {
        if (root == null) return true;
        else if (root.val <= min || root.val >= max) return false;
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }
}
