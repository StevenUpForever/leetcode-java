package data_structure;

public class Q348DesignTicTacToe {

    //Difficulty: medium
    //TAG: Amazon
    //TAG: data structure

    /**
     * 348. Design Tic-Tac-Toe
     * Design a Tic-tac-toe game that is played between two players on a n x n grid.
     *
     * You may assume the following rules:
     *
     * A move is guaranteed to be valid and is placed on an empty block.
     * Once a winning condition is reached, no more moves is allowed.
     * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
     * Example:
     * Given n = 3, assume that player 1 is "X" and player 2 is "O" in the board.
     *
     * TicTacToe toe = new TicTacToe(3);
     *
     * toe.move(0, 0, 1); -> Returns 0 (no one wins)
     * |X| | |
     * | | | |    // Player 1 makes a move at (0, 0).
     * | | | |
     *
     * toe.move(0, 2, 2); -> Returns 0 (no one wins)
     * |X| |O|
     * | | | |    // Player 2 makes a move at (0, 2).
     * | | | |
     *
     * toe.move(2, 2, 1); -> Returns 0 (no one wins)
     * |X| |O|
     * | | | |    // Player 1 makes a move at (2, 2).
     * | | |X|
     *
     * toe.move(1, 1, 2); -> Returns 0 (no one wins)
     * |X| |O|
     * | |O| |    // Player 2 makes a move at (1, 1).
     * | | |X|
     *
     * toe.move(2, 0, 1); -> Returns 0 (no one wins)
     * |X| |O|
     * | |O| |    // Player 1 makes a move at (2, 0).
     * |X| |X|
     *
     * toe.move(1, 0, 2); -> Returns 0 (no one wins)
     * |X| |O|
     * |O|O| |    // Player 2 makes a move at (1, 0).
     * |X| |X|
     *
     * toe.move(2, 1, 1); -> Returns 1 (player 1 wins)
     * |X| |O|
     * |O|O| |    // Player 1 makes a move at (2, 1).
     * |X|X|X|
     * Follow up:
     * Could you do better than O(n2) per move() operation?
     */

    /*
    Solution:

    Use row, col array, row[i] represent ith row sum, col[i] represent ith col sum
    diag1, diag2 represent sum of two diagonals

    every move, we try update, row, col array and diag1, diag2 with value that player 1 for -1, player2 for 1
    Thus when we get value from row[i] col[i] diag1 diag2 be -n or n that means player1 or player2 win

    Time: O(1)
    Space: O(n)
     */

    class TicTacToe {

        private int[] rowScore, colScore;
        private int diag1, diag2, n;

        /** Initialize your data structure here. */
        public TicTacToe(int n) {
            rowScore = new int[n];
            colScore = new int[n];
            diag1 = 0;
            diag2 = 0;
            this.n = n;
        }

        /** Player {player} makes a move at ({row}, {col}).
         @param row The row of the board.
         @param col The column of the board.
         @param player The player, can be either 1 or 2.
         @return The current winning condition, can be either:
         0: No one wins.
         1: Player 1 wins.
         2: Player 2 wins. */
        public int move(int row, int col, int player) {
            int val = player == 1 ? -1 : 1;
            rowScore[row] += val;
            colScore[col] += val;
            if (row == col) diag1 += val;
            if (n - row - 1 == col) diag2 += val;
            //after update, if any sum == -n or n we return current player
            if (Math.abs(rowScore[row]) == n ||
                    Math.abs(colScore[col]) == n ||
                    Math.abs(diag1) == n ||
                    Math.abs(diag2) == n) return player;
            else return 0;
        }
    }

    /**
     * Your TicTacToe object will be instantiated and called as such:
     * TicTacToe obj = new TicTacToe(n);
     * int param_1 = obj.move(row,col,player);
     */

}
