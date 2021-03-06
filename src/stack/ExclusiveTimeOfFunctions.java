package stack;

import java.util.*;

public class ExclusiveTimeOfFunctions {

    //TAG: Facebook
    //TAG: Uber
    //TAG: stack
    //Difficulty: Medium

    /**
     * 636. Exclusive Time of Functions
     Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

     Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

     A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

     Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

     Example 1:
     Input:
     n = 2
     logs =
     ["0:start:0",
     "1:start:2",
     "1:end:5",
     "0:end:6"]
     Output:[3, 4]
     Explanation:
     Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1.
     Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
     Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time.
     So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
     Note:
     Input logs will be sorted by timestamp, NOT log id.
     Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
     Two functions won't start or end at the same time.
     Functions could be called recursively, and will always end.
     1 <= n <= 100
     */

    /*
     * Similar to valid parentheses (valid (()()))
     * Use stack to pair the start and end time
     *
     * Time: O(n)
     * Space: O(n)
     */


    public int[] exclusiveTime(int n, List<String> logs) {
        //Res used to record each func's exclusive time
        int[] res = new int[n];
        //stack record the log id, to pair the same id log
        Stack<Integer> stack = new Stack<>();
        //Use preTime to record last func time, no matter start or end
        int preTime = 0;
        for (String log : logs) {
            String[] parts = log.split(":");
            Integer time = Integer.parseInt(parts[2]);
            //No matter new func is start or end, peek func need to add curTime - preTime
            if (!stack.isEmpty()) res[stack.peek()] +=  time - preTime;
            preTime = time;
            if (parts[1].equals("start")) stack.push(Integer.parseInt(parts[0]));
            else {
                /*
                If met the pair (start - end), need to plus 1 in this func time,
                start:0, end:3 time is 4
                 */
                res[stack.pop()]++;
                //Also add preTime by 1 due to same reason
                preTime++;
            }
        }
        return res;
    }

}
