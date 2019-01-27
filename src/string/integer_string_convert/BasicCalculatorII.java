package string.integer_string_convert;

import java.util.Stack;

public class BasicCalculatorII {

    //Difficulty: medium
    //TAG: Apple
    //TAG: String

    /**
     * 227. Basic Calculator II
     * Implement a basic calculator to evaluate a simple expression string.
     *
     * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
     *
     * Example 1:
     *
     * Input: "3+2*2"
     * Output: 7
     * Example 2:
     *
     * Input: " 3/2 "
     * Output: 1
     * Example 3:
     *
     * Input: " 3+5 / 2 "
     * Output: 5
     * Note:
     *
     * You may assume that the given expression is always valid.
     * Do not use the eval built-in library function.
     */

    /*
    Solution:

    don't need to calculate the result in one pass, that will involve many cases

    push all numbers to stack, with + num be push(num), - num be push(num), * num be push(pop * num),
    / num be push(pop / num)

    then just add all numbers in stack to res and return

    Time: O(n)
    Space: O(n)
     */

    public int calculate(String s) {
        if (s == null) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    case '/':
                        stack.push(stack.pop() / num);
                        break;
                        default:
                            break;
                }
                num = 0;
                sign = c;
            }
         }
         num = 0;
        while (!stack.isEmpty()) num += stack.pop();
        return num;
    }

}
