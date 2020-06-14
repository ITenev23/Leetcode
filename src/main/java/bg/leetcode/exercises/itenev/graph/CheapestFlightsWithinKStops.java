package bg.leetcode.exercises.itenev.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * <p>
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 * <p>
 * Example 1:
 * Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * <p>
 * Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 */
public class CheapestFlightsWithinKStops {

    private static class City implements Comparable<City> {
        int id;
        int costFromSource;
        int hopsFromSource;

        public City(int id, int distance, int hops) {
            this.id = id;
            this.costFromSource = distance;
            this.hopsFromSource = hops;
        }

        public int compareTo(City city) {
            return this.costFromSource - city.costFromSource;
        }

    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] graphFlights = new int[n][n];
        for (int[] flight : flights)
            graphFlights[flight[0]][flight[1]] = flight[2];

        int[] costs = new int[n];
        int[] hops = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        Arrays.fill(hops, Integer.MAX_VALUE);
        costs[src] = 0;
        hops[src] = 0;

        PriorityQueue<City> queue = new PriorityQueue<>();
        queue.offer(new City(src, 0, 0));

        while (!queue.isEmpty()) {
            City city = queue.poll();
            if (city.id == dst)
                return city.costFromSource;
            if (city.hopsFromSource == K + 1)
                continue;

            int[] nextLinks = graphFlights[city.id];

            for (int i = 0; i < n; i++) {
                if (nextLinks[i] != 0) {
                    int newCost = city.costFromSource + nextLinks[i];
                    int newHops = city.hopsFromSource + 1;
                    if (newCost < costs[i] && (city.hopsFromSource != K || i == dst)) {
                        queue.offer(new City(i, newCost, newHops));
                        costs[i] = newCost;
                    } else if (newHops < hops[i]) {
                        queue.offer(new City(i, newCost, newHops));
                        hops[i] = newHops;
                    }
                }
            }
        }

        return costs[dst] == Integer.MAX_VALUE ? -1 : costs[dst];
    }

    /*********************************************************************************/

    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[][] graph = new int[n][n];

        for(int[] f : flights)
            graph[f[0]][f[1]] = f[2];

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0, K});

        while(!pq.isEmpty()) {
            int[] head = pq.poll();
            int airport = head[0], price = head[1], stops = head[2];
            if(airport == dst)
                return price;
            if(stops >= 0) {
                for(int i = 0; i < graph[airport].length; i++) {
                    if(graph[airport][i] > 0) {
                        pq.offer(new int[]{i, price + graph[airport][i], stops - 1});
                    }
                }
            }
        }

        return -1;
    }
}
