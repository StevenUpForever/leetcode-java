package tree.bst;

import public_class.TreeNode;

public class InorderSuccessorInBST {

    //TAG: Facebook
    //TAG: Microsoft
    //TAG: tree
    //TAG: bst
    //Difficulty: meidum

    /**
     * 285. Inorder Successor in BST
     * Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
     *
     * Note: If the given node has no in-order successor in the tree, return null.
     */

    /*
    Solution 1:
    Use ascending order in BST, keep track pre and cur as global treeNode, when pre == p, means cur is successor
    of pre, then cur is the result

    Time: O(n)
    Space: O(n)
     */

    private TreeNode pre = null, res = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inorderSuccessorHelper(root, p);
        return res;
    }

    private void inorderSuccessorHelper(TreeNode root, TreeNode p) {
        if (root == null) return;
        inorderSuccessorHelper(root.left, p);
        if (pre != null && pre.val == p.val) res = root;
        pre = root;
        inorderSuccessorHelper(root.right, p);
    }

    /*
    Solution 2:
    check by root.val compare with p
    1. if root.val <= p.val move to right sub problem
    2. else check if left sub problem is null if so, current node is successor, because no value in left could larger than p
    else return left, left is valid result from valid sub problem

    Time: O(n)
    Space: O(n)
     */

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if (root.val <= p.val) return inorderSuccessor(root.right, p);
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            return (left != null) ? left : root;
        }
    }

}
