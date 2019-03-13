package linkedlist.cycle;

import java.util.HashSet;
import java.util.Set;

public class Q708InsertIntoACyclicSortedList {

    //Difficulty: medium
    //TAG: Google
    //TAG: linkedList

    /**
     * 708. Insert into a Cyclic Sorted List
     * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
     *
     * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.
     *
     * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.
     *
     * The following example may help you understand the problem better:
     *
     *
     *
     *
     *
     * In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.
     *
     *
     *
     *
     *
     * The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.
     */

    /*
    Solution:

    Several cases:
    1. null list then insert->insert and return insert
    2. normal list like ->2-3-4-5-0... and value between 2-5 do normal loop and find place to insert
    3. normal list like ->2-3-4-5-0... and value < 2, then we need find first node first, then loop find insert place
    4. normal list like ->2-3-4-5-0... and value > 5, do same as rule 2, loop to the end and insert
    5. normal list like ->2-3-4-5-1... and value == 0, do same as rule 2, loop to the end and insert at the start
    6. repeat value list like ->2-2-3-3-4-5-0... with value between 2-5 and < 2 follow case 2 and 3, and loop include
    equal value, like node.value <= node.next.value node = node.next
    7. all repeat values like 2-2-2-2-2... with any values, need use a hashSet filter visited nodes, find if this is
    a same value cycle, follow rule 3, then insert at the front

    Time: O(n)
    Space: O(n)
     */

    public Node insert(Node head, int insertVal) {
        Node insert = new Node(insertVal, null);
        //Do null case first
        if (head == null) {
            insert.next = insert;
            return insert;
        }
        Node res = head;
        //find smallest node, but be caution that all values are small (use set filter)
        Set<Node> set = new HashSet<>();
        while (head.val <= head.next.val && set.add(head)) head = head.next;
        //find insert place, if the insertValue is larger than largest value, we should not loop
        if (head.val > insertVal) {
            while (head.next.val < insertVal) head = head.next;
        }
        Node next = head.next;
        head.next = insert;
        insert.next = next;
        return res;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    };

}
