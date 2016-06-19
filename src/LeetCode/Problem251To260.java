package LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChengzhiJia on 6/5/16.
 */
public class Problem251To260 {

    /*
    257. Binary Tree Paths
    Given a binary tree, return all root-to-leaf paths.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]
     */

    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> result = new ArrayList<>();
        if (root == null) return result;
        pathSearch(result, "", root);
        return result;
    }

    void pathSearch(ArrayList<String> result, String str, TreeNode node) {
        if (!str.equals("")) str += "->";
        str += node.val;
        if (node.left == null && node.right == null) {
            result.add(str);
        } else {
            if (node.left != null) pathSearch(result, str, node.left);
            if (node.right != null) pathSearch(result, str, node.right);
        }
    }

}
