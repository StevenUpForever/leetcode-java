package legacy_code;

/**
 * Created by ChengzhiJia on 6/3/16.
 */
public class Problem231To240 {

    /*
    231. Power of Two
    Given an integer, write a function to determine if it is a power of two.
     */

    //Binary manipulation solution from leetCode, great one

//    bool isPowerOfTwo(int n) {
//        return (n>0&&((n&(n-1))==0));
//    }

    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        if (n <= 2) return true;
        while (n % 2 == 0 && n > 2) {
            n /= 2;
        }
        return n == 2;
    }

    /*
    235. Lowest Common Ancestor of a Binary Search Tree
    Given a binary search tree (bst), find the lowest common ancestor (LCA) of two given nodes in the bst.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
     */
    /*
    Approach: Use feature about call stack in recursive method, which each node need its left and right subtree to notify if any p or q found, if so, return itself to its super node, if no nodes found, return null, until the end when root node's call stack finished
     */
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null || root == p || root == q) return root; //Base case of recursion steps, find any node or reach bottom and found no node
//        TreeNode left = lowestCommonAncestor(root.left, p, q);
//        TreeNode right = lowestCommonAncestor(root.right, p, q);
//        //Do call stack steps about left subtree search result and right substree search result
//        if (left != null && right != null) return root;
//        else if (left == null && right == null) return null;
//        else return left == null ? right : left;
//    }

    /*
    236. Lowest Common Ancestor of a Binary Tree
    Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”

        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4
For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) return null;
        else if (left != null && right != null) return root;
        else return left == null ? right : left;
    }

    /*
    237. Delete Node in a Linked List
    Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
     */
    public void deleteNode(ListNode node) {
        if (node == null) return;
        while (node.next != null) {
            node.val = node.next.val;
            if (node.next.next == null) node.next = null;
            else node = node.next;
        }
    }

    /*
    238. Product of Array Except Self
    Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].

Follow up:
Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)
     */

    //Best Solution from legacy_code discussion
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    /*
    240. Search a 2D Matrix II
    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = 0, n = matrix[0].length - 1;
        int num = matrix[m][n];
        while (m < matrix.length && n >= 0) {
            int temp = matrix[m][n];
            if (temp == target) return true;
            else if (temp < target) m++;
            else n--;
        }
        return false;
    }

}
