package stack;

import java.util.Stack;

public class DecodeString {

    //TAG: Google
    //TAG: String
    //TAG: Stack
    //Difficulty: Medium

    /**
     * 394. Decode String
     * Given an encoded string, return it's decoded string.

     The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

     You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

     Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

     Examples:

     s = "3[a]2[bc]", return "aaabcbc".
     s = "3[a2[c]]", return "accaccacc".
     s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
     */

    /**
     * Solution:
     * Could use recursion/DFS or Stacks
     * Use two stacks, one represent integer need to multiple copied by nested string
     * string stack represent current string level (nested [])
     *
     * if current char is digit, append to integer for next loop
     * if current char is [, then we push current number and string into stack, reset k for nested string
     * if current char is ], then we assign current string to a temp, assign stringstack.pop to current string, which
     * is also upper level string, append temp to current string by integerStack.pop - 1 times
     * Otherwise append char to current string
     *
     */

    public String decodeString(String s) {
        Stack<Integer> integerStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                //Just append digit here, when met [ push the finished number
                k = k * 10 + c - '0';
            } else if (c == '[') {
                integerStack.push(k);
                stringStack.push(builder);
                builder = new StringBuilder();
                //reset k for next nested string
                k = 0;
            } else if (c == ']') {
                StringBuilder temp = builder;
                /*
                set builder to upper level builder, good example to use stack, when pop all out of stringStack, the
                builder will be the initial builder
                 */
                builder = stringStack.pop();
                for (k = integerStack.pop(); k > 0; --k) builder.append(temp);
            } else builder.append(c);
        }
        return builder.toString();
    }


}
