package tree.bst;

import public_class.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class KthSmallestElementInABST {

    //TAG: Uber
    //TAG: bst
    //TAG: Tree

    /**
     * 230. Kth Smallest Element in a bst
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

     Note:
     You may assume k is always valid, 1 ≤ k ≤ bst's total elements.

     Follow up:
     What if the bst is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

     Credits:
     Special thanks to @ts for adding this problem and creating all test cases.
     */

    /**
     * Solution:
     * in order traversal of bst will be an ascending array,
     * Inorder traversal bst and put all integers into array list,
     * list is ascending array, return list(k - 1)
     *
     * Time: O(n)
     * Space: O(n)
     */

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        kthSmallestHelper(root, list);
        return k - 1 < 0 || k - 1 >= list.size() ? -1 : list.get(k - 1);
    }

    private void kthSmallestHelper(TreeNode root, List<Integer> list) {
        if (root == null) return;
        kthSmallestHelper(root.left, list);
        list.add(root.val);
        kthSmallestHelper(root.right, list);
    }

}
