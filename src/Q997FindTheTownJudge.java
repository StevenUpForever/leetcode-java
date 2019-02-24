import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q997FindTheTownJudge {

    //Difficulty: Easy
    //TAG: array

    /**
     * 997. Find the Town Judge
     * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
     *
     * If the town judge exists, then:
     *
     * The town judge trusts nobody.
     * Everybody (except for the town judge) trusts the town judge.
     * There is exactly one person that satisfies properties 1 and 2.
     * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
     *
     * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: N = 2, trust = [[1,2]]
     * Output: 2
     * Example 2:
     *
     * Input: N = 3, trust = [[1,3],[2,3]]
     * Output: 3
     * Example 3:
     *
     * Input: N = 3, trust = [[1,3],[2,3],[3,1]]
     * Output: -1
     * Example 4:
     *
     * Input: N = 3, trust = [[1,2],[2,3]]
     * Output: -1
     * Example 5:
     *
     * Input: N = 4, trust = [[1,3],[1,4],[2,3],[2,4],[4,3]]
     * Output: 3
     *
     *
     * Note:
     *
     * 1 <= N <= 1000
     * trust.length <= 10000
     * trust[i] are all different
     * trust[i][0] != trust[i][1]
     * 1 <= trust[i][0], trust[i][1] <= N
     */

    /*
    Solution 1:
    record all people -> trusts in a map
    intersect all sets together find public trust people
    check if there is this kind of person and the person trust nobody by !map.containsKey(judge)

    Time: O(n)
    Space: O(n)
     */

    public int findJudge(int N, int[][] trust) {
        if (N <= 0 || trust == null) return -1;
        if (N == 1 && trust.length == 0) return 1;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> judgeSet = new HashSet<>();
        for (int[] arr: trust) {
            map.putIfAbsent(arr[0], new HashSet<>());
            map.get(arr[0]).add(arr[1]);
            judgeSet.add(arr[1]);
        }
        for (Set<Integer> set: map.values()) {
            judgeSet.retainAll(set);
        }
        if (judgeSet.size() == 0) return -1;
        int judge = judgeSet.iterator().next();
        return map.containsKey(judge) ? -1 : judge;
    }

    /*
    Solution 2:
    Similar to 277, loop once add count of current person by:
    trust someone else, count--
    been trusted, count++

    finally the one has trust with N - 1 became the judge

    Time: O(n)
    Space: O(n)
     */

    public int findJudge2(int N, int[][] trust) {
        int[] count = new int[N+1];
        for (int[] t: trust) {
            count[t[0]]--;
            count[t[1]]++;
        }
        for (int i = 1; i <= N; ++i) {
            if (count[i] == N - 1) return i;
        }
        return -1;
    }

}
