package Problem11To20;

public class Longest_Common_Prefix {

    /**
     * 14. Longest Common Prefix
     * Write a function to find the longest common prefix string amongst an array of strings.
     */

    /**
     * Solution:
     * Keep result variable, index variable, iterative each string in array
     *      1. get the character from the first string
     *      2. consider some corner cases
     *          1. if index is over any string's length, return current result
     *          2. if current index character from any string is not equal to this local compare character, return the current character
     *      3. After finally met any conditions from step 2, worst case when all string the same length and same character sets, return final result
     *
     * Time: O(n) n represent the total length from all the strings in the array
     * Space: O(min(len(0) ... len(end))) worst case is when all strings are same length and character sets
     */

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        StringBuilder builder = new StringBuilder();
        //Outer loop make strs[0] as pivot, so i will not over strs[0] length, also will include the condition that index is over 0th string length but not others
        for (int i = 0; i < strs[0].length(); i++) {
            char cur = strs[0].charAt(i);
            //Inner loop iterative all rest strings in array
            for (int j = 1; j < strs.length; j++) {
                //If index over any string's length or not equal to 0th string current character, prefix ended
                if (i >= strs[j].length() || strs[j].charAt(i) != cur) return builder.toString();
            }
            //If could go over all inner loop, means current character is within common prefix
            builder.append(cur);
        }
        return builder.toString();
    }

}
