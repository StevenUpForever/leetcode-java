package LeetCode;

/**
 * Created by ChengzhiJia on 3/15/17.
 */
public class Problem91To100 {
    /*
    91. Decode Ways
    A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
<<<<<<< Updated upstream

Show Company Tags
Show Tags
     */
    /*
    Approach: DP problem, M[0] = 0, M[i] represent the max decode ways for substring to i
    M[i] = s[i] == '0' ? 0 : s[i + 1] + s[i + 2] <= 26 ? M[i + 1] + M[i + 2] : M[i + 1]
    Why loop from end to front: save a base case when first character is '0'
    What does induction rule mean: when s[i + 1] + s[i + 2] > 26, there's only one way to separate it, so the same as M[i + 1], otherwise have two conditions, when treat s[i + 1] + s[i + 2] as one part, it's same as > 26, use the M[i + 1], when separate to two parts, treate M[i + 1] as the one part, so use M[i + 2], so use M[i + 1] + M[i + 2]
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int len = chars.length;
        int[] dp = new int[len + 1];
        dp[len]  = 1;
        dp[len - 1] = chars[len - 1] != '0' ? 1 : 0;
        for (int i = len - 2; i >= 0; i--) {
            if (chars[i] != '0') dp[i] = (chars[i] - '0') * 10 + chars[i + 1] - '0' <= 26 ? dp[i + 1] + dp[i + 2] : dp[i + 1];
        }
        return dp[0];
    }

    /*
    98. Validate Binary Search Tree
    Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:
    2
   / \
  1   3
Binary tree [2,1,3], return true.
Example 2:
    1
   / \
  2   3
Binary tree [1,2,3], return false.
     */
    public boolean isValidBST(TreeNode root) {
        //Use long as min and max value, because besides the recursion verify steps, about initial steps, Integer min and max is valid
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode root, long min, long max) {
        if (root == null) return true;
        else if (root.val <= min || root.val >= max) return false;
        return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
    }
}
