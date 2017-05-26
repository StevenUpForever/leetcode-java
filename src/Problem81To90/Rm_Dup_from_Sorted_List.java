package Problem81To90;

import PublicClass.ListNode;

public class Rm_Dup_from_Sorted_List {

    /**
     * 83. Remove Duplicates from Sorted List
     * Given a sorted linked list, delete all duplicates such that each element appear only once.

     For example,
     Given 1->1->2, return 1->2.
     Given 1->1->2->3->3, return 1->2->3.
     */

    /**
     * Solution:
     * Similar as 26 Remove_Dup_from_Sorted_Array from Problem21To30
     * Which has slow and fast pointer, fast go advanced by 1 every time, and only if slow != fast, make slow.next = fast, slow = slow.next
     * at last slow.next = null
     *
     * Time: O(n)
     * Space: O(1)
     */

    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        //In case if slow = null (due to head == null), so need to check if slow != null, disconnect slow with slow.next
        if (slow != null) slow.next = null;
        return head;
    }

}
