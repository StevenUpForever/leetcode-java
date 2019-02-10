package tree.bottom_up;

import public_class.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees {

    //TAG: Google
    //TAG: Uber
    //TAG: tree
    //TAG bottom_up

    /**
     * 652. Find Duplicate Subtrees
     * Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.
     *
     * Two trees are duplicate if they have the same structure with same node values.
     *
     * Example 1:
     *         1
     *        / \
     *       2   3
     *      /   / \
     *     4   2   4
     *        /
     *       4
     * The following are two duplicate subtrees:
     *       2
     *      /
     *     4
     * and
     *     4
     * Therefore, you need to return above trees' root in the form of a list.
     */

    /*
     * Solution:
     * 1st ask subtree is any node to any node or any node to leaf node
     * subtree should be any node to leaf node here
     * so DFS the tree, when in the call back:
     * concat left and right subStr with current node, to a path string, and put to a map
     * next time when map contains this string, and value is 1, means only appear once, add to list,
     * otherwise may dup again
     *
     * return the list
     *
     * Time: O(n)
     * Space: O(n) if any same height subtree is duplicated
     */

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        findDuplicateSubtreesHelper(root, new HashMap<>(), list);
        return list;
    }

    private String findDuplicateSubtreesHelper(TreeNode root, Map<String, Integer> map, List<TreeNode> list) {
        if (root == null) return "";
        String key = root.val + "," + findDuplicateSubtreesHelper(root.left, map, list) + ","
                + findDuplicateSubtreesHelper(root.right, map, list);
        if (map.getOrDefault(key, 0) == 1) list.add(root);
        map.put(key, map.getOrDefault(key, 0) + 1);
        return key;
    }

}
