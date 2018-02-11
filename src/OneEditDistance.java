public class OneEditDistance {

    //TAG: Uber
    //TAG: String

    /**
     * 161. One Edit Distance
     * Given two strings S and T, determine if they are both one edit distance apart.
     */

    /**
     * Solution:
     * Just check if could edit once, so loop the string, when find 1 char different:
     *      if length is the same, that means current diff char need be replaced,
     *      compare s.substring(i + 1) and t.substring(i + 1)
     *      if length is different, check small.substring(i) with large.substring(i + 1)
     *
     * Time: O(min(s, t))
     * Space: O(1)
     */

    public boolean isOneEditDistance(String s, String t) {
        int lens = s.length(), lent = t.length();
        for (int i = 0; i < Math.min(lens, lent); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                //Length is same, could only replace
                if (lens == lent) return s.substring(i + 1).equals(t.substring(i + 1));
                //Otherwise could only add 1 char to shorter string or delete 1 char to longer string
                else return lens < lent ? s.substring(i).equals(t.substring(i + 1)) :
                        s.substring(i + 1).equals(t.substring(i));
            }
        }
        /*
        If goes here, that means only last char of longer string left, check if diff length is 1, totally same string
        should return false
         */
        return Math.abs(lens - lent) == 1;
    }

}
