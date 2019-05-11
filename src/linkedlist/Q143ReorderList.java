package linkedlist;

import public_class.ListNode;

public class Q143ReorderList {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: linkedList

    /**
     * 143. Reorder List
     * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
     * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     * Example 1:
     *
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     * Example 2:
     *
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     */

    /*
    Solution:

    1. find the median of the list, if even, second half is  n/2, if odd, second half start at mid + 1
    2. set first half end.next = null, and reverse second half
    3. concat first and second half

    Time: O(n)
    Space: O(1)
     */

    public void reorderList(ListNode head) {
        if (head == null) return;
        ListNode slow = head, fast = head, pre = null;
        while (fast != null) {
            pre = slow;
            slow = slow.next;
            /*
            Make sure slow stop at the right start of the second half
            for 1 2 3 4 5 stop at 4, for 1 2 3 4, stop at 3
             */
            fast = fast.next == null ? fast.next : fast.next.next;
        }
        pre.next = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = fast;
            fast = slow;
            slow = next;
        }
        //after reverse fast is the start of the reverse list
        slow = head;
        while (slow != null && fast != null) {
            ListNode sNext = slow.next, fNext = fast.next;
            slow.next = fast;
            fast.next = sNext;
            slow = sNext;
            fast = fNext;
        }
    }

}
