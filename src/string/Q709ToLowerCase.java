package string;

public class Q709ToLowerCase {

    //Difficulty: Easy
    //TAG: Apple
    //TAG: String

    /**
     * 709. To Lower Case
     * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
     *
     *
     *
     * Example 1:
     *
     * Input: "Hello"
     * Output: "hello"
     * Example 2:
     *
     * Input: "here"
     * Output: "here"
     * Example 3:
     *
     * Input: "LOVELY"
     * Output: "lovely"
     */

    public String toLowerCase(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c - 'A' >= 0 && c - 'A' < 26) {
                builder.append((char)(c - 'A' + 'a'));
            } else builder.append(c);
        }
        return builder.toString();
    }

}
