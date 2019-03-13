package tree.pre_order;

import public_class.TreeNode;

public class SymmetricTree {

    //TAG: LinkedIn
    //TAG: Apple
    //TAG: Tree
    //TAG: top down
    //Difficulty: Easy

    /**
     * 101. Symmetric Tree
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

     For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

     1
     / \
     2   2
     / \ / \
     3  4 4  3
     But the following [1,2,2,null,3,null,3] is not:
     1
     / \
     2   2
     \   \
     3    3
     Note:
     Bonus points if you could solve it both recursively and iteratively.
     */

    /*
     * Solution:
     * Similar to LCA lowest common ancestors
     * recursion left node and right node
     *      Base case: if left == right (include left == null && right == null) return true
     *      if left != right, include val not equal or any one is null, not symmetric and return false
     *      Symmetric recursion rule: recursion left.left, right.right and left.right, right.left
     *
     * Time: O(n/2) half nodes times compare O(n)
     * Space: O(n)
     */

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }

    private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        else if (left == null || right == null || left.val != right.val) return false;
        return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right, right.left);
    }

}
