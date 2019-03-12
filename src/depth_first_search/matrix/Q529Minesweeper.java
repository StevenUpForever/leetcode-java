package depth_first_search.matrix;

public class Q529Minesweeper {

    //Difficulty: medium
    //TAG: Google
    //TAG: dfs

    /**
     * 529. Minesweeper
     * Let's play the minesweeper game (Wikipedia, online game)!
     *
     * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
     *
     * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after revealing this position according to the following rules:
     *
     * If a mine ('M') is revealed, then the game is over - change it to 'X'.
     * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
     * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
     * Return the board when no more squares will be revealed.
     *
     *
     * Example 1:
     *
     * Input:
     *
     * [['E', 'E', 'E', 'E', 'E'],
     *  ['E', 'E', 'M', 'E', 'E'],
     *  ['E', 'E', 'E', 'E', 'E'],
     *  ['E', 'E', 'E', 'E', 'E']]
     *
     * Click : [3,0]
     *
     * Output:
     *
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'M', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * Explanation:
     *
     * Example 2:
     *
     * Input:
     *
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'M', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * Click : [1,2]
     *
     * Output:
     *
     * [['B', '1', 'E', '1', 'B'],
     *  ['B', '1', 'X', '1', 'B'],
     *  ['B', '1', '1', '1', 'B'],
     *  ['B', 'B', 'B', 'B', 'B']]
     *
     * Explanation:
     *
     *
     *
     * Note:
     *
     * The range of the input matrix's height and width is [1,50].
     * The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
     * The input board won't be a stage when game is over (some mines have been revealed).
     * For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
     */

    /*
    Solution:

    dfs update cell if cell is E and update to count or B, only keep dfs if update to B

    Time: O(mn)
    Space: O(mn)
     */

    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1], row = board.length, col = board[0].length;
        if (x >= row || y >= col) return board;
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            dfs(board, x, y, row, col);
        }
        return board;
    }

    private void dfs(char[][] board, int x, int y, int row, int col) {
        if (x < 0 || x >= row || y < 0 || y >= col || board[x][y] != 'E') return;
        int count = count(board, x, y);
        if (count > 0) {
            board[x][y] = (char)(count + '0');
        } else {
            board[x][y] = 'B';
            //dfs to 8 directions
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    dfs(board, x + i, y + j, row, col);
                }
            }
        }
    }

    private int count(char[][] board, int x, int y) {
        int startX = Math.max(x - 1, 0), startY = Math.max(y - 1, 0),
                endX = Math.min(x + 1, board.length - 1), endY = Math.min(y + 1, board[0].length - 1);
        int count = 0;
        for (int i = startX; i <= endX; i++) {
            for (int j = startY; j <= endY; j++) {
                if (board[i][j] == 'M') count++;
            }
        }
        return count;
    }

}
