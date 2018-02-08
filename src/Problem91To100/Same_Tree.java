package Problem91To100;

import public_class.TreeNode;

public class Same_Tree {

    /**
     * 100. Same Tree
     * Given two binary trees, write a function to check if they are equal or not.

     Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
     */

    /**
     * Solution:
     * Traversal the p and q in same order, if at any step,
     *      one is null and another is not
     *      or the value is not same, return false
     * Otherwise if all traversal done return true
     *
     * Time: O(n) n represent total nodes of the tree
     * Space: O(logn)
     */

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        //First check validation of current p and q, then means all are not null, compare their values
        else if (p == null || q == null || p.val != q.val) return false;
        //Traversal in same direction
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
