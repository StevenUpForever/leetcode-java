package breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class Q490TheMaze {

    //Difficulty: medium
    //TAG: Amazon
    //TAG: bfs

    /**
     * 490. The Maze
     * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
     *
     * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
     *
     * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
     *
     *
     *
     * Example 1:
     *
     * Input 1: a maze represented by a 2D array
     *
     * 0 0 1 0 0
     * 0 0 0 0 0
     * 0 0 0 1 0
     * 1 1 0 1 1
     * 0 0 0 0 0
     *
     * Input 2: start coordinate (rowStart, colStart) = (0, 4)
     * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
     *
     * Output: true
     *
     * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
     *
     * Example 2:
     *
     * Input 1: a maze represented by a 2D array
     *
     * 0 0 1 0 0
     * 0 0 0 0 0
     * 0 0 0 1 0
     * 1 1 0 1 1
     * 0 0 0 0 0
     *
     * Input 2: start coordinate (rowStart, colStart) = (0, 4)
     * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
     *
     * Output: false
     *
     * Explanation: There is no way for the ball to stop at the destination.
     *
     *
     *
     * Note:
     *
     * There is only one ball and one destination in the maze.
     * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
     * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
     * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
     */

    /*
    Solution:

    bfs

    Time: O(mn)
    Space: O(mn)
     */

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x == destination[0] && y == destination[1]) return true;
            int tempX = poll[0] - 1;
            while (tempX >= 0 && maze[tempX][y] == 0) tempX--;
            if (!visited[tempX + 1][y]) {
                visited[tempX + 1][y] = true;
                queue.offer(new int[]{tempX + 1, y});
            }
            int tempY = poll[1] - 1;
            while (tempY >= 0 && maze[x][tempY] == 0) tempY--;
            if (!visited[x][tempY + 1]) {
                visited[x][tempY + 1] = true;
                queue.offer(new int[]{x, tempY + 1});
            }
            tempX = poll[0] + 1;
            while (tempX < maze.length && maze[tempX][y] == 0) tempX++;
            if (!visited[tempX - 1][y]) {
                visited[tempX - 1][y] = true;
                queue.offer(new int[]{tempX - 1, y});
            }
            tempY = poll[1] + 1;
            while (tempY < maze[0].length && maze[x][tempY] == 0) tempY++;
            if (!visited[x][tempY - 1]) {
                visited[x][tempY - 1] = true;
                queue.offer(new int[]{x, tempY - 1});
            }
        }
        return false;
    }

}
