package Problem51To60;

import java.util.*;

public class N_Queens {

    /**
     * 51. N-Queens
     * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



     Given an integer n, return all distinct solutions to the n-queens puzzle.

     Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

     For example,
     There exist two distinct solutions to the 4-queens puzzle:

     [
     [".Q..",  // Solution 1
     "...Q",
     "Q...",
     "..Q."],

     ["..Q.",  // Solution 2
     "Q...",
     "...Q",
     ".Q.."]
     ]
     */

    /**
     * Solution: DFS
     * Base case: when filled all n rows, return
     * Recursion rule: for each row
     *      1. for loop every element in this row
     *      2. if met the check condition: there's no element on diagonals and the same column, recursive to next row
     *
     * Time: O(n!) for each row, will at least have n - 1 options, (for column)
     * Space: O(n)
     */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        solveQueensHelper(res, new ArrayList<>(), n);
        return res;
    }

    private void solveQueensHelper(List<List<String>> res, List<Integer> list, int n) {
        if (list.size() == n) {
            List<String> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == i) builder.append('Q');
                    else builder.append('.');
                }
                newList.add(builder.toString());
            }
            res.add(newList);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (passTheCheck(list, i)) {
                list.add(i);
                solveQueensHelper(res, list, n);
                list.remove(list.size() - 1);
            }
        }
    }

    private boolean passTheCheck(List<Integer> list, int col) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == col || Math.abs(list.get(i) - i) == list.size() - i) return false;
        }
        return true;
    }

}
