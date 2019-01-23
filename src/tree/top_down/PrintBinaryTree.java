package tree.top_down;

import public_class.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PrintBinaryTree {

    //Difficulty: medium
    //TAG: Uber
    //TAG: Tree
    //TAG: recursion

    /**
     * 655. Print Binary Tree
     * Print a binary tree in an m*n 2D string array following these rules:
     *
     * The row number m should be equal to the height of the given binary tree.
     * The column number n should always be an odd number.
     * The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
     * Each unused space should contain an empty string "".
     * Print the subtrees following the same rules.
     * Example 1:
     * Input:
     *      1
     *     /
     *    2
     * Output:
     * [["", "1", ""],
     *  ["2", "", ""]]
     * Example 2:
     * Input:
     *      1
     *     / \
     *    2   3
     *     \
     *      4
     * Output:
     * [["", "", "", "1", "", "", ""],
     *  ["", "2", "", "", "", "3", ""],
     *  ["", "", "4", "", "", "", ""]]
     * Example 3:
     * Input:
     *       1
     *      / \
     *     2   5
     *    /
     *   3
     *  /
     * 4
     * Output:
     *
     * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
     *  ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
     *  ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
     *  ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
     * Note: The height of binary tree is in the range of [1, 10].
     */

    /*
    Solution:
    assume h = tree height, get from O(n) post order
    size of every list is 2^n - 1
    prepare for all list in a combined lists

    pre order the tree again, set mid value to root.value and recursion to left and right

    Time: O(logn * n (lists size) + n (get height) + n (dfs)) =  O(nlogn + n)
    Space O(logn * n + n max recursion levels) = O(nlogn + n)
     */

    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> lists = new ArrayList<>();
        int height = height(root);
        int size = (int)Math.pow(2, height) - 1;
        for (int i = 0; i < height; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                list.add("");
            }
            lists.add(list);
        }
        printTreeHelper(lists, 0, 0, size, root);
        return lists;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    private void printTreeHelper(List<List<String>> lists, int index, int left, int right, TreeNode root) {
        if (left > right || root == null) {
            return;
        }
        int mid = left + (right - left)/2;
        lists.get(index).set(mid, String.valueOf(root.val));
        printTreeHelper(lists, index + 1, left, mid - 1, root.left);
        printTreeHelper(lists, index + 1, mid + 1, right, root.right);
    }

}
