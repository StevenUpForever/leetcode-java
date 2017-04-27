package Problem11To20;

import PublicClass.ListNode;

public class Remove_Nth_Node_From_End_of_List {

    /**
     * 19. Remove Nth Node From End of List
     * Given a linked list, remove the nth node from the end of list and return its head.

     For example,

     Given linked list: 1->2->3->4->5, and n = 2.

     After removing the second node from the end, the linked list becomes 1->2->3->5.
     Note:
     Given n will always be valid.
     Try to do this in one pass.
     */

    /**
     * Solution:
     * Due to in one pass, use slow and fast pointer
     * 1. iterative the fast pointer n times, and set slow pointer at this time
     *      base case when fast point already met the null, means the list contains less than n nodes, just return
     * 2. iterative slow and fast pointer together, until fast pointer met the end node
     * 3. two conditions to skip the slow.next (last nth node)
     *      1. when slow.next == null, set slow.next = null
     *      2. when slow.next != null, slow.next = slow.next.next
     * 4. return the origin node
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode fast = head, slow = head;
        //fast point skip n nodes from start
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        /*
        If fast met the end, two conditions
        1. when n == 0, means it's just right want to skip the first node, return the second node (no matter null or not)
        2. if n > 0, means want to skip the node before the first (must be null) return null
         */
        if (fast == null) {
            if (n == 0) return head.next;
            return null;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        /*
        Slow node is the last second node of last nth node
        1. If the skip node is the last one, slow.next = null (consider about slow.next.next non pointer exception)
        2. If not, link slow to slow.next.next
         */
        slow.next = slow.next != null ? slow.next.next : null;
        return head;
    }

}
