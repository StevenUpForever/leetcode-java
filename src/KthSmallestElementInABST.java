import public_class.TreeNode;

public class KthSmallestElementInABST {

    //TAG: Uber
    //TAG:

    /**
     * 230. Kth Smallest Element in a BST
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

     Note:
     You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

     Follow up:
     What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

     Credits:
     Special thanks to @ts for adding this problem and creating all test cases.
     */

    /**
     * Solution:
     * in order traversal of BST will be an ascending array,
     */

    public int kthSmallest(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k-1-count); // 1 is counted as current node
        }

        return root.val;
    }

    public int countNodes(TreeNode n) {
        if (n == null) return 0;
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

}
