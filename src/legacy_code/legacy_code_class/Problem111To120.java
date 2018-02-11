package legacy_code.legacy_code_class;

/**
 * Created by ChengzhiJia on 3/15/17.
 */
public class Problem111To120 {
    /*
    116. Populating Next Right Pointers in Each Node
    Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 class-> NULL
     */
     class TreeLinkNode {
        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }

//    public void connect(TreeLinkNode root) {
//         if (root == null) return;
//         while (root.left != null) {
//             TreeLinkNode cur = root;
//             while (cur != null) {
//                 cur.left.next = cur.right;
//                 if (cur.next != null) cur.right.next = cur.next.left;
//                 cur = cur.next;
//             }
//             root = root.left;
//         }
//    }

    /*
    117. Populating Next Right Pointers in Each Node II
    Follow up for problem "Populating Next Right Pointers in Each Node".

What if the given tree could be any binary tree? Would your previous solution still work?

Note:

You may only use constant extra space.
For example,
Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
     */
    public void connect(TreeLinkNode root) {
         while (root != null) {
             TreeLinkNode cur = root;
             TreeLinkNode temp = null;
             TreeLinkNode nextHead = null;
             while (cur != null) {
                 if (cur.left != null) {
                     if (temp != null) temp.next = cur.left;
                     else nextHead = cur.left;
                     temp = cur.left;
                 }
                 if (cur.right != null) {
                     if (temp != null) temp.next = cur.right;
                     else nextHead = cur.right;
                     temp = cur.right;
                 }
                 cur = cur.next;
             }
             root = nextHead;
             temp = null;
             nextHead = null;
         }
    }
}
