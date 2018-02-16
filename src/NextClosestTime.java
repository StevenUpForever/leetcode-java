import java.util.HashMap;
import java.util.Map;

public class NextClosestTime {

    //TAG: Google
    //Difficulty: Medium

    /**
     * 681. Next Closest Time
     * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

     You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

     Example 1:

     Input: "19:34"
     Output: "19:39"
     Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
     Example 2:

     Input: "23:59"
     Output: "22:22"
     Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.
     */

    /**
     * Solution:
     */
    public String nextClosestTime(String time) {
        char[] chars = time.toCharArray();
        Map<Integer, Character> map = new HashMap<>();
        map.put(0, '2');
        map.put(1, chars[0] < '2' ? '9' : '3');
        map.put(3, '5');
        map.put(4, '9');
        char min = time.charAt(0);

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < min) min = chars[i];
        }
        for (int i = chars.length - 1; i >= 0; i--) {
            if (i != 2 && chars[i] < map.get(i)) {
                char replaceMin = ':';
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] > chars[i] && chars[j] <= map.get(i))
                        if (chars[j] < replaceMin) replaceMin = chars[j];
                }
                if (replaceMin != ':') {
                    chars[i] = replaceMin;
                    for (int k = i + 1; k < chars.length; k++)
                        if (k != 2) chars[k] = min;
                    return new String(chars);
                }
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (i != 2) chars[i] = min;
        }
        return new String(chars);
    }

}
