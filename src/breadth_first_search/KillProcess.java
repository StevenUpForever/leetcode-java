package breadth_first_search;

import java.util.*;

public class KillProcess {

    //Difficulty: medium
    //TAG: Apple
    //TAG: BFS
    //TAG: DFS

    /**
     * 582. Kill Process
     * Given n processes, each process has a unique PID (process id) and its PPID (parent process id).
     *
     * Each process only has one parent process, but may have one or more children processes. This is just like a tree structure. Only one process has PPID that is 0, which means this process has no parent process. All the PIDs will be distinct positive integers.
     *
     * We use two list of integers to represent a list of processes, where the first list contains PID for each process and the second list contains the corresponding PPID.
     *
     * Now given the two lists, and a PID representing a process you want to kill, return a list of PIDs of processes that will be killed in the end. You should assume that when a process is killed, all its children processes will be killed. No order is required for the final answer.
     *
     * Example 1:
     * Input:
     * pid =  [1, 3, 10, 5]
     * ppid = [3, 0, 5, 3]
     * kill = 5
     * Output: [5,10]
     * Explanation:
     *            3
     *          /   \
     *         1     5
     *              /
     *             10
     * Kill 5 will also kill 10.
     * Note:
     * The given kill id is guaranteed to be one of the given PIDs.
     * n >= 1.
     */

    /*
    Solution:
    1. make a hashMap with ppid -> [pid]
    Use BFS or DFS loop start id and add all to list

    Time: O(n)
    Space: O(n)
     */

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        List<Integer> list = new ArrayList<>();
        if (pid == null || ppid == null || kill < 0) return list;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            int curPpid = ppid.get(i);
            map.putIfAbsent(curPpid, new ArrayList<>());
            map.get(curPpid).add(pid.get(i));
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(kill);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            if (poll != 0) list.add(poll);
            List<Integer> curList = map.get(poll);
            if (curList != null) {
                for (Integer id: curList) queue.offer(id);
            }
        }
        return list;
    }

}
