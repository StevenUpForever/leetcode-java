package linkedlist;

import public_class.ListNode;

public class ReverseLinkedList {

    //TAG: Facebook
    //TAG: Microsoft
    //TAG: Amazon
    //TAG: Apple
    //TAG: Uber
    //TAG: LinkedList
    //TAG: Recursion
    //Difficulty: Easy

    /**
     * 206. Reverse Linked List
     * Reverse a singly linked list.

     click to show more hints.

     Hint:
     A linked list can be reversed either iteratively or recursively. Could you implement both?
     */

    /*
     * Solution 1: Iterative
     * keep track of pre node and cur node, loop the list, every time
     *      keep track of cur.next temporarily, point cur.next = pre, then parallelly move pre to cur, cur to next
     * Until cur = null, at this time, pre is the last node of list, means the head of new reversed list, return pre
     *
     * Time: O(n)
     * Space: O(1)
     */

    public ListNode reverseListS1(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /*
     * Solution 2: recursion
     * 1. find the end of the list recursively, this end node will be the begin node of reversed list
     * 2. at track back steps, point cur next.next = head, head.next = null
     *
     * Time: O(n)
     * Space: O(n)
     */

    public ListNode reverseS2(ListNode head) {
        /*
        if there's no next == nil，at last step，head = nil，head.next throw exception
         */
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        ListNode newHead = reverseS2(next);
        /*
        When met this line, next is the current newHead node which when first recursive level,
        it’s the newHead/last node, head is the last second node
         */
        next.next = head;
        //This step will be useful when met the last node，it will 1 -> 2 && 2 -> 1，need to set 1.next point to nil
        head.next = null;
        return newHead;
    }

}
