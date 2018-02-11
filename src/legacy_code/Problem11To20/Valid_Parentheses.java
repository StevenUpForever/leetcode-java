package legacy_code.Problem11To20;

import java.util.*;

public class Valid_Parentheses {

    /**
     * 20. Valid Parentheses
     * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

     The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
     */

    /**
     * Solution:
     * Use stack to save the parenthesis
     * 1. When met left side parenthesis, push into stack
     * 2. when met right side parenthesis, depends on the feature of parentheses and stack, need to review the most recent (peek in stack) to see if could be a pair, if so, delete the peek, otherwise is invalid parentheses
     * 3. at last to see if stack is empty and s.length() is empty, if any one is not, means over more left side (s is empty) or more right side (stack is empty) return false
     */

    public boolean isValid(String s) {
        if (s == null) return true;
        Stack<Character> stack = new Stack<>();
        //Need to consider about when stack is empty and current char is ) or } or ], the code do this in for loop
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') stack.push(c);
            //If the prerequisite is stack is NOT empty, and then peek and current char pairs
            else if (!stack.isEmpty() && ((c == ')' && stack.peek() == '(')
                        || (c == '}' && stack.peek() == '{')
                        || (c == ']' && stack.peek() == '['))) {
                stack.pop();
            }
            /*
            1. Include when stack is empty and c is ) or } or ]
            2. Include when stack is not empty but current c cannot pair with peek
             */
            else return false;
        }
        return stack.isEmpty();
    }

}
