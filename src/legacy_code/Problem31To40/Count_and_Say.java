package legacy_code.Problem31To40;

public class Count_and_Say {

    /**
     * 38. Count and Say
     * The count-and-say sequence is the sequence of integers beginning as follows:
     1, 11, 21, 1211, 111221, ...

     1 is read off as "one 1" or 11.
     11 is read off as "two 1s" or 21.
     21 is read off as "one 2, then one 1" or 1211.
     Given an integer n, generate the nth sequence.

     Note: The sequence of integers will be represented as a string.
     */

    /**
     * Solution: brute force
     * While loop the n until 0
     *      for each loop build current string to the new string under the rule
     */

    public String countAndSay(int n) {
        //Initialize the string as "1" 1st string
        String res = "1";
        //Due to we have 1st string already, so n loop until 1 not 0
        while (n > 1) {
            //new StringBuilder to store countAndSay result of current res string
            StringBuilder temp = new StringBuilder();
            int count = 0, index = 0;
            while (index < res.length()) {
                if (index > 0 && res.charAt(index) != res.charAt(index - 1)) {
                    //Be aware of use API Integer.toString() otherwise it's integer + char ASCII value
                    temp.append(Integer.toString(count) + res.charAt(index - 1));
                    //reset count to 0, it will increase by 1 anyway
                    count = 0;
                }
                count++;
                index++;
            }
            //Append last count part
            temp.append(Integer.toString(count) + res.charAt(index - 1));
            res = temp.toString();
            n--;
        }
        return res;
    }

}
