package data_structure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Q460LFUCache {

    //Difficulty: Hard
    //TAG: Snap
    //TAG: data structure

    /**
     * 460. LFU Cache
     * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
     *
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
     *
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     *
     * Example:
     *
     * data_structure.Q460LFUCache cache = new data_structure.Q460LFUCache( 2  capacity  );
     *
             *cache.put(1,1);
     *cache.put(2,2);
     *cache.get(1);       // returns 1
     *cache.put(3,3);    // evicts key 2
     *cache.get(2);       // returns -1 (not found)
     *cache.get(3);       // returns 3.
     *cache.put(4,4);    // evicts key 1.
     *cache.get(1);       // returns -1 (not found)
     *cache.get(3);       // returns 3
     *cache.get(4);       // returns 4
     */

    /*
    Solution:
    Data structure:
    key1    key2    key3    key4
     |       |       |       |
     |       |       |       |
    Freq1   Freq2   Freq3   Freq4
    Val1    Val2    Val3    Val4

    sorted list
    [freq1, freq2, freq3, freq4]
       |      |      |      |
       |      |      |      |
      key1   key6   key4   key7
      key5   key2   key3   key8

      when get(int key)
      1. put key in last place of freqKeyList
      return keyValueMap.get(key)

      **********************
      Key point is in put()
      1. update keyValueMap anyway, like put(1, 2) put(1, 3) should keep only (1, 3)
      2. check if keyFreqMap has key, if not, new key coming, but anyway go step 3, and capacity--,
         if capacity < 0, go step 2.1
        2.1 find least freq in freqList[0]
        2.2 depends on freqList[0] goto freqKeyListMap find leastFreq key List, and delete first key
            (means least recent used least freq key)
        2.3 if freList[0] is empty, try to delete related key, freq in freqList, freqKeyListMap, keyFreqMap
      3. update/insert this key
        3.1 update keyFreqMap by value++
        3.2 update freqKeyListMap by (create new key list if new freq) add key at first (mean most recent) at key list
        3.3 try insert this freq in freqList if this is a new freq
     */


    private int capacity;
    //The key frequency map, that can fast search freq by key in O(1)
    private Map<Integer, Integer> keyFreqMap;
    //Key value map that can fast get(int key) in O(1)
    private Map<Integer, Integer> keyValueMap;
    //Frequency List<Key> map that can find most recent used key in same frequency in O(1)
    //The key list is most recent at last (index len - 1)
    private Map<Integer, List<Integer>> freqKeyListMap;
    //Frequency list that can find least frequency in O(1)
    //The freq list is least freq at first (index 0) means ascending order
    private List<Integer> freqList;

    public Q460LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyFreqMap = new HashMap<>();
        this.keyValueMap = new HashMap<>();
        this.freqKeyListMap = new HashMap<>();
        //LinkedList could insert and delete frequency by O(1)
        this.freqList = new LinkedList<>();
    }

    public int get(int key) {
        if (keyValueMap.containsKey(key)) {
            updateKey(key);
            return keyValueMap.get(key);
        } else return -1;
    }

    public void put(int key, int value) {
        keyValueMap.put(key, value);
        if (keyValueMap.size() > capacity) {
            deleteKey();
        }
        updateKey(key);
    }

    private void updateKey(int key) {
        int oldFreq = keyFreqMap.getOrDefault(key, 0);
        keyFreqMap.put(key, oldFreq + 1);
        int newFreq = oldFreq + 1;
        if (!freqKeyListMap.containsKey(newFreq)) {
            freqKeyListMap.put(newFreq, new LinkedList<>());
        }
        freqKeyListMap.get(newFreq).add(key);
        insertFreqIntoList(newFreq);
        if (freqKeyListMap.containsKey(oldFreq)) {
            freqKeyListMap.get(oldFreq).remove(key);
            if (freqKeyListMap.get(oldFreq).size() == 0) {
                freqKeyListMap.remove(oldFreq);
                for (int i = 0; i < freqList.size(); i++) {
                    if (freqList.get(i) == oldFreq) {
                        freqList.remove(i);
                        break;
                    }
                }
            }
        }
    }

    private void insertFreqIntoList(int freq) {
        int i = 0;
        while (i < freqList.size() && freqList.get(i) < freq) {
            i++;
        }
        if (i == 0 || i == freqList.size()) freqList.add(freq);
        else if (freqList.get(i) != freq) freqList.add(i, freq);
    }

    private void deleteKey() {
        if (freqList.size() == 0) return;
        int leastFreq = freqList.get(0);
        List<Integer> leastFreqKeys = freqKeyListMap.get(leastFreq);
        if (leastFreqKeys == null || leastFreqKeys.size() == 0) {
            //Means there is no key has this freq, remove all related
            freqKeyListMap.remove(leastFreq);
            freqList.remove(0);
        } else {
            int key = leastFreqKeys.remove(0);
            keyFreqMap.remove(key);
            keyValueMap.remove(key);
            if (leastFreqKeys.size() == 0) {
                freqKeyListMap.remove(leastFreq);
                freqList.remove(0);
            }
        }
    }

}
