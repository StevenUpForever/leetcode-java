package legacy_code.Problem61To70;

public class Add_Binary {

    /**
     * 67. Add Binary
     * Given two binary strings, return their sum (also a binary string).

     For example,
     a = "11"
     b = "1"
     Return "100".
     */

    /**
     * Solution:
     * Add bits of a and b from the end to start
     * Keep a carry of the current result
     * append result to the stringBuilder
     * append carry of carry is > 0
     * reverse the result string and return
     *
     * Time: O(max(a, b))
     * Space: O(max(a, b))
     */

    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int len1 = a.length(), len2 = b.length();
        //i will go the max(a, b) length, validation check in the loop
        for (int i = 0; i < len1 || i < len2; i++) {
            int sum = 0;
            if (i < len1) sum += a.charAt(len1 - 1 - i) - '0';
            if (i < len2) sum += b.charAt(len2 - 1 - i) - '0';
            res.append((sum + carry)%2);
            carry = (sum + carry)/2;
        }
        //append carry if carry = 1
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }

}
