package Problem11To20;

import java.util.*;

public class Letter_Combinations_of_a_Phone_Number {

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
     * Solution 1 (Iterative):
     * Outer loop is all characters in string digits, for each character in digits
     *      remove the current first string temp in result list, for the mapped string in digit pad of current character, append it to this temp and add it into final list
     *
     * Time: O(3^n) n represent the length of digits
     * Space: O(3^n) All strings in result list
     */

    static final String[] digitPad = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinationsS1(String digits) {
        LinkedList<String> list = new LinkedList<>();
        if (digits == null || digits.length() == 0) return list;
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
            int padIndex = digits.charAt(i) - '0';
            //While loop all strings not append current character, like when after the third outer loop, the length in result array is [2, 2, 2, 3, 3, 3], need first 3 strings
            while (list.peek().length() == i) {
                String cur = list.remove();
                for (char c: digitPad[padIndex].toCharArray()) {
                    list.add(cur + c);
                }
            }
        }
        return list;
    }

    /**
     * Solution 2 (recursion):
     * Base case: when current string is longer or equal to digits
     *      1.  at least length of result string is 2 like 2, 3 to ad, which when pivot + 1 == 3 equal to string length (each string in pad in length 3)
     *      2. when digits is longer than 2, result will longer than 2 either, which result string length > digit length
     * recursion rule:
     *      1. iterative all characters in digit pad string, add new character in this string to a current recursion string
     *      2. bring this current appended string to next recursion step
     *
     * Time: O(3^n) n represent the length of digits
     * Space: O(3^n) All strings in result list
     */

    public List<String> letterCombinationsS2(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        StringBuilder builder = new StringBuilder();
        letterCombinationsHelper(builder, res, digits, 0);
        return res;
    }

    private void letterCombinationsHelper(StringBuilder curStr, List<String> res, String digits, int index) {
        //As discussed in base case
        if (index >= digits.length()) {
            res.add(curStr.toString());
            return;
        }
        for (char c: digitPad[digits.charAt(index) - '0'].toCharArray()) {
            letterCombinationsHelper(curStr.append(c), res, digits, index + 1);
            //Remember to reset to pre super tree node status before right subTree recursion
            curStr.deleteCharAt(curStr.length() - 1);
        }
    }

}
