package data_structure;

import java.util.*;

public class DesignSnakeGame {

    //Difficulty: medium
    //TAG: Uber
    //TAG: data structure

    /**
     * 353. Design Snake Game
     * Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
     *
     * The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
     *
     * You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
     *
     * Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
     *
     * When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.
     *
     * Example:
     *
     * Given width = 3, height = 2, and food = [[1,2],[0,1]].
     *
     * Snake snake = new Snake(width, height, food);
     *
     * Initially the snake appears at position (0,0) and the food at (1,2).
     *
     * |S| | |
     * | | |F|
     *
     * snake.move("R"); -> Returns 0
     *
     * | |S| |
     * | | |F|
     *
     * snake.move("D"); -> Returns 0
     *
     * | | | |
     * | |S|F|
     *
     * snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
     *
     * | |F| |
     * | |S|S|
     *
     * snake.move("U"); -> Returns 1
     *
     * | |F|S|
     * | | |S|
     *
     * snake.move("L"); -> Returns 2 (Snake eats the second food)
     *
     * | |S|S|
     * | | |S|
     *
     * snake.move("U"); -> Returns -1 (Game over because snake collides with border)
     */

    /*
    Solution 1:
    allocate a board, keep track the state, each cell represent by next step direction
    e.g.
    "U" represent next step is up
    use direction represent cell due to need to know when delete a tail, which is the next tail

    Use "F" represent the food location

    Case that return -1:
    new x or y over board limit
    x or y meet the other part, e.g. "U" "L" "D" "R"

    Note: when next move is just the old tail, it won't return -1, due to head and tail will move in the same time
     */

    class SnakeGame {

        private int start_x, start_y, end_x, end_y, foodIndex, score;
        private String[][] board;
        private int[][] food;

        /** Initialize your data structure here.
         @param width - screen width
         @param height - screen height
         @param food - A list of food positions
         E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
        public SnakeGame(int width, int height, int[][] food) {
            start_x = 0;
            start_y = 0;
            end_x = 0;
            end_y = 0;
            board = new String[height][width];
            this.food = food;
            if (food.length > 0) {
                board[food[0][0]][food[0][1]] = "F";
            }
            foodIndex = 1;
            score = 0;
        }

        /** Moves the snake.
         @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
         @return The game's score after the move. Return -1 if game over.
         Game over when snake crosses the screen boundary or bites its body. */
        public int move(String direction) {
            //indicate where current cell will go
            board[start_x][start_y] = direction;
            int[] newStart = movePoint(direction, start_x, start_y);
            start_x = newStart[0];
            start_y = newStart[1];
            if (start_x < 0 || start_x >= board.length ||
                    start_y < 0 || start_y >= board[0].length ||
                    (board[start_x][start_y] != null && !board[start_x][start_y].equals("F")
                            //Any x or y not met means not the same point
                            && (start_x != end_x || start_y != end_y))) return -1;
            /*
            if the new move is empty or not the food, delete the tail
            == null for move to new place, equals("F") here means met the old tail
             */
            if (board[start_x][start_y] == null || !board[start_x][start_y].equals("F")) {
                int temp_end_x = end_x, temp_end_y = end_y;
                int[] newEnd = movePoint(board[end_x][end_y], end_x, end_y);
                end_x = newEnd[0];
                end_y = newEnd[1];
                //reset tail to null
                board[temp_end_x][temp_end_y] = null;
            } else {
                score++;
                if (foodIndex < food.length) {
                    board[food[foodIndex][0]][food[foodIndex][1]] = "F";
                    foodIndex++;
                }
            }
            return score;
        }

        private int[] movePoint(String direction, int x, int y) {
            switch (direction) {
                case "U":
                    x--;
                    break;
                case "D":
                    x++;
                    break;
                case "L":
                    y--;
                    break;
                case "R":
                    y++;
                    break;
            }
            return new int[]{x, y};
        }

    }

    /*
    Solution 2:
    Save board memory, use queue represent the status, push means new head, and pop means delete tail
    Use a set add all body to filter when new head meet the visited body and return -1

    Use index/width represent row, index/width represent col

    see https://leetcode.com/problems/design-snake-game/discuss/82668/Java-Deque-and-HashSet-design-with-detailed-comments
     */

    public class SnakeGame2 {

        Set<Integer> set; // this copy is good for fast loop-up for eating body case
        Deque<Integer> body; // this copy is good for updating tail
        int score;
        int[][] food;
        int foodIndex;
        int width;
        int height;

        public SnakeGame2(int width, int height, int[][] food) {
            this.width = width;
            this.height = height;
            this.food = food;
            set = new HashSet<>();
            set.add(0);
            body = new LinkedList<>();
            body.offerLast(0);
        }


        public int move(String direction) {
            //case 0: game already over: do nothing
            if (score == -1) {
                return -1;
            }
            int rowHead = body.peekFirst() / width;
            int colHead = body.peekFirst() % width;
            switch (direction) {
                case "U" : rowHead--;
                    break;
                case "D" : rowHead++;
                    break;
                case "L" : colHead--;
                    break;
                default :  colHead++;
            }
            int head = rowHead * width + colHead;

            //case 1: out of boundary or eating body
            //Remove tail first in case a fake -1 due to head met old tail
            set.remove(body.peekLast()); // new head is legal to be in old tail's position, remove from set temporarily
            if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
                return score = -1;
            }

            // add head for case2 and case3
            set.add(head);
            body.offerFirst(head);

            //case2: eating food, keep tail, add head
            if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
                set.add(body.peekLast()); // old tail does not change, so add it back to set
                foodIndex++;
                return ++score;
            }
            //case3: normal move, remove tail, add head
            body.pollLast();
            return score;
        }
    }

}


