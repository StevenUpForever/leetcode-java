package LeetCode;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
/**
 * Created by ChengzhiJia on 6/10/16.
 */
public class Problem101To110 {

    /*
    102. Binary Tree Level Order Traversal
    Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        addNewNode(result, root, 0);
        return result;
    }

    private void addNewNode(List<List<Integer>> result, TreeNode node, int currentHeight) {
        if (node == null) return;
        if (currentHeight >= result.size()) result.add(new ArrayList<Integer>());
        result.get(currentHeight).add(node.val);
        addNewNode(result, node.left, currentHeight + 1);
        addNewNode(result, node.right, currentHeight + 1);
    }

    /*
    103. Binary Tree Zigzag Level Order Traversal
    Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
     */
    /*
    Approach: Be aware of push sequence about odd and even levels
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int level = 0;
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                if (level % 2 == 0) {
                    TreeNode cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left != null) deque.offerLast(cur.left);
                    if (cur.right != null) deque.offerLast(cur.right);
                } else {
                    TreeNode cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right != null) deque.offerFirst(cur.right);
                    if (cur.left != null) deque.offerFirst(cur.left);
                }
            }
            res.add(list);
            level++;
        }
        return res;
    }

    /*
    108. Convert Sorted Array to Binary Search Tree
    Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return createBST(0, nums.length - 1, nums);
    }

    private TreeNode createBST(int start, int end, int[] nums) {
        if (start <= end) {
            int middle = (start + end)/2;
            TreeNode node = new TreeNode(nums[middle]);
            node.left = createBST(start, middle - 1, nums);
            node.right = createBST(middle + 1, end, nums);
            return node;
        } else return null;
    }

}
