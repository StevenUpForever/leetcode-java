package array;

import java.util.*;

public class TopKFrequentElements {

    /**
     * 347. Top K Frequent Elements
     * Given a non-empty array of integers, return the k most frequent elements.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     * Note:
     *
     * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
     */

    /*
    Solution:
    1. count the frequency
    2. put all frequency entry in priority queue, max heap
    3. pop k times, add keys to list

    Time: O(n + nlogn + klogk)
    Space: O(n)
     */

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || k < 1) return list;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pq.offer(entry);
        }
        while (k-- > 0 && !pq.isEmpty()) {
            list.add(pq.poll().getKey());
        }
        return list;
    }



}
