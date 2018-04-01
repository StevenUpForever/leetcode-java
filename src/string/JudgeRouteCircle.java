package string;

public class JudgeRouteCircle {

    //TAG: Google
    //TAG: String
    //Difficulty: Easy

    /**
     * 657. Judge Route Circle
     * Initially, there is a Robot at position (0, 0). Given a sequence of its moves, judge if this robot makes a circle, which means it moves back to the original place.

     The move sequence is represented by a string. And each move is represent by a character. The valid robot moves are R (Right), L (Left), U (Up) and D (down). The output should be true or false representing whether the robot makes a circle.

     Example 1:
     Input: "UD"
     Output: true
     Example 2:
     Input: "LL"
     Output: false
     */

    /**
     * Solution:
     * set a row and col for matrix move, by UDLR, move row or col
     *
     * finally check if row == 0 && col == 0
     *
     * Time: O(n)
     * Space: O(1)
     */

    public boolean judgeCircle(String moves) {
        int row = 0, col = 0;
        if (moves == null || moves.length() == 0) return true;
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            switch (c) {
                case 'U':
                    row--;
                    break;
                case 'D':
                    row++;
                    break;
                case 'L':
                    col--;
                    break;
                case 'R':
                    col++;
                    break;
                    default:
                        break;

            }
        }
        return row == 0 && col == 0;
    }

}
