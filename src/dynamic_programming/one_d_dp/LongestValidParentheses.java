package dynamic_programming.one_d_dp;

import java.util.*;

public class LongestValidParentheses {

    //TAG: dp
    //TAG: stack
    //Difficulty: Hard

    /**
     * 32. Longest Valid Parentheses
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

     For "(()", the longest valid parentheses substring is "()", which has length = 2.

     Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
     */

    /**
     * Solution: DP
     * use a dp array to record the longest valid parentheses until current index
     * M[0] = 0, no matter the first character is ( or ), it's invalid
     * induction rule:
     *      1. when s[i] == (, current substring is not valid, dp[i] = 0
     *      2. when s[i - 1] and s[i] is a pair of ( ), this pair is valid and length 2, so dp[i] = dp[i - 2] + 2
     *      3. when s[i - 1] and s[i] is a pair of ) ), because parentheses is from two side to center, so find the last character before last valid parentheses to see if it's a '('
     *              3.1 if so, s[i - 1] could be a pair, dp[i - 1] + 2 + the dp value before last parentheses and last '(' means dp[i−dp[i−1]−2]
     *              3.2 if not, it's not valid
     *  Keep a global max to record the max value from dp array
     *
     *  Time: O(n)
     *  Space: O(n)
     */

    public int longestValidParenthesesS2(String s) {
        int res = 0;
        if (s == null || s.length() == 0) return res;
        int[] dp = new int[s.length()];
        dp[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') dp[i] = 0;
            else if (s.charAt(i - 1) == '(') dp[i] = (i > 1 ? dp[i - 2] : 0) + 2;
            else {
                //Define a index represent the character which last before the last valid parentheses
                int lastBeforeValid = i - dp[i - 1] - 1;
                if (lastBeforeValid >= 0 && s.charAt(lastBeforeValid) == '(')
                    dp[i] = dp[i - 1] + 2 + (lastBeforeValid - 1 >= 0 ? dp[lastBeforeValid - 1] : 0);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * Solution 3: stack
     * Approach: Think about ignore which part is valid parentheses in the whole string, just push/pop them all in stack to validate, when finish, the stack left the characters which could not combine for a valid parentheses either left side or right side, that means all substring between each character in stack are valid parentheses
     * keep a global max to record the max substring length of stack
     */

    /*
    Solution 3.1 Time exceeded

    1st step to filter all parentheses in string
    2st go back all characters in stack to calculate the max length

    Time: O(2n)
    Space: O(n)
     */

    public int longestValidParenthesesS3_1(String s) {
        if (s == null || s.length() < 2) return 0;
        Stack<parenthesisObj> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')' && !stack.isEmpty() && stack.peek().c == '(') stack.pop();
            else stack.push(new parenthesisObj(c, i));
        }
        if (stack.isEmpty()) return s.length();
        //when calculate the last part of parentheses, last index should be len - 1 + 1, so that this index will not be calculate into the res
        int res = 0, lastIndex = s.length();
        while (!stack.isEmpty()) {
            parenthesisObj cur = stack.pop();
            //Calculate length between lastIndex and cur.index (not include them)
            res = Math.max(res, lastIndex - cur.index - 1);
            lastIndex = cur.index;
        }
        return Math.max(res, lastIndex);
    }

    private class parenthesisObj {
        Character c;
        int index;
        parenthesisObj(Character c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    /*
    Solution 3.2:

    go over the string to push to stack, at the same time, validate the max length of valid parentheses length

    Time: O(n)
    Space: O(n)
     */

    public int longestValidParenthesesS3_2(String s) {
        Stack<Integer> stack = new Stack<>();
        //Want to calculate i - left, not include left, so at first left = -1
        int res = 0, left = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='(') stack.push(i);
            else {
                //If stack is empty, and current is ), then need to add it, already calculation about left part, move left to current i
                if (stack.isEmpty()) left = i;
                else{
                    stack.pop();
                    //calculate the max length at this time when delete one pair of parentheses
                    if(stack.isEmpty()) res = Math.max(res,i - left);
                    else res = Math.max(res,i - stack.peek());
                }
            }
        }
        return res;
    }


}
