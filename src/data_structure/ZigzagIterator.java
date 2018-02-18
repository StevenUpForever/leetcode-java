package data_structure;

import java.util.List;

public class ZigzagIterator {

    //TAG: Google
    //TAG: data structure
    //Difficulty: Medium

    /**
     * 281. Zigzag Iterator
     * Given two 1d vectors, implement an iterator to return their elements alternately.

     For example, given two 1d vectors:

     v1 = [1, 2]
     v2 = [3, 4, 5, 6]
     By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

     Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

     Clarification for the follow up question - Update (2015-09-18):
     The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

     [1,2,3]
     [4,5,6,7]
     [8,9]
     It should return [1,4,8,2,5,9,3,6,7].
     */

    /**
     * Solution:
     * It's not zigzag, but a rotated BST, use two indices, move index1 first then index2, if any met the end, continue
     * move the other to the end
     */

    private List<Integer> v1, v2;
    private int index1, index2;

    private int shift;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        index1 = 0;
        index2 = 0;
        shift = 0;
    }

    public int next() {
        Integer res = 0;
        if (shift == 0 && index1 < v1.size() || index2 >= v2.size()) res = v1.get(index1++);
        else if (index2 < v2.size() || index1 >= v1.size()) res = v2.get(index2++);
        shift = 1 - shift;
        return res;
    }

    public boolean hasNext() {
        return index1 < v1.size() || index2 < v2.size();
    }

    /**
     * Follow up:
     * assign index array for all lists, one shift from 0 to lists.length, if any array ends and shift at
     * current array, move to next shift until the array is not end
     */

/**
 * Your data_structure.ZigzagIterator object will be instantiated and called as such:
 * data_structure.ZigzagIterator i = new data_structure.ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

}
