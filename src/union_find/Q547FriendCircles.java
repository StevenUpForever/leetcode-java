package union_find;

public class Q547FriendCircles {

    //Difficulty: Medium
    //TAG: LinkedIn
    //TAG: Union find
    //TAG: DFS
    //TAG: BFS

    /**
     * 547. Friend Circles
     * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
     *
     * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
     *
     * Example 1:
     * Input:
     * [[1,1,0],
     *  [1,1,0],
     *  [0,0,1]]
     * Output: 2
     * Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
     * The 2nd student himself is in a friend circle. So return 2.
     * Example 2:
     * Input:
     * [[1,1,0],
     *  [1,1,1],
     *  [0,1,1]]
     * Output: 1
     * Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
     * so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
     * Note:
     * N is in range [1,200].
     * M[i][i] = 1 for all students.
     * If M[i][j] = 1, then M[j][i] = 1.
     */

    /*
    Solution:
    Union find
    the matrix is symmetric by diagonal, we just need to loop half of it
    use union find merge same row into find(i)
    at last find how many same arr[i] == i means how many groups
     */

    public int findCircleNum(int[][] M) {
        if (M == null) return 0;
        int len = M.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) arr[i] = i;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (M[i][j] == 1) {
                    arr[find(arr, j)] = find(arr, i);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) count++;
        }
        return count;
    }

    private int find(int[] arr, int i) {
        if (arr[i] == i) return i;
        return find(arr, arr[i]);
    }

    /*
    Solution 2: dfs
    https://leetcode.com/problems/friend-circles/discuss/101338/Neat-DFS-java-solution
     */

    /*
    Solution 3: bfs
    https://leetcode.com/problems/friend-circles/discuss/101344/Java-BFS-Equivalent-to-Finding-Connected-Components-in-a-Graph
     */

}
