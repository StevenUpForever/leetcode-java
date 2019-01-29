package matrix;

public class ValidSudoku {

    //Difficulty: Medium
    //TAG: Uber
    //TAG: array
    //TAG: matrix

    /**
     * 36. Valid Sudoku
     * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

     The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

     A partially filled sudoku which is valid.

     Note:
     A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
     */

    /*
     * Solution 1: Brute force
     * for for loop all elements in board matrix
     *      for each element, run horizontal, vertical and current 9 * 9 to verify if duplicate, if so, return false
     * If could go through all elements in matrix, return true
     *
     * Time: O(n^2) * (O(n) horizontal + O(n) vertical + O(9) 9 * 9 place) = O(n^3)
     * Space: O(1)
     */

    public boolean isValidSudokuS1(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char cur = board[i][j];
                if (cur == '.') continue;
                for (int m = 0; m < board[0].length; m++) {
                    if (board[i][m] == cur && m != j) return false;
                }
                for (int m = 0; m < board.length; m++) {
                    if (board[m][j] == cur && m != i) return false;
                }
                //int /3 * 3 is the start index of the small box which contains current number
                for (int row = i /3 * 3; row < i /3 * 3 + 3; row++) {
                    for (int col = j /3 * 3; col < j /3 * 3 + 3; col++) {
                        if (board[row][col] == cur && row != i && col != j) return false;
                    }
                }
            }
        }
        return true;
    }

    /*
     * Solution 2: less time, more space
     * (Get idea from legacy_code)
     * Use three matrix to represent the horizontal, vertical and small box arrays which num 1 should be index0 ... num 9 should be index 8
     * Due to Sudoku feature
     *      1. every row, column and small box are 9 spaces
     *      2. every integer in each row, column or small box is unique
     * So not consider of current element x and y as coordinator in that 3 verify matrix, make it's value as the x or y in verify matrix, 9 space can only contains 1 - 9, number value can represent the index also
     *
     * Time: O(n^2)
     * Space: O(n^2)
     */

    public boolean isValidSudokuS2(char[][] board) {
        boolean[][] horizon = new boolean[board.length][board.length], verti = new boolean[board.length][board.length], subBox = new boolean[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') continue;
                //Due to index 0 - 8, according to number 1 - 9, so index = number - 1
                //smallbox treat 9 small box from left -> right, up -> down as a 9 space of x
                int index = board[i][j] - '0' - 1, smallBoxIndex = i / 3 * 3 + j / 3;
                if (horizon[i][index] || verti[j][index] || subBox[smallBoxIndex][index]) return false;
                horizon[i][index] = verti[j][index] = subBox[smallBoxIndex][index] = true;
            }
        }
        return true;
    }

}
