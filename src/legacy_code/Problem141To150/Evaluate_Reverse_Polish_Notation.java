package legacy_code.Problem141To150;

import java.util.*;

public class Evaluate_Reverse_Polish_Notation {

    /**
     * 150. Evaluate Reverse Polish Notation
     * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

     Valid operators are +, -, *, /. Each operand may be an integer or another expression.

     Some examples:
     ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
     ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
     */

    /**
     * Solution:
     * Use stack, when met +, -, *, /, pop two numbers calculate by the met +, -, *, /, then push back to stack, until stack is empty or array is done
     *
     * Time: O(n)
     * Space: O(n/2) if valid Reverse Polish Notation stack = O(n)
     */

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        for (String str: tokens) {
                switch (str) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        //Be aware of - and / is last second -// last first
                        Integer first = stack.pop();
                        Integer second = stack.pop();
                        stack.push(second - first);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        Integer first2 = stack.pop();
                        Integer second2 = stack.pop();
                        stack.push(second2/first2);
                        break;
                    default:
                        stack.push(Integer.valueOf(str));
                        break;
                }
        }
        return stack.peek();
    }

}

