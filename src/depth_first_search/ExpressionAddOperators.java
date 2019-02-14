package depth_first_search;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    //TAG: Google
    //TAG: Facebook
    //TAG: Apple
    //TAG: DFS
    //Difficulty: Hard

    /**
     * 282. Expression Add Operators
     * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
     *
     * Examples:
     * "123", 6 -> ["1+2+3", "1*2*3"]
     * "232", 8 -> ["2*3+2", "2+3*2"]
     * "105", 5 -> ["1*0+5","10-5"]
     * "00", 0 -> ["0+0", "0-0", "0*0"]
     * "3456237490", 9191 -> []
     */

    /*
    Solution:
    DFS
    base case: if index == num.length(), return, and only if current value == target, add to list
    recursion rule:
        loop num, parse diff digits number and recursion + - * to next level
        key point here is save current multiple number, for next possible multiple reorder calculation
        e.g. 1+2*3, first already did 1 + 2, but need do 2*3 first, then could do 1 + 2 - 2(saved) + 2 (saved) * 3

     Time: O(n^n)
     Space: O(n)
     */


    public List<String> addOperators(String num, int target) {
        List<String> list = new ArrayList<>();
        addOperatorsHelper(list, "", num, 0, 0, target, 0);
        return list;
    }

    private void addOperatorsHelper(List<String> list, String operationStr, String num, int index, long cur, int target,
                                    long multi) {
        if (index == num.length()) {
            if (cur == target) list.add(operationStr);
            return;
        }
        //loop num because try to parse diff digits number from current index
        for (int i = index; i < num.length(); i++) {
            //If any number later than first is 0 and first is 0, e.g. 00 is not valid number, skip current loop
            if (i != index && num.charAt(index) == '0') break;
            long curNum = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {
                addOperatorsHelper(list, operationStr + curNum, num, i + 1, curNum, target, curNum);
            } else {
                /*
                save multiple number, e.g. 1 + 2 * 3, first calculate 1 + 2, but need do 2 * 3 first, then we can do
                1 + 2 - 2(saved) + 2 (saved) * 3
                 */
                addOperatorsHelper(list, operationStr + "+" + curNum, num, i + 1,
                        cur + curNum, target, curNum);
                addOperatorsHelper(list, operationStr + "-" + curNum, num, i + 1,
                        cur - curNum, target, -curNum);
                addOperatorsHelper(list, operationStr + "*" + curNum, num, i + 1,
                        cur - multi + multi * curNum, target, multi * curNum);
            }
        }
    }

}
