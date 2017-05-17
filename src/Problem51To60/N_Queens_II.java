package Problem51To60;

import java.util.*;

public class N_Queens_II {

    /**
     * 52. N-Queens II
     * Follow up for N-Queens problem.

     Now, instead outputting board configurations, return the total number of distinct solutions.
     */

    /**
     * Solution 1: DFS
     * DFS same as N_Queens, no need for a List<List<Integer>>
     * have a global res to record the new result
     *
     * Time: O(n!)
     * Space: O(n) arrayList n + levels of recursion stacks n O(2n) = O(n)
     */

    int res = 0;

    public int totalNQueensS1(int n) {
        totalNQueensHelper(new ArrayList<>(), n);
        return res;
    }

    private void totalNQueensHelper(List<Integer> list, int n) {
        if (list.size() == n) {
            res++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (passTheCheck(list, i)) {
                list.add(i);
                totalNQueensHelper(list, n);
                //Reset to pre status
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean passTheCheck(List<Integer> list, int col) {
        for (int i = 0; i < list.size(); i++) {
            //If previous rows have 'Q' on the same col or the same diagonal, return false
            if (list.get(i) == col || Math.abs(list.get(i) - col) == list.size() - i) return false;
        }
        return true;
    }

    /**
     * Solution 2: boolean[] record recursion
     * Use three boolean[] arrays record column, left diagonal and right diagonal respectively
     * when go each element in three arrays, record the col, lDiag, rDiag value for current element, of all not existed, value number and recursion to next level
     * *** This will save the time in passTheCheck, will no need to go over all integers in list ***
     *
     * Time: O(n!)
     * Space: O(n) actually O(4n) n for recursion stacks, 3n for 3 arrays
     */

    public int totalNQueensS2(int n) {
        boolean[] cols = new boolean[n], lDiag = new boolean[2*n], rDiag = new boolean[2*n];
        totalNQueensHelper2(cols, lDiag, rDiag, 0, n);
        return res;
    }

    private void totalNQueensHelper2(boolean[] cols, boolean[] lDiag, boolean[] rDiag, int num, int n) {
        if (num == n) {
            res++;
            return;
        }
        for (int i = 0; i < n; i++) {
            //If any value in three arrays is true, here cannot put a queen
            //num - i + n, num - i may overflow, so all add n to make sure between 0...2n
            if (cols[i] || lDiag[num - i + n] || rDiag[num + i]) continue;
            cols[i] = lDiag[num - i + n] = rDiag[num + i] = true;
            totalNQueensHelper2(cols, lDiag, rDiag, num + 1, n);
            cols[i] = lDiag[num - i + n] = rDiag[num + i] = false;
        }
    }


}
