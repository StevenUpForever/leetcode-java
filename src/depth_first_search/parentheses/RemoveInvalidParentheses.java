package depth_first_search.parentheses;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {

    //TAG: Facebook
    //TAG: DFS
    //Difficulty: Hard

    /**
     * 301. Remove Invalid Parentheses
     * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

     Note: The input string may contain letters other than the parentheses ( and ).

     Examples:
     "()())()" -> ["()()()", "(())()"]
     "(a)())()" -> ["(a)()()", "(a())()"]
     ")(" -> [""]
     Credits:
     Special thanks to @hpplayer for adding this problem and creating all test cases.
     */

    /**
     * Solution:
     * Similar to use stack filter (), and give all solutions, use DFS
     *
     * key point is when find more ) than (, try to delete every one ) before this valid string scope
     * 1. permutation all strings and DFS to next level (not know if there's another ) need to delete)
     *
     * 2. push next char if ( or ), no matter where is character, when stack > 0, continue
     * if stack < 0 means there's one extra ), try step 1
     *
     * Because ( may more than ) e.g. ((), and this will know when finish the string
     * so do a reverse step, which char is [) (], and use reverse string
     */

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        remove(res, s, 0, 0, new char[]{'(', ')'});
        return res;
    }

    public void remove(List<String> res, String s, int last_i, int last_j,  char[] chars) {
        int pairs = 0;
        //Loop from end of modified string to end, find next invalid )
        for (int i = last_i; i < s.length(); ++i) {
            if (s.charAt(i) == chars[0]) pairs++;
            if (s.charAt(i) == chars[1]) pairs--;
            //There's one more ) need to delete
            if (pairs < 0) {
                /*
                loop from last end of modified substring to current end, find any ) and
                delete and recursion to next level
                 */
                for (int j = last_j; j <= i; ++j) {
                    /*
                    If two adjacent )) delete anyone will have duplicate output, so skip (because when j
                    at the first dup ), it already deleted once)
                     */
                    if (s.charAt(j) == chars[1] && (j == last_j
                            || s.charAt(j - 1) != chars[1])) {
                        remove(res, s.substring(0, j) + s.substring(j + 1, s.length()), i, j, chars);
                    }
                }
                /*
                return due to dfs will have all solutions, otherwise will have wrong solutions as current s is not
                a valid one and cannot add to res
                 */
                return;
            }
        }
        String reverse = new StringBuilder(s).reverse().toString();
        //Whether there's more ( or not, need to check reverse string to make sure it's valid
        if (chars[0] == '(')
            remove(res, reverse, 0, 0, new char[]{')', '('});
        else res.add(reverse);
    }





}
