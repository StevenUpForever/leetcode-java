package data_structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllOoneDataStructure {

    //TAG: Uber
    //TAG: Data structure
    //Difficulty: Hard

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

        /*
        insert new node, it does following things:
        if the string is new:
            if the count 1 node is there, add key to node1 sets
            if the count 1 node is not there, alloc a new node in front of tail and add key to set
        if the string is in map, have count, increase count, remove it from the set:
            if increase count node is not there, alloc
            if there, add to increase count node's set
         */
        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            //key is new
            if (!map.containsKey(key)) {
                //if count 1 node is there
                if (tail.pre.count != 1) {
                    doubleListNode oneNode = new doubleListNode(1);
                    insertNodeInFront(oneNode, tail);
                }
                tail.pre.set.add(key);
                map.put(key, tail.pre);
            } else {
                doubleListNode cur = map.get(key);
                cur.set.remove(key);
                //First check if cur.pre.count = cur.count + 1, if not, alloc new node
                if (cur.pre.count != cur.count + 1) {
                    doubleListNode newNode = new doubleListNode(cur.count + 1);
                    insertNodeInFront(newNode, cur);
                }
                cur.pre.set.add(key);
                map.put(key, cur.pre);
                //Remove node if cur.set is empty, so that when it's the max/min node, should not return any string
                if (cur.set.isEmpty()) deleteNode(cur);
            }
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            //Only do when key existed
            if (map.containsKey(key)) {
                doubleListNode node = map.get(key);
                node.set.remove(key);
                if (node.count == 1) {
                    map.remove(key);
                } else {
                    if (node.next.count != node.count - 1) {
                        doubleListNode newNode = new doubleListNode(node.count - 1);
                        insertNodeBehind(newNode, node);
                    }
                    node.next.set.add(key);
                    map.put(key, node.next);
                }
                if (node.set.isEmpty()) deleteNode(node);
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            //Check if head.next is not tail, if so, due to node.set is not null, return any string
            return head.next.count != Integer.MIN_VALUE ? head.next.set.iterator().next() : "";
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            //Check if tail.pre is not tail, if so, due to node.set is not null, return any string
            return tail.pre.count != Integer.MAX_VALUE ? tail.pre.set.iterator().next() : "";
        }

        //Used when node.set is empty
        private void deleteNode(doubleListNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        //Insert newnode in front of target
        private void insertNodeInFront(doubleListNode newNode, doubleListNode target) {
            doubleListNode pre = target.pre;
            pre.next = newNode;
            newNode.pre = pre;
            newNode.next = target;
            target.pre = newNode;
        }

        private void insertNodeBehind(doubleListNode newNode, doubleListNode target) {
            doubleListNode next = target.next;
            target.next = newNode;
            newNode.pre = target;
            newNode.next = next;
            next.pre = newNode;
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
