package map_set;

import java.util.HashMap;
import java.util.Map;

public class Q1013PairsOfSongsWithTotalDurationsDivisibleBy60 {

    //Difficulty: Easy
    //TAG: map_set

    /**
     * 1013. Pairs of Songs With Total Durations Divisible by 60
     * In a list of songs, the i-th song has a duration of time[i] seconds.
     *
     * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.
     *
     *
     *
     * Example 1:
     *
     * Input: [30,20,150,100,40]
     * Output: 3
     * Explanation: Three pairs have a total duration divisible by 60:
     * (time[0] = 30, time[2] = 150): total duration 180
     * (time[1] = 20, time[3] = 100): total duration 120
     * (time[1] = 20, time[4] = 40): total duration 60
     * Example 2:
     *
     * Input: [60,60,60]
     * Output: 3
     * Explanation: All three pairs have a total duration of 120, which is divisible by 60.
     *
     *
     * Note:
     *
     * 1 <= time.length <= 60000
     * 1 <= time[i] <= 500
     */

    /*
    Solution:
    similar to two sum, when checking the target - num = n * 60,
    we know time[i] <= 500, then n * 60 <= 500 + 500, n <= 16
    loop from 1 to 16 find all possible i * 16 in map

    Time: O(n)
    Space: O(n)
     */

    public int numPairsDivisibleBy60(int[] time) {
        if (time == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int num: time) {
            for (int i = 1; i <= 16; i++) {
                int key = i * 60 - num;
                if (map.containsKey(key)) {
                    count += map.get(key);
                }
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return count;
    }

}
