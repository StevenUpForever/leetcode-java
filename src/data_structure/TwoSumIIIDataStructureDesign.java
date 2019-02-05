package data_structure;

import java.util.*;

public class TwoSumIIIDataStructureDesign {

    //TAG: LinkedIn
    //TAG: Data structure
    //Difficulty: Easy

    /**
     * 170. Two Sum III - Data structure design
     * Design and implement a Q1TwoSum class. It should support the following operations: add and find.

     add - Add the number to an internal data structure.
     find - Find if there exists any pair of numbers which sum is equal to the value.

     For example,
     add(1); add(3); add(5);
     find(4) -> true
     find(7) -> false
     */

    /**
     * Solution 1: slow in add(), fast in find()
     * The key point is find(), most efficient way is using HashMap/Set structure to search
     * So we try to add as many sums as possible in the set, when adding one number from num array
     * So the find will just check if the sum is in the sum set
     */

    public class TwoSumS1 {

        List<Integer> num;
        HashSet<Integer> sum;

        /** Initialize your data structure here. */
        public TwoSumS1() {
            num = new ArrayList<>();
            sum = new HashSet<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            for (int cur: num) sum.add(cur + number);
            num.add(number);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            return sum.contains(value);
        }
    }

    /**
     * Solution 2: fast in add(), slow in find()
     * Store every added number into a hashMap, value have two types, if only one, value == 1, if more than 1, value == 2
     * Find(): check if sum - value is in hashMap, there are two conditions:
     *      1. if sum - value == value, means the searched value may be the value itself, so, if value == 2, means there are more than one sum - value, return true, otherwise false
     *      2. if sum - value != value, just verify if in hashMap, if so, return true, otherwise false
     */

    public class TwoSumS2 {
        HashMap<Integer, Integer> map;

        /** Initialize your data structure here. */
        public TwoSumS2() {
            map = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            if (map.containsKey(number)) {
                map.put(number, 2);
            } else map.put(number, 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for (int key: map.keySet()) {
                int num2 = value - key;
                if (map.containsKey(num2)) {
                    //Don't return a must value but only the possible true at here,
                    // due to may have other answers after this loop
                    if (num2 == key || map.get(num2) == 2) return true;
                }
            }
            return false;
        }
    }

}
