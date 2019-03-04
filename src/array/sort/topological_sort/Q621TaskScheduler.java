package array.sort.topological_sort;

import java.util.Arrays;

public class Q621TaskScheduler {

    //TAG: Facebook
    //TAG: Greedy
    //Difficulty: Medium

    /**
     * 621. Task Scheduler
     * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

     However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

     You need to return the least number of intervals the CPU will take to finish all the given tasks.

     Example 1:
     Input: tasks = ["A","A","A","B","B","B"], n = 2
     Output: 8
     Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
     Note:
     The number of tasks is in the range [1, 10000].
     The integer n is in the range [0, 100].
     */

    /*
     * Solution 1:
     * https://leetcode.com/problems/task-scheduler/discuss/104496/concise-Java-Solution-O(N)-time-O(26)-space
     */

    public int leastInterval(char[] tasks, int n) {
        //Stats and sort freqs of tasks and use most freqs task as much as possible
        int[] freqs = new int[26];
        for(char c: tasks) freqs[c - 'A']++;
        Arrays.sort(freqs);
        //Count same highest freqs tasks, so when at last make a frame, could have correct boundary
        int i = 25;
        while(i >= 0 && freqs[i] == freqs[25]) i--;
        //See solution link for count the max length
        return Math.max(tasks.length, (freqs[25] - 1) * (n + 1) + 25 - i);
    }

}
