package array.k_merge;

import public_class.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Q23MergeKSortedLists {

    //TAG: Google
    //TAG: Uber
    //TAG: LinkedIn
    //TAG: Apple
    //TAG: LinkedList
    //TAG: array
    //TAG: k merge
    //Difficulty: Hard

    /**
     * 23. Merge k Sorted Lists
     * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
     */

    /*
     * Solution 1: Binary reduction
     * treat these lists as an array, and do merge sort about this array, merge each two lists together
     * When not consider the I/O speed, this should be the one of the fastest way
     * When consider about I/O, each time when merge two lists, need to read these two lists from memory, the actual time is more slower than a merge sort
     * Theoretically time: n represent the length of lists array, m represent the average length of lists, O(nlogn * 2m) = O(mnlogn)
     * If each step, merge two list to one list, it's the same as iteration, the time is O(2mn) = O(mn) ***not consider I/O performance***
     * Space: O(logn)
     */

    /*
     * Solution 2: iteration
     * merge list from the first to the end
     * Same I/O performance issue as the binary reduction
     * Time: n represent the length of lists array, m represent the average length of list, O(mn) ***not consider I/O performance***
     * Space: O(1)
     */

    public ListNode mergeKListsS2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode node = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode temp = node, cur = lists[i], dummy = new ListNode(0), res = dummy;
            while (cur != null && temp != null) {
                if (temp.val < cur.val) {
                    dummy.next = temp;
                    temp = temp.next;
                } else {
                    dummy.next = cur;
                    cur = cur.next;
                }
                dummy = dummy.next;
            }
            if (temp != null) dummy.next = temp;
            if (cur != null) dummy.next = cur;
            node = res.next;
        }
        return node;
    }

    /*
     * Solution 3: Priority queue
     * push all list node into queue (merge to a dummy node at the same time)
     * Time: O(mnlogn) logn is the sort in a heap for n nodes at one time in the heap (each time need to poll a node and push a new one into it, max n nodes)
     */

    public ListNode mergeKListsS3(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        for (ListNode node: lists) {
            //Be aware of not add null node into queue
            if (node != null) queue.add(node);
        }
        ListNode dummy = new ListNode(0);
        ListNode res = dummy;
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            dummy.next = cur;
            dummy = dummy.next;
            if (cur.next != null) queue.offer(cur.next);
        }
        return res.next;
    }

}
