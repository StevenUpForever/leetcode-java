package depth_first_search;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {


    //TAG: Uber
    //TAG: DFS
    //Difficulty: Medium

    /**
     * 17. Letter Combinations of a Phone Number
     * Given a digit string, return all possible letter combinations that the number could represent.

     A mapping of digit to letters (just like on the telephone buttons) is given below.



     Input:Digit string "23"
     Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     Note:
     Although the above answer is in lexicographical order, your answer could be in any order you want.
     */

    /**
     * Solution:
     * Begin recursion from the first string, in the loop, recursion next strings related to input digit array related strings
     * recursion tree like below, if input is 23
     *              abc
     *         /    \      \
     *        a     b       c
     *      / \ \ / | \
     *     d  e f d  e f ...
     *
     *     Time: O(m^n) m is string length, n is digit length
     *     Space: O(n)
     */

    static final String[] digitPad = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        letterCombinationsHelper(list, new StringBuilder(), digits, 0);
        return list;
    }

    private void letterCombinationsHelper(List<String> res, StringBuilder builder, String digits, int index) {
        if (index >= digits.length()) {
            if (builder.length() > 0) res.add(builder.toString());
            return;
        }
        String cur = digitPad[digits.charAt(index) - '0'];
        for (int i = 0; i < cur.length(); i++) {
            builder.append(cur.charAt(i));
            letterCombinationsHelper(res, builder, digits, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }

}
