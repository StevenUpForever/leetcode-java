package LeetCode;
import java.util.*;

/**
 * Created by ChengzhiJia on 2/9/17.
 */
public class Problem301To310 {

    /*
    301. Remove Invalid Parentheses
    Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Examples:
"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]
     */
//    public List<String> removeInvalidParentheses(String s) {
//
//    }

    public int numSetBits(long a) {
        int num = 2, res = 0;
        for (int i = 0; i < 32; i++) {
            if (((long)Math.pow(num, i) & a) != 0) {
                res++;
            }
        }
        return res;
    }
}
