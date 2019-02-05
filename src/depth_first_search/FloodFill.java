package depth_first_search;

import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {

    //TAG: Uber
    //TAG: Snap
    //TAG: DFS
    //TAG: Breadth First Search
    //Difficulty: Easy

    /**
     * 733. Flood Fill
     * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

     Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

     To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

     At the end, return the modified image.

     Example 1:
     Input:
     image = [[1,1,1],[1,1,0],[1,0,1]]
     sr = 1, sc = 1, newColor = 2
     Output: [[2,2,2],[2,2,0],[2,0,1]]
     Explanation:
     From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected
     by a path of the same color as the starting pixel are colored with the new color.
     Note the bottom corner is not colored 2, because it is not 4-directionally connected
     to the starting pixel.
     Note:

     The length of image and image[0] will be in the range [1, 50].
     The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
     The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
     */

    /*
     * Solution:
     *
     * bfs
     *
     * Time: O(mn)
     * Space: O(mn)
     */

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0) return image;
        int color = image[sr][sc];
        boolean[][] visited = new boolean[image.length][image[0].length];
        image[sr][sc] = newColor;
        Queue<Cell> queue = new LinkedList<>();
        queue.offer(new Cell(sr, sc));
        visited[sr][sc] = true;
        while (!queue.isEmpty()) {
            Cell poll = queue.poll();
            if (poll.x + 1 < image.length && image[poll.x + 1][poll.y] == color && !visited[poll.x + 1][poll.y]) {
                image[poll.x + 1][poll.y] = newColor;
                visited[poll.x + 1][poll.y] = true;
                queue.offer(new Cell(poll.x + 1, poll.y));
            }
            if (poll.y + 1 < image[0].length && image[poll.x][poll.y + 1] == color && !visited[poll.x][poll.y + 1]) {
                image[poll.x][poll.y + 1] = newColor;
                visited[poll.x][poll.y + 1] = true;
                queue.offer(new Cell(poll.x, poll.y + 1));
            }
            if (poll.x - 1 >= 0 && image[poll.x - 1][poll.y] == color && !visited[poll.x - 1][poll.y]) {
                image[poll.x - 1][poll.y] = newColor;
                visited[poll.x - 1][poll.y] = true;
                queue.offer(new Cell(poll.x - 1, poll.y));
            }
            if (poll.y - 1 >= 0 && image[poll.x][poll.y - 1] == color && !visited[poll.x][poll.y - 1]) {
                image[poll.x][poll.y - 1] = newColor;
                visited[poll.x][poll.y - 1] = true;
                queue.offer(new Cell(poll.x, poll.y - 1));
            }
        }
        return image;
    }

    class Cell {
        int x;
        int y;
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /*
     * Solution:
     *
     * dfs
     *
     * Time: O(mn)
     * Space: O(mn)
     */

    public int[][] floodFillS2(int[][] image, int sr, int sc, int newColor) {
        //Key point is image[sr][sc] == newColor, otherwise color == newColor, recursion will not stop
        if (image == null || image.length == 0 || image[sr][sc] == newColor) return image;
        floodFillHelper(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void floodFillHelper(int[][] image, int sr, int sc, int color, int newColor) {
        //image[sr][sc] != color is the key point, so that Time is O(n) not O(4^n)
        if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length || image[sr][sc] != color)
            return;
        image[sr][sc] = newColor;
        floodFillHelper(image, sr + 1, sc, color, newColor);
        floodFillHelper(image, sr, sc + 1, color, newColor);
        floodFillHelper(image, sr - 1, sc, color, newColor);
        floodFillHelper(image, sr, sc - 1, color, newColor);
    }

}
