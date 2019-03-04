package array.sliding_window;

import java.util.ArrayList;
import java.util.List;

public class Q1004MaxConsecutiveOnesIII {

    //Difficulty: medium
    //TAG: array
    //TAG: sliding window

    /**
     * 1004. Max Consecutive Ones III
     * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
     *
     * Return the length of the longest (contiguous) subarray that contains only 1s.
     *
     *
     *
     * Example 1:
     *
     * Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * Output: 6
     * Explanation:
     * [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
     * Example 2:
     *
     * Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * Output: 10
     * Explanation:
     * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
     *
     *
     * Note:
     *
     * 1 <= A.length <= 20000
     * 0 <= K <= A.length
     * A[i] is 0 or 1
     */

    /*
    Solution 1:
    Record all longest 1s with:
    length of 0s before first 1
    length of 1s stopped at current index

    loop these [0s, 1s] object, filled with K, try to get max length

    Time: O(n^2)
    Space: O(n)
     */

    public int longestOnes(int[] A, int K) {
        if (A == null || A.length == 0) return 0;
        int countOf0 = 0, countOf1 = 0;
        List<int[]> lists = new ArrayList<>();
        //Add [count of 0 before first 1, count of max 1s] to a list
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (countOf1 > 0) {
                    lists.add(new int[]{countOf0, countOf1});
                    countOf0 = 1; countOf1 = 0;
                } else countOf0++;
            } else if (A[i] == 1) {
                countOf1++;
            }
        }
        //Append last pair
        lists.add(new int[]{countOf0, countOf1});
        int max = 0;
        for (int i = 0; i < lists.size(); i++) {
            int temp = K, j = i, cur = 0;
            while (temp >= 0 && j >= 0) {
                int[] obj = lists.get(j);
                int numOf0 = obj[0], numOf1 = obj[1];
                cur += numOf1;
                //if current temp >= numOf0 means current pair could connect to previous pair, loop next
                if (temp >= numOf0) {
                    cur += numOf0;
                    temp -= numOf0;
                } else {
                    //Otherwise stop here
                    cur += temp;
                    break;
                }
                j--;
            }
            max = Math.max(max, cur);
        }
        return max;
    }

    /*
    Solution 2: sliding window

    Time: O(n)
    Space: O(1)
     */

    public int longestOnes2(int[] A, int K) {
        int res = 0, index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) K--;
            if (K < 0 && A[index++] == 0) K++;
            res = Math.max(res, i - index + 1);
        }
        return res;
    }

}
