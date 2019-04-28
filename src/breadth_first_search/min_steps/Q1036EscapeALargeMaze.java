package breadth_first_search.min_steps;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Q1036EscapeALargeMaze {

    //Difficulty: hard
    //TAG: bfs

    /**
    1036. Escape a Large Maze
     In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.

     We start at the source square and want to reach the target square.  Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.

     Return true if and only if it is possible to reach the target square through a sequence of moves.



     Example 1:

     Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
     Output: false
     Explanation:
     The target square is inaccessible starting from the source square, because we can't walk outside the grid.
     Example 2:

     Input: blocked = [], source = [0,0], target = [999999,999999]
     Output: true
     Explanation:
     Because there are no blocked cells, it's possible to reach the target square.


     Note:

     0 <= blocked.length <= 200
     blocked[i].length == 2
     0 <= blocked[i][j] < 10^6
     source.length == target.length == 2
     0 <= source[i][j], target[i][j] < 10^6
     source != target
     */

    /*
    Solution:

    obviously cannot do bfs or dfs from source to target directly due to large matrix, could start
    from the blocked array, actually the problem is check whether any combination points in blocked could combine
    a boarder than connected to border of the matrix, or could combine a cycle themselves
    And any source or target should one within the cycle, and another one out of the cycle

    it's not easy to check two points separated by the boarder or cycle, so we could do bfs from source AND target
    (totally twice bfs), then check if went over blocked.length steps or find the other side
    the max length points could make boarder is blocked.length, see picture in https://leetcode.com/problems/escape-a-large-maze/discuss/282870/python-solution-with-picture-show-my-thoughts

    Time: O(4^n) 4 directions bfs and n represent length of blocked array
    Space: O(4^n)
     */

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        return bfs(blocked, source, target) && bfs(blocked, target, source);
    }

    private boolean bfs(int[][] blocked, int[] source, int[] target) {
        Set<String> blocks = new HashSet<>(), visited = new HashSet<>();
        for (int[] point: blocked) {
            blocks.add(pointStr(point[0], point[1]));
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(source);
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                int x = poll[0], y = poll[1];
                if (x == target[0] && y == target[1]) return true;
                for (int[] dir: dirs) {
                    int new_x = x + dir[0], new_y = y + dir[1];
                    String str = pointStr(new_x, new_y);
                    if (new_x < 0 || new_x >= 1000000 || new_y < 0 || new_y >= 1000000
                            || visited.contains(str) || blocks.contains(str)) {
                        continue;
                    }
                    visited.add(str);
                    queue.offer(new int[]{new_x, new_y});
                }
            }
            step++;
            if (step >= blocked.length) return true;
        }
        return false;
    }

    private String pointStr(int x, int y) {
        return x + "," + y;
    }

}
