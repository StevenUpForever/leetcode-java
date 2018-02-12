package recursion;

public class SudokuSolver {

    //TAG: Uber
    //TAG: Recursion
    //Difficulty: Hard

    /**
     * 37. Sudoku Solver
     * Write a program to solve a Sudoku puzzle by filling the empty cells.

     Empty cells are indicated by the character '.'.

     You may assume that there will be only one unique solution.


     A sudoku puzzle...

     ...and its solution numbers marked in red.
     */

    /**
     * Solution:
     * loop the matrix, when it's '.', loop from '0' to 9, check if fit for the rule, same as valid sudoku
     * recursion same step to next non - '.' until all recursion passed
     *
     * Time: O(mn^mn)
     * Space: O(mn)
     */

    public void solveSudoku(char[][] board) {
        sudokuRecursion(board);
    }

    private boolean sudokuRecursion(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') continue;
                for (char c = '1'; c <= '9'; c++) {
                    if (sudokuCheck(board, i, j, c)) {
                        board[i][j] = c;
                        if (sudokuRecursion(board)) return true;
                        else board[i][j] = '.';
                    }
                }
                return false;
            }
        }
        return true;
    }

    private boolean sudokuCheck(char[][] board, int row, int col, char c) {
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] != '.' && board[row][i] == c) return false;
            if (board[i][col] != '.' && board[i][col] == c) return false;
            if (board[row/3*3 + i/3][col/3*3 + i%3] != '.' && board[row/3*3 + i/3][col/3*3 + i%3] == c) return false;
        }
        return true;
    }


}
