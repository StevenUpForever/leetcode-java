package union_find;

import java.util.*;

public class Q990SatisfiabilityOfEqualityEquations {

    //Difficulty: medium
    //TAG: union find

    /**
     * 990. Satisfiability of Equality Equations
     * Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.
     *
     * Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.
     *
     *
     *
     * Example 1:
     *
     * Input: ["a==b","b!=a"]
     * Output: false
     * Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.  There is no way to assign the variables to satisfy both equations.
     * Example 2:
     *
     * Input: ["b==a","a==b"]
     * Output: true
     * Explanation: We could assign a = 1 and b = 1 to satisfy both equations.
     * Example 3:
     *
     * Input: ["a==b","b==c","a==c"]
     * Output: true
     * Example 4:
     *
     * Input: ["a==b","b!=c","c==a"]
     * Output: false
     * Example 5:
     *
     * Input: ["c==c","b==d","x!=z"]
     * Output: true
     *
     *
     * Note:
     *
     * 1 <= equations.length <= 500
     * equations[i].length == 4
     * equations[i][0] and equations[i][3] are lowercase letters
     * equations[i][1] is either '=' or '!'
     * equations[i][2] is '='
     */

    /*
    Solution 1:
    1. loop all == equations, add all equal values to a <char, set> map, includes relative sets, like union find
    2. bfs == equations, find any char that has equal char2 and non-equal char2 return false
    else return true

    Time: O(n)
    Space: O(n)
     */

    public boolean equationsPossible(String[] equations) {
        if (equations == null || equations.length == 0) return true;
        Map<Character, Set<Character>> equalMap = new HashMap<>();
        Map<Character, Set<Character>> unEqualMap = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        for (String str: equations) {
            char first = str.charAt(0), second = str.charAt(3);
            if (str.charAt(1) == '=') {
                equalMap.putIfAbsent(first, new HashSet<>());
                equalMap.get(first).add(second);
                equalMap.get(first).addAll(equalMap.getOrDefault(second, new HashSet<>()));
                equalMap.putIfAbsent(second, new HashSet<>());
                equalMap.get(second).add(first);
                equalMap.get(second).addAll(equalMap.getOrDefault(first, new HashSet<>()));
                queue.offer(first);
                if (first != second) queue.offer(second);
                visited.add(first);
                visited.add(second);
            } else {
                if (first == second) return false;
                unEqualMap.putIfAbsent(first, new HashSet<>());
                unEqualMap.get(first).add(second);
                unEqualMap.get(first).addAll(equalMap.getOrDefault(second, new HashSet<>()));
                unEqualMap.putIfAbsent(second, new HashSet<>());
                unEqualMap.get(second).add(first);
                unEqualMap.get(second).addAll(equalMap.getOrDefault(first, new HashSet<>()));
            }
        }
        while (!queue.isEmpty()) {
            Character cur = queue.poll();
            Set<Character> set = equalMap.get(cur);
            if (set == null) continue;
            for (Character c: set) {
                if (unEqualMap.containsKey(cur) && unEqualMap.get(cur).contains(c)) return false;
                if (visited.add(c)) queue.offer(c);
            }
        }
        return true;
    }

    /*
    Solution 2:
    union find
    bind == equations all start char and last char to union find start char

    Time: O(n)
    Space: O(n)
     */

    private int[] uf = new int[26];
    public boolean equationsPossible2(String[] equations) {
        for (int i = 0; i < 26; ++i) uf[i] = i;
        for (String e : equations) {
            if (e.charAt(1) == '=') {
                union(e.charAt(0) - 'a', e.charAt(3) - 'a');
            }
        }
        for (String e : equations) {
            if (e.charAt(1) == '!' && find(e.charAt(0) - 'a') == find(e.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
    }

    private int find(int x) {
        if (x != uf[x]) uf[x] = find(uf[x]);
        return uf[x];
    }

    private void union(int x, int y) {
        uf[find(x)] = find(y);
    }

}
