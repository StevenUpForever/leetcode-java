package breadth_first_search.min_steps;

import java.util.*;

public class Q126WordLadderII {

    //Difficulty: hard
    //TAG: Amazon
    //TAG: dfs
    //TAG: bfs

    /**
     * 126. Word Ladder II
     * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
     *
     * Only one letter can be changed at a time
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note:
     *
     * Return an empty list if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * Example 1:
     *
     * Input:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * Output:
     * [
     *   ["hit","hot","dot","dog","cog"],
     *   ["hit","hot","lot","log","cog"]
     * ]
     * Example 2:
     *
     * Input:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * Output: []
     *
     * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
     */

    /*
    Solution:

    bfs find shorted path count the dfs find all paths with the same count

    Time: O(n + n!)
    Space: O(n)
     */

    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        HashSet<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        HashMap<String, ArrayList<String>> nodeNeighbors = new HashMap<>();
        HashMap<String, Integer> distance = new HashMap<>();
        ArrayList<String> solution = new ArrayList<>();

        dict.add(start);
        bfs(start, end, dict, nodeNeighbors, distance);
        dfs(start, end, nodeNeighbors, distance, solution, res);
        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String start, String end, Set<String> dict, HashMap<String, ArrayList<String>> nodeNeighbors,
                     HashMap<String, Integer> distance) {
        for (String str : dict)
            nodeNeighbors.put(str, new ArrayList<>());

        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);

        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                ArrayList<String> neighbors = getNeighbors(cur, dict);

                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor))
                            foundEnd = true;
                        else
                            queue.offer(neighbor);
                    }
                }
            }

            if (foundEnd) break;
        }
    }

    // Find all next level nodes.
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<>();
        char chs[] = node.toCharArray();

        for (char ch ='a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }

    // DFS: output all paths with the shortest distance.
    private void dfs(String cur, String end, HashMap<String, ArrayList<String>> nodeNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }

}
