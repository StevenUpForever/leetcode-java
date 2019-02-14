package linkedlist;

import public_class.ListNode;

public class Q19RemoveNthNodeFromEndOfList {

    //Difficulty: medium
    //TAG: Apple
    //TAG: LinkedList

    /**
     * 19. Remove Nth Node From End of List
     * Given a linked list, remove the n-th node from the end of list and return its head.
     *
     * Example:
     *
     * Given linked list: 1->2->3->4->5, and n = 2.
     *
     * After removing the second node from the end, the linked list becomes 1->2->3->5.
     * Note:
     *
     * Given n will always be valid.
     *
     * Follow up:
     *
     * Could you do this in one pass?
     */

    /*
    Solution:
    move fast and slow together, fast moves every time, slow moves only n smaller than 0
    n--
    finally we check n, if n >= 0 means, we haven't move slow and next step will not move slow too,
    Means we are choosing remove first node or the second
    because slow represents the node before the target node

    finally slow.next = slow.next.next;
    due to we already covered list only has one node situation (in n >- 0), and the slow will not be the last node
    (at least last second node)
    we don't care about slow.next.next

    Time: O(n)
    Space: O(1)
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return head;
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (n < 0) slow = slow.next;
            fast = fast.next;
            n--;
        }
        if (n >= 0) return head.next;
        else {
            slow.next = slow.next.next;
            return head;
        }
    }

    /*
    Solution 2:
    add a dummy node at front of the list, this could use a unique way to avoid when need remove first node
    Then move fast until n times, start move slow
    do same thing finally as solution 1
    return dummy.next

    Time: O(n)
    Space: O(1)
     */

}
