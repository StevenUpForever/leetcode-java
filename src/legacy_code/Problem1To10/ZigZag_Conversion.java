package legacy_code.Problem1To10;

public class ZigZag_Conversion {

    /**
     * 6. ZigZag Conversion
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

     P   A   H   N
     A P L S I I G
     Y   I   R
     And then read line by line: "PAHNAPLSIIGYIR"
     Write the code that will take a string and make this conversion given a number of rows:

     string convert(string text, int nRows);
     convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     */

    /**
     * Solution:
     * 1. Build nRows number of StringBuilders
     * 2. Iterative every character in string, with the loop for current nRows from left -> right -> left to add each character in proper StringBuilder
     *      Due to ensure the positive and opposite direction has the same length, positive length is [0...nRows - 2], opposite length is [nRows - 1, 1]
     * 3. concat every stringBuilder
     *
     * Time: O(n) for loop each character once
     * Space: O(n) no matter how many stringBuilders created, total characters in all builders are n
     */

    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0 || numRows < 2) return s;
        StringBuilder[] builders = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            builders[i] = new StringBuilder();
        }
        int i = 0;
        while (i < s.length()) {
            /*Each step, add a 'V' style characters, like for the sample, the first loop is add
            P
            A   and     P
                      Y
             it's [0..rows - 2] and [rows - 1, 1]
             */
            for (int m = 0; i < s.length() && m < builders.length - 1; m++) {
                builders[m].append(s.charAt(i));
                i++;
            }
            for (int n = builders.length - 1; i < s.length() && n > 0; n--) {
                builders[n].append(s.charAt(i));
                i++;
            }
        }
        for (int index = 1; index < builders.length; index++) builders[0].append(builders[index]);
        return builders[0].toString();
    }

}
