package data_structure;

import java.util.HashMap;
import java.util.Map;

public class Q359LoggerRateLimiter {

    //TAG: Google
    //TAG: data structure
    //difficulty: easy

    /**
     * 359. Logger Rate Limiter
     * Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.
     *
     * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.
     *
     * It is possible that several messages arrive roughly at the same time.
     *
     * Example:
     *
     * Logger logger = new Logger();
     *
     * // logging string "foo" at timestamp 1
     * logger.shouldPrintMessage(1, "foo"); returns true;
     *
     * // logging string "bar" at timestamp 2
     * logger.shouldPrintMessage(2,"bar"); returns true;
     *
     * // logging string "foo" at timestamp 3
     * logger.shouldPrintMessage(3,"foo"); returns false;
     *
     * // logging string "bar" at timestamp 8
     * logger.shouldPrintMessage(8,"bar"); returns false;
     *
     * // logging string "foo" at timestamp 10
     * logger.shouldPrintMessage(10,"foo"); returns false;
     *
     * // logging string "foo" at timestamp 11
     * logger.shouldPrintMessage(11,"foo"); returns true;
     */

    /*
    Solution:
    Be aware that only if the message is printed then within 10 secs cannot print, which means, when use a hashMap
    to record message and latest time, need to make sure that time the message is printed, which means
    it's not the latest message but the latest PRINTED message
     */

    class Logger {

        private Map<String, Integer> map;
        /** Initialize your data structure here. */
        public Logger() {
            this.map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            boolean res = !map.containsKey(message) || map.get(message) + 10 <= timestamp;
            if (res) map.put(message, timestamp);
            return res;
        }
    }

}
