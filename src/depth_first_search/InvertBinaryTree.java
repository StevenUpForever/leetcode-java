package depth_first_search;

import public_class.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertBinaryTree {

    //Difficulty: Easy
    //TAG: Uber
    //TAG: DFS
    //TAG: BFS

    /**
     * 226. Invert Binary Tree
     * Invert a binary tree.
     *
     * Example:
     *
     * Input:
     *
     *      4
     *    /   \
     *   2     7
     *  / \   / \
     * 1   3 6   9
     * Output:
     *
     *      4
     *    /   \
     *   7     2
     *  / \   / \
     * 9   6 3   1
     */

    /*
    Solution:
    DFS, assume that sub problem invertTree(left) invertTree(right) is ready, then just swap these two sub problem

    Time: O(n)
    Space: O(n)
     */

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    /*
    Solution 2:
    BFS
     */

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) return null;
        final Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        return root;
    }



}
