package Problem21To30;

import java.util.*;

public class Generate_Parentheses {

    /**
     * 22. Generate Parentheses
     * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

     For example, given n = 3, a solution set is:

     [
     "((()))",
     "(()())",
     "(())()",
     "()(())",
     "()()()"
     ]
     */

    /**
     * Solution:
     * Permutations/subsets better use recursion
     * for example, when n == 3, the recursion tree is as below
     *              ""
     *             /  \
     *            (   not valid
     *           / \
     *          (   ) (0 '(' left)
     *         / \
     *        (  ) (1 '(' left)
     *       /  / \
     *      (  (  ) (0 '(' left)
     *     /
     *    )
     *   /
     *  )       ......
     * /
     *)
     * Base case: when number of added '(' and ')' is n * 2, add current parentheses string
     * Recursion rule:
     * 1. 1st recursion no matter how many the ( and ) is, if ( is less than n, add (
     * 2. 2st recursion only if ) is less than ( (of course less than n too), add )
     *
     * Time: O(2^n)
     * Space: O(2n) = O(n) most recursion steps, to combine one string as 2n length
     */

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateParenthesisHelper(0, 0, n, new StringBuilder(), res);
        return res;
    }

    private void generateParenthesisHelper(int left, int right, int n, StringBuilder builder, List<String> res) {
        if (left + right == n * 2) {
            res.add(builder.toString());
            return;
        }
        //Remember to reset current build status to previous node when fall back to upper node and go to right subtree
        if (left < n) {
            generateParenthesisHelper(left + 1, right, n, builder.append('('), res);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right < left) {
            generateParenthesisHelper(left, right + 1, n, builder.append(')'), res);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

}
