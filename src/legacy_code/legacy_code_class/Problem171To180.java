package legacy_code.legacy_code_class;

/**
 * Created by ChengzhiJia on 3/14/17.
 */
public class Problem171To180 {
    /*
    171. Excel Sheet Column Number
    Related to question Excel Sheet Column Title

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
Credits:
Special thanks to @ts for adding this problem and creating all test cases.
     */
    /*
    Approach: like 26 bit number calculation
     */
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = 26 * res + s.charAt(i) - 'A' + 1; //Be aware here A represent 1, so need add 'A' - 'A' + 1
        }
        return res;
    }

}
