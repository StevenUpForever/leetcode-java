package map_set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q1042FlowerPlantingWithNoAdjacent {

    //Difficulty: easy
    //TAG: array
    //TAG: map

    /**
     * 1042. Flower Planting With No Adjacent
     * You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.
     *
     * paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.
     *
     * Also, there is no garden that has more than 3 paths coming into or leaving it.
     *
     * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
     *
     * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.
     *
     *
     *
     * Example 1:
     *
     * Input: N = 3, paths = [[1,2],[2,3],[3,1]]
     * Output: [1,2,3]
     * Example 2:
     *
     * Input: N = 4, paths = [[1,2],[3,4]]
     * Output: [1,2,1,2]
     * Example 3:
     *
     * Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
     * Output: [1,2,3,4]
     *
     *
     * Note:
     *
     * 1 <= N <= 10000
     * 0 <= paths.size <= 20000
     * No garden has 4 or more paths coming into or leaving it.
     * It is guaranteed an answer exists.
     */

    /*
    Solution:

    use map<int, set<int>> map save all paths, then
    from 1 to N, find any number that not contains in map[i] so it's the gardenNoAdj with i

    Time: O(ij) ij is total numbers in paths
    Space: O(ij)
     */

    public int[] gardenNoAdj(int N, int[][] paths) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] path: paths) {
            map.putIfAbsent(path[0] - 1, new HashSet<>());
            map.get(path[0] - 1).add(path[1] - 1);
            map.putIfAbsent(path[1] - 1, new HashSet<>());
            map.get(path[1] - 1).add(path[0] - 1);
        }
        int[] res = new int[N];
        for (int i = 0; i < N; i++) {
            int[] colors = new int[5];
            for (int j : map.getOrDefault(i, new HashSet<>())) colors[res[j]] = 1;
            for (int k = 4; k > 0; k--)
                if (colors[k] == 0) res[i] = k;
        }
        return res;
    }

}
