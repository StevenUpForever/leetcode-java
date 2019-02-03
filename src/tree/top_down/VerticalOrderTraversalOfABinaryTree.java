package tree.top_down;

import public_class.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class VerticalOrderTraversalOfABinaryTree {

    //Difficulty: medium
    //TAG: tree
    //TAG: top_down

    /**
     * 987. Vertical Order Traversal of a Binary Tree
     * Given a binary tree, return the vertical order traversal of its nodes values.
     *
     * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
     *
     * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
     *
     * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
     *
     * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [3,9,20,null,null,15,7]
     * Output: [[9],[3,15],[20],[7]]
     * Explanation:
     * Without loss of generality, we can assume the root node is at position (0, 0):
     * Then, the node with value 9 occurs at position (-1, -1);
     * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
     * The node with value 20 occurs at position (1, -1);
     * The node with value 7 occurs at position (2, -2).
     * Example 2:
     *
     *
     *
     * Input: [1,2,3,4,5,6,7]
     * Output: [[4],[2],[1,5,6],[3],[7]]
     * Explanation:
     * The node with value 5 and the node with value 6 have the same position according to the given scheme.
     * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
     *
     *
     * Note:
     *
     * The tree will have between 1 and 1000 nodes.
     * Each node's value will be between 0 and 1000.
     */

    /*
    Solution:

    Use a global offset to indicate real list of lists, see comments, same size yLists save all y coordinators

    the do dfs

    Time: O(n)
    Space: O(n)
     */

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> lists = new LinkedList<>();
        verticalTraversalHelper(lists, new LinkedList<>(), root, 0, 0);
        return lists;
    }

    /*
    When a new x < 0 found, offset++, e.g. when first x = -1 found, lists[0] actually represent x = -1
    real root (x == 0) is lists[1] we set offset = 1, then offset + x is the real index in lists
     */
    private int offset;
    private void verticalTraversalHelper(List<List<Integer>> lists, List<List<Integer>> yLists, TreeNode root, int x, int y) {
        if (root == null) return;
        //Sync result lists and y lists to same size
        if (offset + x < 0) {
            lists.add(0, new LinkedList<>());
            yLists.add(0, new LinkedList<>());
            offset++;
        }
        else if (offset + x >= lists.size()) {
            lists.add(new LinkedList<>());
            yLists.add(new LinkedList<>());
        }
        List<Integer> list = lists.get(offset + x), yList = yLists.get(offset + x);
        //Find a right place to insert the root.val, (the last position that has same y value with root.val)
        int index = 0;
        while (index < list.size() && yList.get(index) <= y) {
            if (yList.get(index) == y && list.get(index) > root.val) break;
            index++;
        }
        list.add(index, root.val);
        yList.add(index, y);
        verticalTraversalHelper(lists, yLists, root.left, x - 1, y + 1);
        verticalTraversalHelper(lists, yLists, root.right, x + 1, y + 1);
    }

    /*
    Solution 2:
    use more data structure and easily save the data

    Map<Integer, Map<Integer, Set/List>> is Map<x, Map<y, Set/List>>

    Sample: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/discuss/231148/Java-TreeMap-Solution
     */

}
