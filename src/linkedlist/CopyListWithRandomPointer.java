package linkedlist;

import public_class.RandomListNode;

import java.util.HashMap;

public class CopyListWithRandomPointer {

    //TAG: Uber
    //TAG: linkedlist

    /**
     * 138. Copy List with Random Pointer
     * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

     Return a deep copy of the list.
     */

    /**
     * Solution:
     * Key point is when copy random node, do not deep copy a new node
     * Use HashMap to filter copied node
     *
     * Time: O(n)
     * Space: O(n)
     */

    public RandomListNode copyRandomList(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode temp = head;
        while (temp != null) {
            map.put(temp, new RandomListNode(temp.label);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            map.get(temp).next = map.get(temp.next);
            map.get(temp).random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }

}
