package depth_first_search;

import public_class.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Q199BinaryTreeRightSideView {

    /**
     * 199. Binary Tree Right Side View
     *
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     *
     * Example:
     *
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     */

    /*
    Solution:
    Use BFS or DFS, from right -> left, only add first occurrence number on current level

    Time: O(n)
    Space: O(n)
     */

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        rightSideViewHelper(list, 0, root);
        return list;
    }

    private void rightSideViewHelper(List<Integer> list, int index, TreeNode root) {
        if (root == null) return;
        if (index == list.size()) {
            list.add(root.val);
        }
        rightSideViewHelper(list, index + 1, root.right);
        rightSideViewHelper(list, index + 1, root.left);
    }

}
