package data_structure;

import java.util.*;

public class InsertDeleteGetRandomO1 {

    //TAG: Uber
    //TAG: Data structure
    //Difficulty: Medium

    /**
    380. Insert Delete GetRandom O(1)

    Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
     */

    /**
    Solutions:
    Due to O(1) insert and remove, cannot use Array, OR WE CAN ADD AND REMOVE ELM AT LAST INDEX, WHICH RANDOM WILL BE O(1)
    Due to O(1) getRandom, cannot use LinkedList, or at least cannot only use LinkedList

    Use HashSet to check if elm is contained or removed from list
    Use LinkedList to insert and remove elm, which time is O(1)
    Use HashMap connect with LinkedList which key is index, value is val in list, could get random in O(1)
     */

    class RandomizedSet {

        /** Initialize your data structure here. */

        private List<Integer> list;
        //Key is val, val is the index
        private HashMap<Integer, Integer> map;
        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) return false;
            //Use list.size() as the newest index in map
            map.put(val, list.size());
            list.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) return false;
            //swap the val if not the last one, so that list.remove will be O(1)
            int index = map.get(val);
            if (index < list.size() - 1) {
                Integer last = list.get(list.size() - 1);
                //Only need to swap last value to index of val, no need care about last index due to will remove it
                list.set(index, last);
                //Update map as well
                map.put(last, index);
            }
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int random = (int)(Math.random() * list.size());
            return list.get(random);
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */

 /**
 Follow up, what if the array could contains duplicate numbers

  Change HashMap to <Integer, LinkedList> when insert, always return true, and append index after list
  when remove, remove any or last index in the list
  */


}
