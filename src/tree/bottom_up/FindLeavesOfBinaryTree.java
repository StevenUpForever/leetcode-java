package tree.bottom_up;

import public_class.TreeNode;
import java.util.*;

public class FindLeavesOfBinaryTree {

    //TAG: LinkedList
    //TAG: Tree
    //TAG: bottom up
    //Difficulty: Medium

    /**
     * 366. Find Leaves of Binary Tree
     * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

     Example:
     Given binary tree
     1
     / \
     2   3
     / \
     4   5
     Returns [4, 5, 3], [2], [1].

     Explanation:
     1. Removing the leaves [4, 5, 3] would result in this tree:

     1
     /
     2
     2. Now removing the leaf [2] would result in this tree:

     1
     3. Now removing the leaf [1] would result in the empty tree:

     []
     Returns [4, 5, 3], [2], [1].
     */

    /**
     * Solution:
     * Trick from get max height of binary tree, return Max(height(root.left), height(root.right)) + 1
     * for each level, we know the level count from bottom to top (max height is counting in call back way)
     * and the level is also the index of list in final list, when doing this in call back part
     *
     * Time: O(n) loop all node once
     * Space: O(n)
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(res, root);
        return res;
    }

    //Same as max height problem, need to return current height as level for current call back process
    private int height(List<List<Integer>> res, TreeNode root) {
        //if null, equals to -1, so leaf node could be 0, the first index in res list
        if (root == null) return -1;
        int level = Math.max(height(res, root.left), height(res, root.right)) + 1;
        if (level >= res.size()) res.add(new ArrayList<>());
        res.get(level).add(root.val);
        return level;
    }

}
