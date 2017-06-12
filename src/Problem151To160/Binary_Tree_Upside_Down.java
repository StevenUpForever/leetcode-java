package Problem151To160;

import PublicClass.TreeNode;

public class Binary_Tree_Upside_Down {

    /**
     * 156. Binary Tree Upside Down
     * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

     For example:
     Given a binary tree {1,2,3,4,5},
     1
     / \
     2   3
     / \
     4   5
     return the root of the binary tree [4,5,2,#,#,3,1].
     4
     / \
     5   2
     / \
     3   1
     confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
     */

    /**
     * Solution:
     * as the reversed tree, it's also can be rotated to:
     *       1
     *     /
     *    2 - 3
     *  /
     * 4 - 5
     *
     * which means after reversed, left.right = right, left.right = node, node.left = null, node.right = null
     * So we need to know the node, node.left, node.right when do a smaller reverse problem (recursion), the left should be returned as current level node
     */

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        //Make sure first begin node do have left, right to reverse
        if (root == null || root.left == null) return root;
        //cur is only recorded as the result node to return at current level
        TreeNode cur = upsideDownBinaryTree(root.left);
        //root is current left node which along with left and right to reverse
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return cur;
    }

}
