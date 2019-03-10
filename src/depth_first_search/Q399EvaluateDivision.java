package depth_first_search;

import java.util.*;

public class Q399EvaluateDivision {

    //Difficulty: medium
    //TAG: Uber
    //TAG: DFS

    /**
     * 399. Evaluate Division
     * Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
     *
     * Example:
     * Given a / b = 2.0, b / c = 3.0.
     * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
     * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
     *
     * The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.
     *
     * According to the example above:
     *
     * equations = [ ["a", "b"], ["b", "c"] ],
     * values = [2.0, 3.0],
     * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
     */

    /*
    Solution:
    Key point is the query x/y x may not have direct relationship with y
    e.g.
    a/b = 2.0 b/c = 3.0 c/d = 4.0 query is a/d

    So first we add all relations in a map
    with <a [b, "2.0"]>

    Then do a DFS find a match between a and b
     */

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if (equations == null || values == null || queries == null) return new double[0];
        Map<String, List<String[]>> map = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            String[] equation = equations[i];
            double value = values[i];
            map.putIfAbsent(equation[0], new ArrayList<>());
            map.get(equation[0]).add(new String[]{equation[1], String.valueOf(value)});
            map.putIfAbsent(equation[1], new ArrayList<>());
            map.get(equation[1]).add(new String[]{equation[0], String.valueOf(1/value)});
        }
        double[] res = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            double val = calcEquationDFS(queries[i][0], queries[i][1], 1.0, map, new HashSet<>());
            res[i] = val == 0.0 ? -1.0 : val;
        }
        return res;
    }

    private double calcEquationDFS(String start, String end, double value, Map<String,
            List<String[]>> map, Set<String> set) {
        if (set.contains(start) || !map.containsKey(start)) return 0.0;
        if (start.equals(end)) return value;
        set.add(start);

        List<String[]> list = map.get(start);
        for (String[] strs: list) {
            double temp = calcEquationDFS(strs[0], end, Double.valueOf(strs[1]) * value, map, set);
            if (temp != 0.0) return temp;
        }
        set.remove(start);
        return 0.0;
    }

}
