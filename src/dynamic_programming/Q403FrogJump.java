package dynamic_programming;

import java.util.*;

public class Q403FrogJump {

    //Difficulty: Hard
    //TAG: Apple
    //TAG: DP

    /**
     * 403. Frog Jump
     * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
     *
     * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
     *
     * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
     *
     * Note:
     *
     * The number of stones is ≥ 2 and is < 1,100.
     * Each stone's position will be a non-negative integer < 231.
     * The first stone's position is always 0.
     * Example 1:
     *
     * [0,1,3,5,6,8,12,17]
     *
     * There are a total of 8 stones.
     * The first stone at the 0th unit, second stone at the 1st unit,
     * third stone at the 3rd unit, and so on...
     * The last stone at the 17th unit.
     *
     * Return true. The frog can jump to the last stone by jumping
     * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
     * 2 units to the 4th stone, then 3 units to the 6th stone,
     * 4 units to the 7th stone, and 5 units to the 8th stone.
     * Example 2:
     *
     * [0,1,2,3,4,8,9,11]
     *
     * Return false. There is no way to jump to the last stone as
     * the gap between the 5th and 6th stone is too large.
     */

    /*
    Solution 1:
    dp, assign a dp list with sets in it, each set represent all possible last steps reach to here
    loop the dp list, for each set, loop each possible steps, try to reach to step - 1 + stone, step + stone, step + 1 + stone
    see if there's proper stone after, if so, update related after dp set with this step

    Time: O(n^2)
    Space: O(n)
     */

    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return true;
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < stones.length; i++) {
            dp.add(new HashSet<>());
        }
        dp.get(0).add(0);
        int end = stones[stones.length - 1];
        /*
        Stop at dp.size - 1, due to if current step is 1 or 0, and loop to dp.size - 1, we don't have valid steps
        but due to we check if (pre + stones[i] == end || cur + stones[i] == end || next + stones[i] == end) return true;
        then the result will be fake true
         */
        for (int i = 0; i < dp.size() - 1; i++) {
            for (int num: dp.get(i)) {
                int pre = num - 1, cur = num, next = num + 1;
                if (pre + stones[i] == end || cur + stones[i] == end || next + stones[i] == end) return true;
                for (int j = i + 1; j < stones.length; j++) {
                    if (stones[j] == pre + stones[i]) dp.get(j).add(pre);
                    if (stones[j] == cur + stones[i]) dp.get(j).add(cur);
                    if (stones[j] == next + stones[i]) dp.get(j).add(next);
                }
            }
        }
        return false;
    }

    /*
    Solution 2:
    Similar to solution 1, use map <stone, set> instead of dp list, so that when we check if there's matched stone after
    we don't need the j loop

    Time: O(n)
    Space: O(n)
     */

    public boolean canCross2(int[] stones) {
        if (stones == null || stones.length == 0) return true;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        map.put(0, new HashSet<>());
        map.get(0).add(0);
        for (int i = 1; i < stones.length; i++) {
            map.put(stones[i], new HashSet<>() );
        }
        for (int i = 0; i < stones.length - 1; i++) {
            int stone = stones[i];
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (Math.abs(reach - stones[stones.length - 1]) <= 1) return true;
                if (step > 0 && map.containsKey(reach)) map.get(reach).add(step);
                if (step - 1 > 0 && map.containsKey(reach - 1)) map.get(reach - 1).add(step - 1);
                if (map.containsKey(reach + 1)) map.get(reach + 1).add(step + 1);
            }
        }
        return false;
    }

    /*
    Solution 3:
    similar to solution 2, but add step - 1, step, step + 1 to same set, then next time we just need use step is good
    e.g. current set is reachable by step 2, then we add 1, 2, 3 to this set, next time we just use current step
    but not step - 1, step, step + 1, actually we did already looped oldStep - 1, oldStep, oldStep + 1

    https://leetcode.com/problems/frog-jump/discuss/88824/Very-easy-to-understand-JAVA-solution-with-explanations
     */

}
