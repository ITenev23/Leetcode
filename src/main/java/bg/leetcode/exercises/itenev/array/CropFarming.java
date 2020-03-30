package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given cost and profit array of farming pattern.
 * Maximize profit for m months.
 * For capital x only crops with cost below or equal to x can be farmed.
 * Each month you can farm only one crop. Once a crop is farmed it can't be used again.
 * After each crop the total capital becomes x + profit.
 * <p>
 * Input: cost = [1, 8, 9], profit = [2, 3, 8], capital = 7, m = 2
 * Output: 17
 * Explanation: In the beginning only crop with cost 1 can be farmed. After first month capital = 7 + 2 = 9.
 * Next crop 9 should be farmed as profit is max for that to give 9 + 8 = 17. So max profit after 2 months will be 17.
 */
public class CropFarming {

    public static void test() {
        int[] costs = {1, 8, 9, 9};
        int[] profits = {2, 8, 3, 4};

        int m = 3;
        int capital = 7;

        System.out.println(findMax(costs, profits, m, capital));
    }

    static class Crop {
        int cost;
        int profit;

        Crop(int cost, int profit) {
            this.cost = cost;
            this.profit = profit;
        }

    }

    static class Comp implements Comparator<Crop> {
        public int compare(Crop a, Crop b) {
            if (a.cost < b.cost) {
                return -1;
            } else if (a.cost > b.cost) {
                return 1;
            }
            return 0;
        }

    }

    static class ProfitComp implements Comparator<Crop> {
        public int compare(Crop a, Crop b) {
            if (a.profit < b.profit) {
                return 1;
            } else if (a.profit > b.profit) {
                return -1;
            }
            return 0;
        }

    }

    private static int findMax(int[] costs, int[] profits, int m, int capital) {
        Crop[] crops = new Crop[costs.length];
        for (int i = 0; i < costs.length; ++i) {
            crops[i] = new Crop(costs[i], profits[i]);
        }

        Arrays.sort(crops, new Comp());

        int start = 0;
        int cap = capital;
        PriorityQueue<Crop> max_q = new PriorityQueue<>(10, new ProfitComp());

        for (int i = 0; i < m; ++i) {
            // Populate the heap
            while (start < costs.length) {
                if (crops[start].cost > cap) {
                    break;
                }
                max_q.offer(crops[start]);
                ++start;
            }

            int maxP = 0;
            if (max_q.size() > 0) {
                maxP = max_q.poll().profit;
            }
            if (maxP <= 0) {
                break;
            }
            cap += maxP;
        }
        return cap;
    }

}
