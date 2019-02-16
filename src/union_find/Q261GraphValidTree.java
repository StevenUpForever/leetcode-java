package union_find;

import java.util.*;

public class Q261GraphValidTree {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: DFS
    //TAG: BFS
    //TAG: union find

    /**
     * 261. Graph Valid Tree
     * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
     *
     * Example 1:
     *
     * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
     * Output: true
     * Example 2:
     *
     * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
     * Output: false
     * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
     */

    /*
    Solution:
    BFS, due to undirected graph, need add both sides to a map, with [edge[0], [adjacent]] [edge[1], [adjacent]]
    Then bfs start from 0, when any adjacent is visited, cannot make a tree due to cycle
    otherwise add adjacent to the queue for next bfs
    mark current node as complete, e.g. visited[cur] = 2

    Time: O(2 * n) = O(n)
    Space: O(n)
     */

    public boolean validTree(int n, int[][] edges) {
        int[] visited = new int[n];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge: edges) {
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            map.putIfAbsent(edge[1], new ArrayList<>());
            map.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = 1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (map.containsKey(cur)) {
                for (Integer next: map.get(cur)) {
                    if (visited[next] == 1) return false;
                    if (visited[next] == 0) {
                        queue.offer(next);
                        visited[next] = 1;
                    }
                }
            }
            /*
            mark current node to a different status than visited temporarily, in order to when bfs next, it will
            not mark the tree false as an error, e.g. in map we have 0 -> 1 and 1 -> 0
            when bfs 0 -> 1 we need mark 0 as another state like 2, then when bfs 1, it will not mark 0 as error due to
            visited, when other numbers like 2, 3 bfs to 1, will still mark 1 to visited[1] = 1 for next check
             */
            visited[cur] = 2;
        }
        for (int v: visited) { if (v == 0) { return false; } }
        return true;
    }

    /*
    Solution 2:
    union find,
    each head in array means the same root, when vertically two number in edge[] belongs to same root, then it's a cycle
    otherwise union x to y
     */

    public boolean validTree2(int n, int[][] edges) {
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);

            if (x == y) return false;

            // union
            nums[x] = y;
        }

        return edges.length == n - 1;
    }

    private int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }

    /*
    Solution 3 DFS:
    reference to https://leetcode.com/problems/graph-valid-tree/discuss/69042/AC-Java-Graph-DFS-solution-with-adjacency-list
     */

}
