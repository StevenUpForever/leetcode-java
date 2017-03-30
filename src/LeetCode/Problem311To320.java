package LeetCode;
import java.util.*;
/**
 * Created by ChengzhiJia on 6/10/16.
 */
public class Problem311To320 {
    /*
    311. Sparse Matrix Multiplication
    Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
     */
    public int[][] multiply(int[][] A, int[][] B) {
        int[][] res = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < B[0].length; k++) {
                       if (B[j][k] != 0)res[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }
        return res;
    }

    /*
    314. Binary Tree Vertical Order Traversal
    Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:

Given binary tree [3,9,20,null,null,15,7],
   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7
return its vertical order traversal as:
[
  [9],
  [3,15],
  [20],
  [7]
]
Given binary tree [3,9,8,4,0,1,7],
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
return its vertical order traversal as:
[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
return its vertical order traversal as:
[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
     */
    /*
    Approach 1: DFS, list separate by the relative order of left and right node, which when goto left, index--, goto right index++
    sort index (using min heap) at last to keep order in final result arrayList
    keep level in node cell
     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2
   in this case, for DFS, it was 2 -> ... -> 8, but vertical order it's 8 -> 2, separate node by level
   Sort every list by level
   Add list to final result

   Approach 2: BFS, index is same as separate by node.left and node.right
   But in BFS, do not need to care about level variable, do not need to sort level
   due to all index variables are continuous number, so could keep global min and max as final index bound, which all integer number must be some index in this range, do not need to sort index
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        HashMap<Integer, List<TreeNodeCell>> map = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        verticalOrderHelper(map, queue, new TreeNodeCell(root, 0, 0));
        while (!queue.isEmpty()) {
            List<TreeNodeCell> list = map.get(queue.poll());
            Collections.sort(list, new Comparator<TreeNodeCell>() {
                @Override
                public int compare(TreeNodeCell o1, TreeNodeCell o2) {
                    return o1.level - o2.level;
                }
            });
            List<Integer> newList = new ArrayList<>();
            for (TreeNodeCell cell: list) {
                newList.add(cell.node.val);
            }
            res.add(newList);
        }
        return res;
    }

    private void verticalOrderHelper(HashMap<Integer, List<TreeNodeCell>> map, PriorityQueue<Integer> queue, TreeNodeCell root) {
        if (root.node == null) return;
        if (map.get(root.index) == null) {
            List<TreeNodeCell> list = new ArrayList<>();
            list.add(root);
            map.put(root.index, list);
            queue.offer(root.index);
        } else {
            map.get(root.index).add(root);
        }
        verticalOrderHelper(map, queue, new TreeNodeCell(root.node.left, root.level + 1, root.index - 1));
        verticalOrderHelper(map, queue, new TreeNodeCell(root.node.right, root.level + 1, root.index + 1));
    }

    private class TreeNodeCell {
        TreeNode node;
        int level;
        int index;
        TreeNodeCell(TreeNode node, int level, int index) {
            this.node = node;
            this.level = level;
            this.index = index;
        }
    }


    /*
    316. Remove Duplicate Letters
    Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"
     */
    public String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

}
