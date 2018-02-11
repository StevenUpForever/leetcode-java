package legacy_code.Problem71To80;

public class Word_Search {

    /**
     * 79. Word Search
     * Given a 2D board and a word, find if the word exists in the grid.

     The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

     For example,
     Given board =

     [
     ['A','B','C','E'],
     ['S','F','C','S'],
     ['A','D','E','E']
     ]
     word = "ABCCED", -> returns true,
     word = "SEE", -> returns true,
     word = "ABCB", -> returns false.
     */

    /**
     * Solution 1: depth_first_search
     * Use a visit helper matrix to indicate whether current cell is visited
     * Try to recursively go 4 directions which helper matrix is false, then if any path goes all characters in word, return true
     *
     * Time: O(mn) for for loop to find start * O(4^max(m, n)) recursion tree (for branches, max(m, n) levels) = O(mn * 4^max(m, n)) = O(4^max(m, n))
     * Space: O(mn) helper matrix + O(max(m, n)) recursion levels = O(mn + max(m, n)) = O(mn)
     */

    private static boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return true;
        else if (board.length == 0) return false;
        char[] chars = word.toCharArray();
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //Begin recursion for current (i, j) if not even could begin with first char, will return by base case in recursion method
//                if (existHelperS1(board, chars, 0, i, j)) return true;
                if (existHelperS2(board, chars, 0, i, j)) return true;
            }
        }
        return false;
    }

    private boolean existHelperS1(char[][] board, char[] chars, int index, int x, int y) {
        //Check index first, so that board[x][y] != chars[index] will not overflow
        if (index == chars.length) return true;
        if (x < 0 || y < 0 || x == board.length || y == board[0].length || board[x][y] != chars[index] || visited[x][y])
            return false;
        visited[x][y] = true;
        if (existHelperS1(board, chars, index + 1, x + 1, y) ||
                existHelperS1(board, chars, index + 1, x, y + 1) ||
                existHelperS1(board, chars, index + 1, x - 1, y) ||
                existHelperS1(board, chars, index + 1, x, y - 1)) return true;
        //reset visit status
        visited[x][y] = false;
        return false;
    }

    /**
     * Solution 2:
     * Depends on solution 1, all elements in this problem in matrix are from 'A' to 'Z', which we could modify the original matrix value to a non 'A' - 'Z'
     *
     * Time: O(4^max(m, n))
     * Space: O(1)
     */

    private boolean existHelperS2(char[][] board, char[] chars, int index, int x, int y) {
        //Check index first, so that board[x][y] != chars[index] will not overflow
        if (index == chars.length) return true;
        if (x < 0 || y < 0 || x == board.length || y == board[0].length || board[x][y] != chars[index])
            return false;
        /*
        From legacy_code, could set to board[y][x] ^= 256, which letter ^ 256 will became a non-letter
         */
        board[x][y] = '.';
        if (existHelperS2(board, chars, index + 1, x + 1, y) ||
                existHelperS2(board, chars, index + 1, x, y + 1) ||
                existHelperS2(board, chars, index + 1, x - 1, y) ||
                existHelperS2(board, chars, index + 1, x, y - 1)) return true;
        //reset visit status
        //Due to if goes board[x][y] = '.' this step, means current char[] and matrix matched, so could reset to the related chars value
        /*
        Mark from legacy_code, reset board[y][x] ^= 256
         */
        board[x][y] = chars[index];
        return false;
    }

}
