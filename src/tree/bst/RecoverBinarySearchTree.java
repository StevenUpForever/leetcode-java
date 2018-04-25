package tree.bst;

import public_class.TreeNode;

public class RecoverBinarySearchTree {

    //TAG: Uber
    //TAG: BST
    //Difficulty: Hard

    /**
     * 99. Recover Binary Search Tree
     * Two elements of a binary search tree (BST) are swapped by mistake.
     *
     * Recover the tree without changing its structure.
     *
     * Example 1:
     *
     * Input: [1,3,null,null,2]
     *
     *    1
     *   /
     *  3
     *   \
     *    2
     *
     * Output: [3,1,null,null,2]
     *
     *    3
     *   /
     *  1
     *   \
     *    2
     * Example 2:
     *
     * Input: [3,1,4,null,null,2]
     *
     *   3
     *  / \
     * 1   4
     *    /
     *   2
     *
     * Output: [2,1,4,null,null,3]
     *
     *   2
     *  / \
     * 1   4
     *    /
     *   3
     * Follow up:
     *
     * A solution using O(n) space is pretty straight forward.
     * Could you devise a constant space solution?
     */

    /**
     * Solution:
     * Use the feature that in order traversal bst is ascending order
     * keep track the pre node, when find two nodes that not pre < cur but pre >= cur, we found one of the node
     * that should swap, depends on it's the first time or second we found this pair, see comments in the code
     *
     * after found two nodes need swap, (after in order traversal) swap the value of them two
     *
     * Time: O(n)
     * Space: O(1)
     */

    private TreeNode one, two;
    private TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public TreeNode recoverTree(TreeNode root) {
        recoverHelper(root);
        int temp = one.val;
        one.val = two.val;
        two.val = temp;
        return root;
    }

    private void recoverHelper(TreeNode root) {
        if (root == null) return;
        recoverHelper(root.left);
        /*
        if first met any number not sorted, pre > cur, then the pre is the not sorted number
        1. for a < b < c first not sorted number like a < c > b, will not know when pre < cur, but could know
        that first not sorted number is the larger one which is pre
         */
        if (one == null && pre.val >= root.val) one = pre;
        /*
        2. after above situation, left a > b < c, which b is the smaller number that not sorted, which is root
         */
        if (one != null && pre.val >= root.val) two = root;
        pre = root;
        recoverHelper(root.right);
    }

}
