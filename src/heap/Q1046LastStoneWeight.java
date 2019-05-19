package heap;

import java.util.PriorityQueue;

public class Q1046LastStoneWeight {

    //Difficulty: easy
    //TAG: priority queue

    /**
     * 1046. Last Stone Weight
     * We have a collection of rocks, each rock has a positive integer weight.
     *
     * Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
     *
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
     *
     *
     *
     * Example 1:
     *
     * Input: [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
     *
     *
     * Note:
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     */

    /*
    Solution:

    1. add all to max heap
    2. keep compare top two and follow the rule

    Time: O(nlogn)
    Space: O(n)
     */

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer o1, Integer o2) -> o2 - o1);
        for (int i = 0; i < stones.length; i++) {
            pq.offer(stones[i]);
        }
        while (!pq.isEmpty()) {
            int poll1 = pq.poll();
            if (pq.isEmpty()) return poll1;
            if (poll1 == pq.peek()) pq.poll();
            else pq.offer(poll1 - pq.poll());
        }
        return 0;
    }

}
