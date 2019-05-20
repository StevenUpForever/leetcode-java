package stack;

import java.util.Stack;

public class Q678ValidParenthesisString {

    //Difficulty: medium
    //TAG: Facebook
    //TAG: stack

    /**
     * 678. Valid Parenthesis String
     * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     * An empty string is also valid.
     * Example 1:
     * Input: "()"
     * Output: True
     * Example 2:
     * Input: "(*)"
     * Output: True
     * Example 3:
     * Input: "(*))"
     * Output: True
     * Note:
     * The string size will be in the range [1, 100].
     */

    /*
    Solution:

    count star in the string,
    when ) over (, check if we have any stars, if so, decrease one star, if not return false
    other wise pop a (

    finally need check ( stack and * stack, due to if something happen like ***((( this will not be a right case

    Time: O(n)
    Space: O(n)
     */

    public boolean checkValidString(String s) {
        Stack<Integer> stack = new Stack<>(), starStack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            else if (c == '*') starStack.push(i);
            else {
                if (stack.isEmpty()) {
                    if (!starStack.isEmpty()) starStack.pop();
                    else return false;
                } else stack.pop();
            }
        }
        while (!stack.isEmpty() && !starStack.isEmpty()) {
            if (stack.pop() > starStack.pop()) return false;
        }
        return stack.isEmpty();
    }

}
