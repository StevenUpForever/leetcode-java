package legacy_code.Problem31To40;

public class Sudoku_Solver {

    /**
     * 37. Sudoku Solver
     * Write a program to solve a Sudoku puzzle by filling the empty cells.

     Empty cells are indicated by the character '.'.

     You may assume that there will be only one unique solution.
     */

    /**
     * Solution: recursion
     * 1. for for loop the matrix, find every element equals to '.'
     *      1. for each element which equals to '.', try to fill with 1 - 9, when pass the sudoku check
     *      2. recursion current method, when all passes, this will be the result
     *      3. otherwise refill it with '.' for next possible fill
     *
     * Time: O(9^n) n represent the number of '.'
     * Space: O(n) when max recursion stack is recursion to fill all '.'
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
                        //recursion self to try to verify the sudoku after current fill to see if not fit for other row/column check
                        if (sudokuRecursion(board)) return true;
                        //If not, refill to '.' for next possible fill in 1 ~ 9
                        else board[i][j] = '.';
                    }
                }
                //If could come to here, means haven't recursion to a true, so means 1-9 all cannot fit for this '.' return false
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
