package stack;

import java.util.Stack;

public class Q1021RemoveOutermostParentheses {

    //Difficulty: easy
    //TAG: stack

    /**
     * 1021. Remove Outermost Parentheses
     * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
     *
     * A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
     *
     * Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
     *
     * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
     *
     *
     *
     * Example 1:
     *
     * Input: "(()())(())"
     * Output: "()()()"
     * Explanation:
     * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
     * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
     * Example 2:
     *
     * Input: "(()())(())(()(()))"
     * Output: "()()()()(())"
     * Explanation:
     * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
     * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
     * Example 3:
     *
     * Input: "()()"
     * Output: ""
     * Explanation:
     * The input string is "()()", with primitive decomposition "()" + "()".
     * After removing outer parentheses of each part, this is "" + "" = "".
     *
     *
     * Note:
     *
     * S.length <= 10000
     * S[i] is "(" or ")"
     * S is a valid parentheses string
     */

    /*
    Solution 1:
    Similar to valid parentheses, when stack is going to be empty add substring from only one exit index + 1 to current
    index - 1

    Time: O(n)
    Space: O(n/2)
     */

    public String removeOuterParentheses(String S) {
        Stack<Integer> stack = new Stack<>();
        String res = "";
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (stack.isEmpty() || c == '(') {
                stack.push(i);
            } else {
                int pop = stack.pop();
                if (stack.isEmpty()) {
                    res += (pop + 1 == i) ? "" : S.substring(pop + 1, i);
                }
            }
        }
        return res;
    }

    /*
    Solution 2:
    don't need a stack, since the S is a valid one, we just a variable count current char

    https://leetcode.com/problems/remove-outermost-parentheses/discuss/270022/JavaC%2B%2BPython-Count-Opened-Parenthesis

    Time: O(n)
    Space: O(1)
     */

    public String removeOuterParentheses2(String S) {
        StringBuilder s = new StringBuilder();
        int opened = 0;
        for (char c : S.toCharArray()) {
            if (c == '(' && opened++ > 0) s.append(c);
            if (c == ')' && opened-- > 1) s.append(c);
        }
        return s.toString();
    }

}
