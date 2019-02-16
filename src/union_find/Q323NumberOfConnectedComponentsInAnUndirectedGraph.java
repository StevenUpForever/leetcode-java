package union_find;

import java.util.Arrays;

public class Q323NumberOfConnectedComponentsInAnUndirectedGraph {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: DFS
    //TAG: BFS
    //TAG: union find

    /**
     * 323. Number of Connected Components in an Undirected Graph
     * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
     *
     * Example 1:
     *
     * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
     *
     *      0          3
     *      |          |
     *      1 --- 2    4
     *
     * Output: 2
     * Example 2:
     *
     * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
     *
     *      0           4
     *      |           |
     *      1 --- 2 --- 3
     *
     * Output:  1
     * Note:
     * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
     */

    /*
    Solution:
    union find, similar to Q261
     */

    public int countComponents(int n, int[][] edges) {
        if (n <= 0 || edges == null) return 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = i;
        for (int[] edge: edges) {
            int find0 = find(nums, edge[0]);
            int find1 = find(nums, edge[1]);
            if (find0 != find1) {
                nums[find0] = find1;
                n--;
            }
        }
        return n;
    }

    private int find(int[] nums, int i) {
        if (nums[i] == i) return i;
        return find(nums, nums[i]);
    }

}
