package best_first_search;

import java.util.*;

public class CheapestFlightsWithinKStops {

    //Difficulty: Medium
    //TAG: Airbnb
    //TAG: breadth first search
    //TAG: Best first search

    /**
     * 787. Cheapest Flights Within K Stops
     * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.
     *
     * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
     *
     * Example 1:
     * Input:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * Output: 200
     * Explanation:
     * The graph looks like this:
     *
     *
     * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
     * Example 2:
     * Input:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 0
     * Output: 500
     * Explanation:
     * The graph looks like this:
     *
     *
     * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
     * Note:
     *
     * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
     * The size of flights will be in range [0, n * (n - 1) / 2].
     * The format of each flight will be (src, dst, price).
     * The price of each flight will be in the range [1, 10000].
     * k is in the range of [0, n - 1].
     * There will not be any duplicated flights or self cycles.
     */

    /*
    Solution 1:
    Breath first search
     */

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Queue<int[]> queue = new LinkedList<>();
        HashMap<Integer, List<int[]>> connections = new HashMap<>();
        for (int[] flight: flights) {
            if (flight[0] == src) queue.offer(flight);
            if (!connections.containsKey(flight[0])) {
                connections.put(flight[0], new ArrayList<>());
            }
            connections.get(flight[0]).add(flight);
        }
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty() && K >= 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                if (poll[1] == dst) min = Math.min(min, poll[2]);
                else if (connections.containsKey(poll[1])) {
                    for (int[] nextFlight: connections.get(poll[1])) {
                        queue.offer(new int[]{poll[0], nextFlight[1], poll[2] + nextFlight[2]});
                    }
                }
            }
            K--;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /*
    Solution 2:
    Best first search
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        PriorityQueue<FlightObj> priorityQueue = new PriorityQueue<>(new Comparator<FlightObj>() {
            @Override
            public int compare(FlightObj o1, FlightObj o2) {
                return o1.price - o2.price;
            }
        });
        HashMap<Integer, List<int[]>> connections = new HashMap<>();
        for (int[] flight: flights) {
            if (flight[0] == src) priorityQueue.offer(new FlightObj(flight[1], flight[2], K));
            if (!connections.containsKey(flight[0])) {
                connections.put(flight[0], new ArrayList<>());
            }
            connections.get(flight[0]).add(flight);
        }
        while (!priorityQueue.isEmpty()) {
            FlightObj poll = priorityQueue.poll();
            if (poll.dest == dst) return poll.price;
            if (poll.K > 0 && connections.containsKey(poll.dest)) {
                for (int[] nextFlight: connections.get(poll.dest)) {
                    priorityQueue.offer(new FlightObj(nextFlight[1], poll.price + nextFlight[2], poll.K - 1));
                }
            }
        }
        return -1;
    }

    class FlightObj {
        int dest;
        int price;
        int K;
        FlightObj(int dest, int price, int K) {
            this.dest = dest;
            this.price = price;
            this.K = K;
        }
    }

}
