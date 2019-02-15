package string;

public class ValidNumber {

    //Difficulty: Hard
    //TAG: LinkedIn
    //TAG: Apple
    //TAG: String

    /**
     * 65. Valid Number
     * Validate if a given string is numeric.

     Some examples:
     "0" => true
     " 0.1 " => true
     "abc" => false
     "1 a" => false
     "2e10" => true
     Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.

     Update (2015-02-10):
     The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.
     */

    /*
     * Solution:
     * many conditions need to be consider of
     *      1. '.' can only be appear once, and must before 'e'
     *      2. 'e' can only appear once, and must after '.', and cannot be the last char
     *      3. '+', '-' can only appear once (anyone) and just after 'e'
     *      4. cannot have non-numbers like 'a' 'b' etc.
     *
     * Time: O(n)
     * Space: O(1)
     */

    public boolean isNumber(String s) {
        boolean point = false, e = false, num = false, numAfterE = false;
        //Filter the prefix and suffix blanks at first by using s.trim()
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //If c is number, so num and numAfterE is true, not consider where is the number
            if (c >= '0' && c <= '9') {
                num = true;
                numAfterE = true;
            } else if (c == '.') {
                //if c is '.' but have a 'e' or another 'e' before, not a number
                if (e || point) return false;
                //Set point to true after find this '.'
                point = true;
            } else if (c == 'e') {
                //if c is 'e' but have a 'e' already or not found a number yet (e is the first char), not a number
                if (!num || e) return false;
                e = true;
                //Reset num After E due to current is 'e'
                numAfterE = false;
            } else if (c == '+' || c == '-') {
                //if c is '+' or '-' but not append after a 'e' not a number, not consider about if appear more than one '+' or '-', because already check the unique of 'e' before
                if (i != 0 && s.charAt(i - 1) != 'e') return false;
            } else return false;
        }
        //If has number and have number after 'e' finally ('e' is not the last char), return true
        return num && numAfterE;
    }

}
