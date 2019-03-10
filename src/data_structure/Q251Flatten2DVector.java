package data_structure;

import java.util.Iterator;
import java.util.List;

public class Q251Flatten2DVector {

    //Difficulty: Medium
    //TAG: Airbnb
    //TAG: Facebook
    //TAG: Apple
    //TAG: data structure

    /**
     * 251. Flatten 2D Vector
     * Implement an iterator to flatten a 2d vector.
     *
     * Example:
     *
     * Input: 2d vector =
     * [
     *   [1,2],
     *   [3],
     *   [4,5,6]
     * ]
     * Output: [1,2,3,4,5,6]
     * Explanation: By calling next repeatedly until hasNext returns false,
     *              the order of elements returned by next should be: [1,2,3,4,5,6].
     * Follow up:
     * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
     */

    /*
    Solution:
    1. do not repeatedly find next number in next() and has next(), should find once when call hasnext() many times
    2. should set a status "found", only next() will reset this state and do findNext operation
    3. initially should return (0, 0)
     */

    class Vector2D implements Iterator<Integer> {

        private List<List<Integer>> list;
        private int row, col;
        //indicate the next number is found, until next() called, save findNext() times of call
        private boolean found;

        public Vector2D(List<List<Integer>> vec2d) {
            this.list = vec2d;
            //Initially -1 due to (0, 0) is the first next()
            this.row = -1;
            this.col = -1;
            this.found = false;
        }

        @Override
        public Integer next() {
            if (list == null) return null;
            //only call findNext() when not called from current position
            if (found) {
                found = false;
            } else {
                findNextNum();
            }
            return row < list.size() ? list.get(row).get(col) : null;
        }

        @Override
        public boolean hasNext() {
            if (found) {
                return row < list.size() && col < list.get(row).size();
            } else {
                found = true;
                return findNextNum();
            }
        }

        private boolean findNextNum() {
            if (row >= list.size()) return false;
            //*** In case list.get(0).len == 0 and when col == -1, (0, 0) is unavailable, need skip this situation ***
            if (col != - 1 && col < list.get(row).size() - 1) {
                col++;
                return true;
            } else {
                row++;
                while (row < list.size() && list.get(row).size() == 0) {
                    row++;
                }
                col = 0;
                return row < list.size();
            }
        }
    }

    public class Vector2D2 {

        private Iterator<List<Integer>> i;
        private Iterator<Integer> j;

        public Vector2D2(List<List<Integer>> vec2d) {
            i = vec2d.iterator();
        }

        public int next() {
            hasNext();
            return j.next();
        }

        public boolean hasNext() {
            while ((j == null || !j.hasNext()) && i.hasNext())
                j = i.next().iterator();
            return j != null && j.hasNext();
        }
    }

}