package tree.bst;

import public_class.TreeNode;

public class LowestCommonAncestorOfABinarySearchTree {

    //TAG: Tree
    //TAG: bottom up
    //TAG: LCA
    //Difficulty: Easy

    /**
     * Lowest Common Ancestor of a Binary Search Tree
     * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

     According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

     _______6______
     /              \
     ___2__          ___8__
     /      \        /      \
     0      _4       7       9
     /  \
     3   5
     For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
     */

    /**
     * Solution:
     * Corner case:
     * 1. if any node is not in the tree
     * 2. if one node is in the tree
     *
     * The solution is not considering these cases
     *
     *According to BST feature, not need to go over all left and right sub solutions,
     * When not in (p, q) scope, just recursion try to go into the scope
     * once root goes into (p, q) scope, return root, the root will be the LCA, from top to down, the first one
     * within (p, q) scope is the LCA
     *
     * Time: O(n)
     * Space: O(1)
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        if (root.val > Math.max(p.val, q.val)) return lowestCommonAncestor(root.left, p, q);
        else if (root.val < Math.min(p.val, q.val)) return lowestCommonAncestor(root.right, p, q);
        else return root;
    }

}
