package matrix;

public class Q999AvailableCapturesForRook {

    //Difficulty: Easy
    //TAG: matrix

    /**
     * 999. Available Captures for Rook
     * On an 8 x 8 chessboard, there is one white rook.  There also may be empty squares, white bishops, and black pawns.  These are given as characters 'R', '.', 'B', and 'p' respectively. Uppercase characters represent white pieces, and lowercase characters represent black pieces.
     *
     * The rook moves as in the rules of Chess: it chooses one of four cardinal directions (north, east, west, and south), then moves in that direction until it chooses to stop, reaches the edge of the board, or captures an opposite colored pawn by moving to the same square it occupies.  Also, rooks cannot move into the same square as other friendly bishops.
     *
     * Return the number of pawns the rook can capture in one move.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","R",".",".",".","p"],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * Output: 3
     * Explanation:
     * In this example the rook is able to capture all the pawns.
     * Example 2:
     *
     *
     *
     * Input: [[".",".",".",".",".",".",".","."],[".","p","p","p","p","p",".","."],[".","p","p","B","p","p",".","."],[".","p","B","R","B","p",".","."],[".","p","p","B","p","p",".","."],[".","p","p","p","p","p",".","."],[".",".",".",".",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * Output: 0
     * Explanation:
     * Bishops are blocking the rook to capture any pawn.
     * Example 3:
     *
     *
     *
     * Input: [[".",".",".",".",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".","p",".",".",".","."],["p","p",".","R",".","p","B","."],[".",".",".",".",".",".",".","."],[".",".",".","B",".",".",".","."],[".",".",".","p",".",".",".","."],[".",".",".",".",".",".",".","."]]
     * Output: 3
     * Explanation:
     * The rook can capture the pawns at positions b5, d6 and f5.
     *
     *
     * Note:
     *
     * board.length == board[i].length == 8
     * board[i][j] is either 'R', '.', 'B', or 'p'
     * There is exactly one cell with board[i][j] == 'R'
     * Accepted
     * 2,482
     * Submissions
     * 3,255
     */

    public int numRookCaptures(char[][] board) {
        if (board == null || board.length == 0) return 0;
        int row = board.length, col = board[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'R') {
                    for (int x = i - 1; x >= 0; x--) {
                        if (board[x][j] == 'B' || board[x][j] == 'p') {
                            if (board[x][j] == 'p') count++;
                            break;
                        }
                    }
                    for (int x = i + 1; x < row; x++) {
                        if (board[x][j] == 'B' || board[x][j] == 'p') {
                            if (board[x][j] == 'p') count++;
                            break;
                        }
                    }
                    for (int y = j - 1; y >= 0; y--) {
                        if (board[i][y] == 'B' || board[i][y] == 'p') {
                            if (board[i][y] == 'p') count++;
                            break;
                        }
                    }
                    for (int y = j + 1; y < col; y++) {
                        if (board[i][y] == 'B' || board[i][y] == 'p') {
                            if (board[i][y] == 'p') count++;
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

}
