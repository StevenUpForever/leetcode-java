package depth_first_search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CourseSchedule {

    //TAG: Uber
    //TAG: DFS
    //TAG: BFS
    //Difficulty: Medium

    /**
     * 207. Course Schedule
     * There are a total of n courses you have to take, labeled from 0 to n - 1.

     Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

     Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

     For example:

     2, [[1,0]]
     There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

     2, [[1,0],[0,1]]
     There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

     Note:
     The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
     You may assume that there are no duplicate edges in the input prerequisites.
     click to show more hints.

     Hints:
     This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
     Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
     Topological sort could also be done via BFS.
     */

    /**
     * Solution: DFS
     * This is like find if some of num within numCourses would make a cycle
     *
     * Time: O(n^n)
     * Space: O(n)
     */

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < prerequisites.length; i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        //Check all pairs
        for(int i = 0; i < numCourses; i++){
            boolean[] visited = new boolean[numCourses];
            if(!dfs(graph, visited, i)) return false;
        }
        return true;
    }

    /*
    Use visit array to filter if any course is visited at current level, otherwise go over all other prerequisites of
    this course and set visited[course] = true
     */
    private boolean dfs(ArrayList[] graph, boolean[] visited, int course){
        if(visited[course]) return false;
        else visited[course] = true;
        for(int i = 0; i < graph[course].size(); i++){
            if(!dfs(graph, visited, (int)graph[course].get(i))) return false;
        }
        visited[course] = false;
        return true;
    }

    //TODO: BFS solution

}
