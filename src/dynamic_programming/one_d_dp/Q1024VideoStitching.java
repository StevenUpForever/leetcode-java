package dynamic_programming.one_d_dp;

import java.util.Arrays;

public class Q1024VideoStitching {

    //Difficulty: medium
    //TAG: dp

    /**
     * 1024. Video Stitching
     * You are given a series of video clips from a sporting event that lasted T seconds.  These video clips can be overlapping with each other and have varied lengths.
     *
     * Each video clip clips[i] is an interval: it starts at time clips[i][0] and ends at time clips[i][1].  We can cut these clips into segments freely: for example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
     *
     * Return the minimum number of clips needed so that we can cut the clips into segments that cover the entire sporting event ([0, T]).  If the task is impossible, return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
     * Output: 3
     * Explanation:
     * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
     * Then, we can reconstruct the sporting event as follows:
     * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
     * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event [0, 10].
     * Example 2:
     *
     * Input: clips = [[0,1],[1,2]], T = 5
     * Output: -1
     * Explanation:
     * We can't cover [0,5] with only [0,1] and [0,2].
     * Example 3:
     *
     * Input: clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
     * Output: 3
     * Explanation:
     * We can take clips [0,4], [4,7], and [6,9].
     * Example 4:
     *
     * Input: clips = [[0,4],[2,8]], T = 5
     * Output: 2
     * Explanation:
     * Notice you can have extra video after the event ends.
     *
     *
     * Note:
     *
     * 1 <= clips.length <= 100
     * 0 <= clips[i][0], clips[i][1] <= 100
     * 0 <= T <= 100
     */

    /*
    Solution:

    dp
    Similar to 294. Flip Game II
    Sort clips by end, we need clips[len - 1] be last clip that can >= T
    dp base case: dp[len - 1] == 1 if clips[len - 1] >= T
    induction rule:
    1. if clip[i][1] >= T this could be the last clip, dp[i] = 1
    2. else loop from i + 1 to len, find any clip that can connect with clip[i] then dp[i] = min(dp[i], dp[j] + 1)
    after update dp[i], check if clip[i][0] == 0, if so update min

    return min

    Time: O(nlogn + n^2)
    Space O(n)
     */

    public int videoStitching(int[][] clips, int T) {
        if (clips == null) return -1;
        Arrays.sort(clips, (int[] o1, int[] o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int[] dp = new int[clips.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int min = dp.length + 1;
        for (int i = clips.length - 1; i >= 0; i--) {
            int[] clip = clips[i];
            if (clip[0] <= T && clip[1] >= T) {
                dp[i] = 1;
            } else {
                for (int j = i + 1; j < clips.length; j++) {
                    int[] next = clips[j];
                    if (clip[1] >= next[0]) {
                        dp[i] = Math.min(dp[i], dp[j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[j] + 1);
                    }
                }
            }
            if (clip[0] == 0) min = Math.min(min, dp[i]);
        }
        return min == dp.length + 1 ? -1 : min;
    }

    /*
    Solution 2:
    greedy

    sort clips by start, then keep extend the clip (by temp variables) to max length, in clips sorted by start,
    first clip each T will be the smallest count

    Time: O(nlogn + n)
    Space: O(1)
     */

    public int videoStitching2(int[][] clips, int T) {
        Arrays.sort(clips, (int[] o1, int[] o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0]- o2[0]);
        int count = 0;
        int end = 0;
        for(int i = 0; i < clips.length; ) {
            //Next smallest clip cannot connect to clop before
            if(clips[i][0] > end) return -1;
            int temp = end;
            while(i < clips.length && clips[i][0] <= end) {
                temp = Math.max(temp, clips[i][1]);
                i++;
            }
            count++;
            end = temp;
            //The first clip that reach to T will be the earliest
            if(end >= T) return count;
        }
        return -1;
    }

}
