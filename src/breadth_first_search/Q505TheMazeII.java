package breadth_first_search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Q505TheMazeII {

    //Difficulty: Medium
    //TAG: Snap
    //TAG: DFS
    //TAG: BFS

    /**
     * 505. The Maze II
     * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
     *
     * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
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
     * Output: 12
     *
     * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
     *              The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
     * Output: -1
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

    Find smallest steps, bfs is faster than dfs
     */

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length, col = maze[0].length;
        int[][] lengthMatrix = new int[row][col];
        for (int[] arr: lengthMatrix) Arrays.fill(arr, Integer.MAX_VALUE);
        int[][] dirs = new int[][] {{-1,0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start[0], start[1], 0));
        while (!queue.isEmpty()) {
            Point poll = queue.poll();
            //If current length is smaller than new one, new one will not have shorter path, continue
            if (lengthMatrix[poll.x][poll.y] <= poll.length) continue;
            lengthMatrix[poll.x][poll.y] = poll.length;
            for (int[] dir : dirs) {
                int x = poll.x, y = poll.y, length = poll.length;
                while (x >= 0 && x < row && y >= 0 && y < col && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    length++;
                }
                //while loop will add one extra when at begin point, need delete this one
                x -= dir[0];
                y -= dir[1];
                length--;
                queue.offer(new Point(x, y, length));
            }
        }
        return lengthMatrix[destination[0]][destination[1]] == Integer.MAX_VALUE ?
                -1 : lengthMatrix[destination[0]][destination[1]];
    }

    class Point {
        int x, y, length;
        Point(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }

}
