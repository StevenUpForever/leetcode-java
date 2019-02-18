package tree.reconstruct;

import public_class.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Q449SerializeAndDeserializeBST {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: tree
    //TAG: bst

    /**
     * 449. Serialize and Deserialize BST
     * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
     *
     * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.
     *
     * The encoded string should be as compact as possible.
     *
     * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
     */

    /*
    Solution:
    due to serialize one string, stateless, we need rebuild BST from one traversal, which pre-order will fit for BST
     */

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode poll = stack.pop();
            builder.append(poll.val).append(",");
            if (poll.right != null) stack.push(poll.right);
            if (poll.left != null) stack.push(poll.left);
        }
        return builder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] strs = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String str: strs) {
            queue.offer(Integer.parseInt(str));
        }
        return constructBST(queue, Integer.MAX_VALUE);
    }

    private TreeNode constructBST(Queue<Integer> queue, int max) {
        if (queue.isEmpty() || queue.peek() >= max) return null;
        int poll = queue.poll();
        TreeNode root = new TreeNode(poll);
        root.left = constructBST(queue, poll);
        root.right = constructBST(queue, max);
        return root;
    }

}
