package linkedlist;

import public_class.ListNode;

public class MergeTwoSortedLists {

    //TAG: LinkedIn
    //TAG: linked list
    //Difficulty: Easy

    /**
     * 21. Merge Two Sorted Lists
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
     */

    /**
     * Solution:
     * Create a dummy node, merge two list to dummy node list and return dummy node
     *
     * Time: O(m + n)
     * Space: O(1)
     */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = l1;
                l1 = l1.next;
            } else {
                dummy.next = l2;
                l2 = l2.next;
            }
            dummy = dummy.next;
        }
        //Remember the post steps when any list iteration and end and the other one is not, append the list of the other list to res list immediately
        if (l1 != null) dummy.next = l1;
        if (l2 != null) dummy.next = l2;
        return res.next;
    }

}
