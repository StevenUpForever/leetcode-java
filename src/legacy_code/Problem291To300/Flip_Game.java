package legacy_code.Problem291To300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Flip_Game {

    /**
     * 293. Flip Game
     * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

     Write a function to compute all possible states of the string after one valid move.

     For example, given s = "++++", after one move, it may become one of the following states:

     [
     "--++",
     "+--+",
     "++--"
     ]
     If there is no valid move, return an empty list [].
     */

    /**
     * Solution:
     * For loop the s, i from 0 to len - 1
     * only if i == '+' && i + 1 == '+' we can repalce with two '-', means original "--" will not be valid
     *
     * Time: O(n)
     * Space: O(n^2) n represent the length of s, worst case will be need to alloc n strings
     */

    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) return res;
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < chars.length - 1; i++) {
            char[] copy = Arrays.copyOf(chars, len);
            if (copy[i] == '+' && copy[i + 1] == '+') {
                copy[i] = copy[i + 1] = '-';
                res.add(String.valueOf(copy));
            }
        }
        return res;
    }

}
