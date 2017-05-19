package Problem61To70;

import PublicClass.ListNode;

public class Rotate_List {

    /**
     * 61. Rotate List
     * Given a list, rotate the list to the right by k places, where k is non-negative.

     For example:
     Given 1->2->3->4->5->NULL and k = 2,
     return 4->5->1->2->3->NULL.
     */

    /**
     * Solution: In place
     * 1. need to know if k is larger than list size, so that the rotate nodes is right k % size nodes
     * 1. Have two pointers, slow keep at the start, right go loop the list until k times
     * 2. Move slow and fast together until fast.next == null, slow will the last k + 1 th node, also the pre of the first node of result list
     * 3. keep record of the slow.next, break the connection with slow and slow.next, append header after fast (which is the end node right now)
     * 4. return slow.next
     *
     * Time: O(n)(find the length) + O(n)(node iterative steps) = O(n)
     * Space; O(1)
     */

    public ListNode rotateRight(ListNode head, int k) {
        //Base case and avoid error of k = k % len if len == 0
        if (head == null || head.next == null || k == 0) return head;
        ListNode slow = head, fast = head;
        //Calculate the length of the list
        int len = 0;
        while (fast != null) {
            len++;
            fast = fast.next;
        }
        fast = head;
        //Make the k real rotate number if k larger than list size
        k = k % len;
        while (fast != null && k-- > 0) fast = fast.next;
        if (fast == null) return head;
        //Move slow and fast together, with fast advanced k nodes from slow
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        //Append header to fast (currently the end node)
        fast.next = head;
        //Slow is the previous node of the last kth node, so record the slow.next as result, break the connection slow and slow.next and result result node
        ListNode next = slow.next;
        slow.next = null;
        return next;
    }

}
