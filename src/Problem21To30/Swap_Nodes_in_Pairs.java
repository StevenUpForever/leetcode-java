package Problem21To30;

import PublicClass.ListNode;

public class Swap_Nodes_in_Pairs {

    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.

     For example,
     Given 1->2->3->4, you should return the list as 2->1->4->3.

     Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     */

    /**
     * Solution 1: Iterative
     * exchange each pair of node, and goto next.next step
     *      1. keep control of the pre node and next node of current pair of nodes
     *      2. make sure next is not null and next.next is not null, and connect with next.next node
     *          1. if next is null, even number of pairs, stop
     *          2. if next is not null but next.next is null, odd number of pairs, just connect with the last node and stop
     */

    public ListNode swapPairsS1(ListNode head) {
        ListNode dummy = new ListNode(0), res = dummy;
        dummy.next = head;
        /*
        Make sure next and next.next are all not null, because the steps in loop will do exchange about these two nodes
        a special case is when there's odd number nodes left in next step, next.next = nextSec.next; this step will handle no matter null node or valid node
         */
        while (dummy.next != null && dummy.next.next != null) {
            ListNode next = dummy.next;
            ListNode nextSec = dummy.next.next;
            next.next = nextSec.next;
            dummy.next = nextSec;
            nextSec.next = next;
            dummy = dummy.next.next;
        }
        return res.next;
    }

    /**
     * Solution 2: recursion
     * Base case: next and next.next is not null, exchange this pair of nodes
     * recursion rule: Do not consider how the detail steps in next recursion step, just connect this pair of node to next recursion step
     * 1 -> 2 -> 3 -> 4 -> 5 => 2 -> 1 -> | next recursion part |
     */

    public ListNode swapPairsS2(ListNode head) {
        /*
        Make sure head and head.next are both null, will avoid either next.next error or no more step to exchange next pair of nodes, includes the end case of recursion
         */
        if (head == null || head.next == null) return head;
        //next node is the second node of each recursion step
        ListNode next = head.next;
        //Connect current pair of nodes to next recursion step
        head.next = swapPairsS2(head.next.next);
        next.next = head;
        //This next finally, after call stack steps of recursion, is the first second node of the list
        return next;
    }

}
