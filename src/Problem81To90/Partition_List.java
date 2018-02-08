package Problem81To90;

import public_class.ListNode;

public class Partition_List {

    /**
     * 86. Partition List
     * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

     You should preserve the original relative order of the nodes in each of the two partitions.

     For example,
     Given 1->4->3->2->5->2 and x = 3,
     return 1->2->2->4->3->5.
     */

    /**
     * Solution 1: Copy space
     * Two Dummy node, append smaller nodes to one, and else to another one
     * Connect smaller dummyNode.next to larger dummyNode (copy of larger dummyNode)
     * return copy of smaller smaller dummyNode
     *
     * Time: O(n)
     * Space: O(n)
     */

    public ListNode partitionS1(ListNode head, int x) {
        ListNode dummyOne = new ListNode(0), dummyTwo = new ListNode(0);
        ListNode copyOne = dummyOne, copyTwo = dummyTwo;
        while (head != null) {
            if (head.val < x) {
                dummyOne.next = head;
                dummyOne = dummyOne.next;
            } else {
                dummyTwo.next = head;
                dummyTwo = dummyTwo.next;
            }
            head = head.next;
        }
        //Last larger node may not be the last node of the list, so need to break the connection of last larger node to its next
        dummyTwo.next = null;
        dummyOne.next = copyTwo.next;
        return copyOne.next;
    }

    /**
     * Solution 2: One dummy node
     * Have two pointers, slow and fast, slow is the pre node of smaller part, and fast keep loop the list to find smaller nodes
     * All smaller nodes exchange to append to slow, and at last it's a correct list
     *
     * Time: O(n)
     * Space: O(1)
     */

    public ListNode partitionS2(ListNode head, int x) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, headPre = dummy;
        while (head != null) {
            //If head.val < x && slow.next = head, at last slow.next == head, but headPre will not be the pre of head but pre second of head node
            if (head.val < x && !slow.next.equals(head)) {
                //Append head.pre to head.next
                ListNode headNext = head.next;
                headPre.next = headNext;
                //Insert head into slow and slow.next
                ListNode slowNext = slow.next;
                slow.next = head;
                head.next = slowNext;
                slow = slow.next;
                head = headNext;
            } else {
                //If slow.next should be skipped, then move slow to next in case the smaller nodes' order will be changed
                if (slow.next != null && slow.next.val < x) slow = slow.next;
                headPre = head;
                head = head.next;
            }
        }
        return dummy.next;
    }

}
