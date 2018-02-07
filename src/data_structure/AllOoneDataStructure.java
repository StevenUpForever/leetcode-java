package data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOoneDataStructure {

    //TAG: Uber
    //TAG: Data structure

    /**
     * 432. All O`one Data Structure
     * Implement a data structure supporting the following operations:

     Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
     Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
     GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
     GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
     Challenge: Perform all these in O(1) time complexity.
     */

    /**
     * Solution:
     * The key point is how to keep O(1) when get max/min when inc/dec is O(1) or keep O(1) when inc/dec when get max/min
     * is O(1)
     * when inc/dec, need to update max/min sequence also in O(1)
     * Similar to LFU cache, Use HashMap to get O(1) inc/dec
     * Use Doubly linkedList to maintain the sequence, which head is the max, tail is the min
     * each doubly list node contains a linkedlist/set to put all strings with same frequency
     */

    class AllOne {

        private doubleListNode head;
        private doubleListNode tail;
        private Map<String, doubleListNode> map;

        /** Initialize your data structure here. */
        public AllOne() {
            head = new doubleListNode(Integer.MAX_VALUE);
            tail = new doubleListNode(Integer.MIN_VALUE);
            head.next = tail;
            head.pre = null;
            tail.pre = head;
            tail.next = null;
            map = new HashMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            //If there's no node with key
            if (!map.containsKey(key)) {
                doubleListNode newNode = new doubleListNode(1);
                newNode.set.add(key);
                insertNewNode(newNode);
            } else {
                //There's existed node
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {

        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {

        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {

        }

        /*
        insert new node, it does following things:
        if the count 1 node is there,
         */
        private void insertNewNode(String key, int count) {

        }

    }

    class doubleListNode {
        int count;
        doubleListNode pre;
        doubleListNode next;
        Set<String> set;
        public doubleListNode(int count) {
            this.count = count;
            set = new HashSet<>();
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */

}
