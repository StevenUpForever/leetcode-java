package tree.bst.in_order;

import public_class.Node;

public class Q426ConvertBinarySearchTreeToSortedDoublyLinkedList {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: bst
    //TAG: inorder

    /**
     * 426. Convert Binary Search Tree to Sorted Doubly Linked List
     * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
     *
     * Let's take the following BST as an example, it may help you understand the problem better:
     *
     *
     *
     *
     *
     * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
     *
     * The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
     *
     *
     *
     *
     *
     * Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
     *
     * The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
     *
     *
     */

    /*
    Solution:

    inorder traversal, similar to reverse linkedList, and finally link head and tail node

    Time: O(n)
    Space: O(n)
     */

    private Node pre, head;
    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        node.left = pre;
        if (pre != null) pre.right = node;
        else head = node;
        pre = node;
        inorder(node.right);
    }

}
