package Problem91To100;

import public_class.ListNode;

public class Reverse_Linked_List_II {

    /**
     * 92. Reverse Linked List II
     * Reverse a linked list from position m to n. Do it in-place and in one-pass.

     For example:
     Given 1->2->3->4->5->NULL, m = 2 and n = 4,

     return 1->4->3->2->5->NULL.

     Note:
     Given m, n satisfy the following condition:
     1 ≤ m ≤ n ≤ length of list.
     */

    /**
     * Solution:
     * not the same as reverse LinkedList, as we don't know the final start node depends on different part of reverse
     * So we need to use another way to reverse nodes
     * 1. skip all nodes previous of reverse part
     * 2. do reverse part:
     *      1. reverse the current part with the next node 1- > [3->2] -> 4 ==> 1- > 4->[3->2]
     *      2. move this last node to the start node in next cycle
     *
     * Time: O(n)
     * Space: O(1)
     */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        //m and n represent the index + 1, sp m > 1, n > 1
        //After loop, pre is the pre node of the first reverse node
        while (m > 1) {
            pre = pre.next;
            m--;
            //Need also decrease n even the reverse is not begin
            n--;
        }
        ListNode cur = pre.next;
        ListNode next = cur.next;
        while (n > 1) {
            /*
            Every time put the next node to the first place, append after pre, then move pre to next cycle
            For example: if 1->2->3->4->5, reverse 2 to 5, the order is
            1->3->2->4->5
            1->4->3->2->5
            1->5->4->3->2
             */
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = cur.next;
            n--;
        }
        return dummy.next;
    }

}
