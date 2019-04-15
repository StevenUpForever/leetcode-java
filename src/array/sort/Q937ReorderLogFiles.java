package array.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Q937ReorderLogFiles {

    //Difficulty: easy
    //TAG: Amazon
    //TAG: array
    //TAG: sort

    /**
     * 937. Reorder Log Files
     * You have an array of logs.  Each log is a space delimited string of words.
     *
     * For each log, the first word in each log is an alphanumeric identifier.  Then, either:
     *
     * Each word after the identifier will consist only of lowercase letters, or;
     * Each word after the identifier will consist only of digits.
     * We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.
     *
     * Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.
     *
     * Return the final order of the logs.
     *
     *
     *
     * Example 1:
     *
     * Input: ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"]
     * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]
     *
     *
     * Note:
     *
     * 0 <= logs.length <= 100
     * 3 <= logs[i].length <= 100
     * logs[i] is guaranteed to have an identifier, and a word after the identifier.
     */

    /*
    Solution:

    Use comparator to sort logs, cases:

    1. when o1 o2 are all digit logs, put them as their index order
    2. any o1 or o2 is digit logs means another one is letter log, put letter in front of digit
    3. when both are letter logs, compare with substring that after prefix

    Time: O(nlogn)
    Space: O(logn)
     */

    public String[] reorderLogFiles(String[] logs) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < logs.length; i++) map.put(logs[i], i);
        Arrays.sort(logs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] strs1 = o1.split(" "),
                        strs2 = o2.split(" ");
                //Integer.parse(str1[1]) is possible to over Integer limit or slower than this
                boolean isDigits1 = Character.isDigit(strs1[1].charAt(0)),
                        isDigits2 = Character.isDigit(strs2[1].charAt(0));
                if (isDigits1 && isDigits2) {
                    return map.get(o1) - map.get(o2);
                } else if (isDigits1) {
                    return 1;
                } else if (isDigits2) {
                    return -1;
                } else {
                    return o1.substring(strs1[0].length() + 1).compareTo(o2.substring(strs2[0].length() + 1));
                }
            }
        });
        return logs;
    }

}
