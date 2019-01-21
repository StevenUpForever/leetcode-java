package string.integer_string_convert;

public class SolveTheEquation {

    //Difficulty: medium
    //TAG: Uber
    //TAG: String

    /**
     * 640. Solve the Equation
     * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.
     *
     * If there is no solution for the equation, return "No solution".
     *
     * If there are infinite solutions for the equation, return "Infinite solutions".
     *
     * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
     *
     * Example 1:
     * Input: "x+5-3+x=6+x-2"
     * Output: "x=2"
     * Example 2:
     * Input: "x=x"
     * Output: "Infinite solutions"
     * Example 3:
     * Input: "2x=x"
     * Output: "x=0"
     * Example 4:
     * Input: "2x+3x-6x=x+2"
     * Output: "x=-1"
     * Example 5:
     * Input: "x=x+2"
     * Output: "No solution"
     */

    /*
    Solution:
    Key is parse different string situation, e.g.:
    1. +2x means coefficient + 2, -7 means value - 7
    2. when coefficient is 1, e.g. +x -x will same as +1x -1x
    3. if first char or first char after '=' is something like x = 1 + 6, will add extra '+' if not for generic parse

    Use helper function parse a formula that not contains '=', which means separate equation by '=' then we could
    apply same rule to both two substring by add "+" in front if there's not '-'

    Time: O(2n) extra n for Integer.parseInt(str.substring(i, j)), if append number manually, more code but time could
    be O(n)
    Space: O(1)
     */

    public String solveEquation(String equation) {
        if (equation == null) return "No solution";
        int equalIndex = equation.indexOf('=');
        //We separate string by '=', due to may need add extra '+' at front of the string
        int[] first = evalString(equation.substring(0, equalIndex));
        int[] second = evalString(equation.substring(equalIndex + 1));
        int coefficient = first[0] - second[0],
                value = second[1] - first[1];
        if (value == 0) return coefficient == 0 ? "Infinite solutions" : "x=0";
        else return coefficient == 0 ? "No solution" : "x=" + String.valueOf(value/coefficient);
    }

    private int[] evalString(String str) {
        if (str == null || str.length() == 0) return new int[2];
        //index record current start of integer, will be + or -
        int coefficient = 0, value = 0, index = 0;
        //Append + at front if there's no -, in order to apply to generic parse steps
        if (str.charAt(0) != '-') str = '+' + str;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //If at last char, set nextChar to 'y' for parse at here
            char nextC = i == str.length() - 1 ? 'y' : str.charAt(i + 1);
            if (c == '+' || c == '-') {
                index = i;
            }
            //This if means current parse string must be number like +6 -7
            if (nextC == 'y' || nextC == '+' || nextC == '-') {
                value += Integer.parseInt(str.substring(index, i + 1));
                //This if means current parse must be coefficient like +2x -x
            } else if (nextC == 'x') {
                String parseStr = index == i ? str.charAt(index) + "1" : str.substring(index, i + 1);
                coefficient += Integer.parseInt(parseStr);
                /*
                Jump over 'x' in case that there's like 1+1=1+2x which x is the last char and making a +x parseInt
                which make an error
                 */
                i++;
            }
        }
        return new int[]{coefficient, value};
    }

}
