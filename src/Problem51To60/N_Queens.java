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
     * Solution: dfs
     * Base case: when filled all n rows, return
     * recursion rule: for each row
     *      1. for loop every element in this row
     *      2. if met the check condition: there's no element on diagonals and the same column, recursive to next row
     *
     * Time: O(n! + n^2(for pass check method)) = O(n!) for each row, will at least have n - 1 options, (for column)
     * Space: O(n)
     */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        solveQueensHelper(res, new ArrayList<>(), n);
        return res;
    }

    //Use a integer ArrayList to record the index of 'Q', instead of String list, faster to search for 'Q'
    private void solveQueensHelper(List<List<String>> res, List<Integer> list, int n) {
        if (list.size() == n) {
            //Convert integer list to string list
            List<String> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (j == list.get(i)) builder.append('Q');
                    else builder.append('.');
                }
                newList.add(builder.toString());
            }
            res.add(newList);
            return;
        }
        //For loop current row, and if pass check add current index i to list and recursion to next step
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
            //If previous rows have 'Q' on the same col or the same diagonal, return false
            if (list.get(i) == col || Math.abs(list.get(i) - col) == list.size() - i) return false;
        }
        return true;
    }

}
