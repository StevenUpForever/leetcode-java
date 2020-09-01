package linkedlist;

import public_class.ListNode;

public class Q237DeleteNodeInALinkedList {

    //TAG: Microsoft
    //TAG: Apple
    //TAG: linked list
    //Difficulty: Easy

    /**
     * 237. Delete Node in a Linked List
     * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

     Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
     */

    public void deleteNode(ListNode node) {
        while (node.next != null) {
            node.val = node.next.val;
            if (node.next.next == null) node.next = null;
            else node = node.next;
        }
    }

}
