package linkedlist;

import public_class.ListNode;

public class Q83RemoveDuplicatesFromSortedList {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: LinkedList

    /**
     * 83. Remove Duplicates from Sorted List
     * Given a sorted linked list, delete all duplicates such that each element appear only once.
     *
     * Example 1:
     *
     * Input: 1->1->2
     * Output: 1->2
     * Example 2:
     *
     * Input: 1->1->2->3->3
     * Output: 1->2->3
     */

    /*
    Solution:
    Similar to Q283 move 0s, be aware of when last need make slow.next = null, need check if slow is null
    due to ListNode may initially is null

    Time: O(n)
    Space: O(1)
     */

    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head, res = head;
        while (head != null) {
            if (slow.val != head.val) {
                slow.next = head;
                slow = slow.next;
            }
            head = head.next;
        }
        if (slow != null) slow.next = null;
        return res;
    }

}
