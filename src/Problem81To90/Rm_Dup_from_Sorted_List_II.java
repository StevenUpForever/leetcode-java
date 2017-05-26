package Problem81To90;

import PublicClass.ListNode;

public class Rm_Dup_from_Sorted_List_II {

    /**
     * 82. Remove Duplicates from Sorted List II
     * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

     For example,
     Given 1->2->3->3->4->4->5, return 1->2->5.
     Given 1->1->1->2->3, return 2->3.
     */

    /**
     * Solution:
     * 1. In a linkedList, if we want to skip the dups, we need to keep track of the previous node before the dups, so we need to have a pre node before dups, and a cur node to go the loop
     * 2. Due we may need to skip first several nodes (dups from 1st node), as the same process in step 1, it's better to have a dummy node before head node, so
     *      We need a dummy node insert before head, and return dummy.next as result
     *      Need a pre node start from dummy, a cur node start from head
     * A. for the while loop, first cur node skip all dups, at the end, cur is the last node of dups
     * B. check if pre is still connected with cur, if so, cur has no dups, pre = cur, cur = cur.next
     *      else cur has dups, pre.next = cur.next, cur = cur.next
     * C. return dummy.next
     *
     * Time: O(n)
     * Space: O(1)
     */

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head, pre = dummy;
        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) cur = cur.next;
            //At this time cur is the last node of dups or the index of that no dup node
            if (pre.next != cur) pre.next = cur.next;
            else pre = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

}
