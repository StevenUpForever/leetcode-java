package breadth_first_search;

import public_class.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {

    //TAG: Uber
    //Breadth first search

    /**
     * 297. Serialize and Deserialize Binary Tree
     * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

     Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

     For example, you may serialize the following tree

     1
     / \
     2   3
     / \
     4   5
     as "[1,2,3,null,null,4,5]", just the same as how legacy_code OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.



     Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

     Credits:
     Special thanks to @Louis1992 for adding this problem and creating all test cases.
     */

    /**
    Use Breadth first search for Serialize and Deserialize
     */

    public class Codec {

        private Queue<TreeNode> queue;
        private List<String> list;

        public Codec() {
            queue = new LinkedList<>();
            list = new ArrayList<>();
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";
             queue.add(root);
             list.add(String.valueOf(root.val));
             while (!queue.isEmpty()) {
                 TreeNode poll = queue.poll();
                 if (poll.left != null) {
                     queue.offer(poll.left);
                     list.add(String.valueOf(poll.left.val));
                 } else list.add("null");
                 if (poll.right != null) {
                     queue.offer(poll.right);
                     list.add(String.valueOf(poll.right.val));
                 } else list.add("null");
             }
             StringBuilder builder = new StringBuilder();
             for (String str: list) {
                 builder.append(str);
                 builder.append(",");
             }
             builder.deleteCharAt(builder.length() - 1);
             return builder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.length() == 0) return null;
            String[] strs = data.split(",");
            TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
            TreeNode res = root;
            int index = 1;
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (!strs[index].equals("null")) {
                    poll.left = new TreeNode(Integer.parseInt(strs[index]));
                    queue.offer(poll.left);
                }
                index++;
                if (!strs[index].equals("null")) {
                    poll.right = new TreeNode(Integer.parseInt(strs[index]));
                    queue.offer(poll.right);
                }
                index++;
            }
            return res;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

}
