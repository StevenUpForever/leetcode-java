package linkedlist;

import public_class.ListNode;

public class Q92ReverseLinkedListII {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: linked list

    /**
     * 92. Reverse Linked List II
     * Reverse a linked list from position m to n. Do it in one-pass.
     *
     * Note: 1 ≤ m ≤ n ≤ length of list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     */

    /*
    Solution:

    skip to m node, then reverse until n node

    Time: O(n)
    Space: O(1)
     */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
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
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = cur.next;
            n--;
        }
        return dummy.next;
    }

}
