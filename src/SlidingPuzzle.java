import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {

    /**
     * 773. Sliding Puzzle
     * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
     *
     * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
     *
     * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
     *
     * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
     *
     * Examples:
     *
     * Input: board = [[1,2,3],[4,0,5]]
     * Output: 1
     * Explanation: Swap the 0 and the 5 in one move.
     * Input: board = [[1,2,3],[5,4,0]]
     * Output: -1
     * Explanation: No number of moves will make the board solved.
     * Input: board = [[4,1,2],[5,0,3]]
     * Output: 5
     * Explanation: 5 is the smallest number of moves that solves the board.
     * An example path:
     * After move 0: [[4,1,2],[5,0,3]]
     * After move 1: [[4,1,2],[0,5,3]]
     * After move 2: [[0,1,2],[4,5,3]]
     * After move 3: [[1,0,2],[4,5,3]]
     * After move 4: [[1,2,0],[4,5,3]]
     * After move 5: [[1,2,3],[4,5,0]]
     * Input: board = [[3,2,4],[1,5,0]]
     * Output: 14
     * Note:
     *
     * board will be a 2 x 3 array as described above.
     * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
     */

    /*
    Solution:

     */

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return -1;
        Set<String> set = new HashSet<>();
        String str = "";
        int[][] dir = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
        for (int[] arr: board) {
            for (int num: arr) {
                str += num;
            }
        }
        Queue<String> queue = new LinkedList<>();
        set.add(str);
        queue.offer(str);
        int steps = 0;
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            if (poll.equals("123450")) {
                return steps;
            }
            int zeroIndex = poll.indexOf('0');
            for (int num: dir[zeroIndex]) {
                StringBuilder builder = new StringBuilder(str);
                swapChar(builder, num, zeroIndex);
                String newStr = builder.toString();
                if (set.add(newStr)) {
                    queue.offer(newStr);
                }
            }
            steps++;
        }
        return -1;
    }

    private void swapChar(StringBuilder builder, int i, int j) {
        char c = builder.charAt(i);
        builder.setCharAt(i, builder.charAt(j));
        builder.setCharAt(j, c);
    }

}
