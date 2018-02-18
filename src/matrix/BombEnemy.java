package matrix;

public class BombEnemy {

    //TAG: Google
    //TAG: matrix
    //Difficulty: Medium

    /**
     * 361. Bomb Enemy
     * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
     The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
     Note that you can only put the bomb at an empty cell.

     Example:
     For the given grid

     0 E 0 0
     E 0 W E
     0 E 0 0

     return 3. (Placing a bomb at (1,1) kills 3 enemies)
     */

    /**
     * Solution:
     * Similar to largest cross (longest cross in matrix for '1')
     * prepare two matrix row and col, one for in current row[i][j] total number of E to left and right until end or W
     * col for col[i][j] total number of E to up and down until end or 'W'
     * at last, loop matrix, when goto '0' update global max = Math.max(max, row[i][j] + col[i][j])
     *
     * Time: O(2m^2 * n^2)
     * Space: O(2mn) = O(mn)
     */

    public int maxKilledEnemies(char[][] grid) {
        if (grid == null|| grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length;
        int[][] rowMatrx = new int[row][col], colMatrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 'W') {
                    rowMatrx[i][j] += j > 0 ? rowMatrx[i][j - 1] : 0;
                    rowMatrx[i][j] += grid[i][j] == 'E' ? 1 : 0;
                    colMatrix[i][j] += i > 0 ? colMatrix[i - 1][j] : 0;
                    colMatrix[i][j] += grid[i][j] == 'E' ? 1 : 0;
                    //When met the end or next is 'W', track back fill all non 'W' value to current max value
                    if (j == col - 1 || grid[i][j + 1] == 'W') {
                        for (int k = j - 1; k >= 0 && grid[i][k] != 'W'; k--)
                            rowMatrx[i][k] = rowMatrx[i][j];
                    }
                    if (i == row - 1 || grid[i + 1][j] == 'W') {
                        for (int k = i - 1; k >= 0 && grid[k][j] != 'W'; k--)
                            colMatrix[k][j] = colMatrix[i][j];
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, rowMatrx[i][j] + colMatrix[i][j]);
                }
            }
        }
        return max;
    }

}
