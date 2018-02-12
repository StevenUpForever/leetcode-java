package string;

public class StringToInteger {

    //TAG: Uber
    //TAG: String

    /**
     * 8. String to Integer (atoi)
     * Implement atoi to convert a string to an integer.

     Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

     Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

     Update (2015-02-10):
     The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
     */

    /**
     * Solution:
     * 1. Ignore all prefix white spaces
     * 2. parse integer part to integer until met non-number character
     *      1. consider about if negative number
     *      2. consider about if value is over integer limit
     * 3. Ignore rest part of String
     */

    public int myAtoi(String str) {
        int res = 0;
        if (str == null) return res;
        int index = 0;
        //Ignore all prefix white-spaces
        while (index < str.length() && str.charAt(index) == ' ') index++;
        int flag = 1;
        //If current char is + or - tag a flag for positive/negative number
        if (index < str.length() && (str.charAt(index) == '-' || str.charAt(index) == '+')) {
            if (str.charAt(index) == '-') flag = -1;
            index++;
        }
        while (index < str.length() && str.charAt(index) - '0' >= 0 && str.charAt(index) - '0' <= 9) {
            int curNum = str.charAt(index) - '0';
            //If current number is valid will be append to result, and will over Int.max
            //Although abs(Int.min) is larger 1 than Int.max, but if over 7, will over Int.max, and at least equal to Int.min, if over 6, then will met Int.max but may not met Int.min
            if (res == Integer.MAX_VALUE / 10 && curNum > 7 || res > Integer.MAX_VALUE / 10)
                return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + curNum;
            index++;
        }
        return res * flag;
    }

}
