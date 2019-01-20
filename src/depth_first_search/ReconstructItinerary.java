package depth_first_search;

import java.util.*;

public class ReconstructItinerary {

    //Difficulty: Medium
    //TAG: Snap
    //TAG: dfs

    /**
     * 332. Reconstruct Itinerary
     * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
     *
     * Note:
     *
     * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
     * All airports are represented by three capital letters (IATA code).
     * You may assume all tickets form at least one valid itinerary.
     * Example 1:
     *
     * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * Example 2:
     *
     * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
     *              But it is larger in lexical order.
     */

    /*
    Solution:
    try to connect smallest lexical airport first, then we need a map which key is depart string, value
    is ascending array of dest (if sort manually), or priority queue

    requirement here is all Itinerary need be visited once

    dfs
    if add to list first then dfs, don't ensure that it could iterative all Itinerary, so:
    add to list after dfs, which means ensure that ensure that no connections after max lexical string first
    then we add first in list

    Time: O(nlogn if one key in map + n total steps in dfs) = O(nlogn + n)
    Space: O(n + n) = O(n)
     */

    private List<String> list = new LinkedList<>();
    public List<String> findItinerary(String[][] tickets) {
        if (tickets == null) return list;
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] itinerary: tickets) {
            if (!map.containsKey(itinerary[0])) {
                map.put(itinerary[0], new PriorityQueue<>());
            }
            map.get(itinerary[0]).offer(itinerary[1]);
        }
        findItineraryHelper("JFK", map);
        return list;
    }

    private void findItineraryHelper(String depart, Map<String, PriorityQueue<String>> map) {
        PriorityQueue<String> pq = map.get(depart);
        while (pq != null && !pq.isEmpty()) {
            findItineraryHelper(pq.poll(), map);
        }
        /*
        Key point is here, due to pq that from smallest lexical to largest
        add at last means we can confirm that the string add there is from the largest depart, and already looped all
        itinerary, the add to first place of list when track back to pre levels
         */
        list.add(0, depart);
    }

}



