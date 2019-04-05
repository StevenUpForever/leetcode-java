package array;

import java.util.ArrayList;
import java.util.List;

public class Q119PascalsTriangleII {

    //Difficulty: easy
    //TAG: amazon
    //TAG: array

    /**
     * 119. Pascal's Triangle II
     *  Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * Note that the row index starts from 0.
     *
     *
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Example:
     *
     * Input: 3
     * Output: [1,3,3,1]
     * Follow up:
     *
     * Could you optimize your algorithm to use only O(k) extra space?
     */

    /*
    Solution:

    alloc one list, every time translate to next list, we do:
    pre = 0
    save a temp value sum = list[i] + pre
    then set pre = list[i]
    list.set(i, sum)
    we'll cover current ith elm, but need save it's value before overwrite

    Time: O(n^2)
    Space: O(n)
     */

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        while (rowIndex-- >= 0) {
            int pre = 0;
            for (int i = 0; i < list.size(); i++) {
                int sum = list.get(i) + pre;
                pre = list.get(i);
                list.set(i, sum);
            }
            list.add(1);
        }
        return list;
    }

}
