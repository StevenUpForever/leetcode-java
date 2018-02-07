package Problem101To110;

import PublicClass.TreeNode;

public class Symmetric_Tree {

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

    /**
     * Solution:
     * recursion left node and right node
     *      Base case: if left is null and right is null, return true, current recursion is done
     *      if left is null or right is null only one is null, or value not equal not symmetric
     *      recursion rule: recursion left.left, right.right and left.right, right.left
     *
     * Time: O(n/2) half nodes times compare O(n)
     * Space: O(logn)
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
