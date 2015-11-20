
public class ProblemFrom2To20 {


    /*Problem 14:
    Write a function to find the longest common prefix string amongst an array of strings.
     */
    public String longestCommonPrefix(String[] strs) {
        //if array strs has no elements, return ""
        if (strs.length == 0){
            return "";
        }
        //find the min length string in strs as minStr
        int minLen = strs[0].length();
        String minStr = strs[0];
        for (String str: strs) {
            if (str.length() == 0) {
                return "";
            }
            if (minLen > Math.min(str.length(), minLen)) {
                minLen = Math.min(str.length(), minLen);
                minStr = str;
            }
        }
        //Shrink the minStr by 1 each step to make sure each string in strs contains this substring and also start from 0
        for (String str: strs) {
            while (str.indexOf(minStr) != 0) {
                minStr = minStr.substring(0, minStr.length() - 1);
            }
        }
        return minStr;
    }









}
