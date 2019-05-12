package tree.bst.in_order;

import public_class.TreeNode;

public class KthSmallestElementInABST {

    //TAG: Google
    //TAG: Facebook
    //TAG: Uber
    //TAG: bst
    //TAG: Tree
    //Difficulty: Medium

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

    /*
     * Solution:
     * in order traversal of bst will be an ascending array,
     * Inorder traversal bst and count k, when k == 0, we find kth smallest number and return
     *
     * Time: O(n)
     * Space: O(n)
     */

    private static int number = 0;
    private static int count = 0;

    public int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }

    /*
    Follow up:
    use array save all in-order numbers, when insert/delete (like a flow), then insert/delete at the proper place
    in the array, then return array[k - 1]
     */

}
