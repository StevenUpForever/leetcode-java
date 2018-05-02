package tree.top_down;

import public_class.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {

    //TAG: Google
    //TAG: Facebook
    //TAG: Apple
    //TAG: tree
    //Difficulty: Easy

    /**
     * 257. Binary Tree Paths
     * Given a binary tree, return all root-to-leaf paths.
     *
     * For example, given the following binary tree:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     * All root-to-leaf paths are:
     *
     * ["1->2->5", "1->3"]
     */

    /**
     * Solution:
     * Pre order traversal, append value and -> (if needed) to string, and recursion to left and right
     *
     * Better use string instead of stringBuilder and delete in call stack, due to number may be more than one digit
     * and may neg number, so when became string the length may not be 1 and hard to delete
     *
     * Time: O(n)
     * Space: O(n)
     */

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        binaryTreePathsHelper(list, root, "");
        return list;
    }

    private void binaryTreePathsHelper(List<String> list, TreeNode root, String curStr) {
        if (root == null) {
            return;
        }
        if (curStr.length() > 0) curStr += "->";
        curStr += root.val;
        if (root.left == null && root.right == null) {
            list.add(curStr);
            return;
        }
        binaryTreePathsHelper(list, root.left, curStr);
        binaryTreePathsHelper(list, root.right, curStr);
    }

}
