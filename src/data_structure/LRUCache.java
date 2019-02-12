package data_structure;

import java.util.HashMap;

public class LRUCache {

    //TAG: Google
    //TAG: Facebook
    //TAG: Microsoft
    //TAG: Amazon
    //TAG: Uber
    //TAG: Snap
    //TAG: Apple
    //TAG: Data structure
    //Difficulty: Hard

    /**
    146. LRU Cache
     Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

     get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

     Follow up:
     Could you do both operations in O(1) time complexity?

     Example:

     LRUCache cache = new LRUCache( 2  capacity );
    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
    */

    /*
     * Solution:
     *
     * Use Doubly linkedList, used when delete LRU and insert
     * Use HashMap for get O(1)
     */

    private HashMap<Integer, DoublyListNode> map;
    private int capacity;
    private DoublyListNode head;
    private DoublyListNode tail;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
        head = new DoublyListNode(0, 0);
        tail = new DoublyListNode(0, 0);
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        DoublyListNode node = map.get(key);
        //Get key point: move this node to 1st in list, this is the most recent used one
        //First remove current node and link pre with next
        removeNode(node);
        //Second insert it at 1st place
        insertNodeAtFirst(node);
        return node.val;
    }

    public void put(int key, int value) {
        /*
        Put key point 1: put this node into 1st place
        1. if this node existed
            1. replace the value
            2. delete and move to first place
        2. if not existed, alloc a new node and move to first place
            decrease capacity
            if no capacity (-1) delete tail node
         */
        if (map.containsKey(key)) {
            DoublyListNode node = map.get(key);
            node.val = value;
            removeNode(node);
            insertNodeAtFirst(node);
        } else {
            DoublyListNode newNode = new DoublyListNode(key, value);
            map.put(key, newNode);
            insertNodeAtFirst(newNode);
            capacity--;
            //Put key point 2: check if over capacity, if so delete most least used node, which at the end of the list
            if (capacity < 0) {
                //Remove node key from map as well
                map.remove(tail.pre.key);
                removeNode(tail.pre);
                capacity++;
            }
        }
    }

    private void removeNode(DoublyListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void insertNodeAtFirst(DoublyListNode node) {
        DoublyListNode sec = head.next;
        head.next = node;
        node.pre = head;
        node.next = sec;
        sec.pre = node;
    }

    class DoublyListNode {
        public int key;
        public int val;
        public DoublyListNode next;
        public DoublyListNode pre;
        public DoublyListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

}
