package tree.bottom_up;

import PublicClass.TreeNode;
import jdk.nashorn.internal.ir.WhileNode;

public class DeleteNodeInABST {

    //TAG: Uber
    //TAG: BST
    //TAG: Tree
    //TAG: Bottom up

    /**
     * 450. Delete Node in a BST
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

     Basically, the deletion can be divided into two stages:

     Search for a node to remove.
     If the node is found, delete the node.
     Note: Time complexity should be O(height of tree).

     Example:

     root = [5,3,6,2,4,null,7]
     key = 3

     5
     / \
     3   6
     / \   \
     2   4   7

     Given key to delete is 3. So we find the node with value 3 and delete it.

     One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

     5
     / \
     4   6
     /     \
     2       7

     Another valid answer is [5,2,6,null,4,null,7].

     5
     / \
     2   6
     \   \
     4   7
     */

    /**
     * Solution:
     * Return TreeNode due to if delete the root, need to return the new root
     *
     * Delete the node with value key has 3 situations:
     * 1. when node.left == null && node.right == null just delete node
     * 2. if node.right == null, delete node, and move left to node
     * 3. if node.right != null, delete node, and move left most node in right sub-tree to node
     *
     * Time: O(n)
     * Space: O(n)
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (root.val < key) root.right = deleteNode(root.right, key);
        else if (root.val > key) root.left = deleteNode(root.left, key);
        else {
            if (root.right == null) return root.left;
            else {
                TreeNode leftMost = leftMost(root.right);
                root.val = leftMost.val;
                root.right = deleteNode(root.right, leftMost.val);
            }
        }
        return root;
    }

    private TreeNode leftMost(TreeNode root) {
        while (root != null && root.left != null) root = root.left;
        return root;
    }

}
