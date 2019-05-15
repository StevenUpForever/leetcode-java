package string;

public class Q1041RobotBoundedInCircle {

    //Difficulty: easy
    //TAG: String

    /**
     * 1041. Robot Bounded In Circle
     * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
     *
     * "G": go straight 1 unit;
     * "L": turn 90 degrees to the left;
     * "R": turn 90 degress to the right.
     * The robot performs the instructions given in order, and repeats them forever.
     *
     * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
     *
     *
     *
     * Example 1:
     *
     * Input: "GGLLGG"
     * Output: true
     * Explanation:
     * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
     * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
     * Example 2:
     *
     * Input: "GG"
     * Output: false
     * Explanation:
     * The robot moves north indefinitely.
     * Example 3:
     *
     * Input: "GL"
     * Output: true
     * Explanation:
     * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
     *
     *
     * Note:
     *
     * 1 <= instructions.length <= 100
     * instructions[i] is in {'G', 'L', 'R'}
     */

    /*
    Solution:

    Run instruction 4 times, and check if it goes back to original point

    Time: O(n)
    Space: O(1)
     */

    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int[][] dirs = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < instructions.length(); j++) {
                char c = instructions.charAt(j);
                switch (c) {
                    case 'G':
                        x += dirs[index][0];
                        y += dirs[index][1];
                        break;
                    case 'L':
                        index++;
                        if (index > 3) index = 0;
                        break;
                    case 'R':
                        index--;
                        if (index < 0) index = 3;
                        break;
                    default:
                        break;
                }
            }
        }
        return x == 0 && y == 0;
    }

}
