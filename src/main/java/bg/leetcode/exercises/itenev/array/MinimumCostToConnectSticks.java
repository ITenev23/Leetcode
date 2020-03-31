package bg.leetcode.exercises.itenev.array;

import java.util.PriorityQueue;

/**
 * You can connect any two sticks of lengths x and y into one stick
 * by paying a cost of x + y.
 * You perform this action until there is one stick remaining.
 * Return min cost of connecting all the given sticks into one.
 *
 * Input: [2,4,3]
 * Output: 14
 *
 * Input: [1,8,3,5]
 * Output: 30
 */
public class MinimumCostToConnectSticks {

    public static int connectSticks(int[] sticks) {
        int cost = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int stick : sticks)
            minHeap.add(stick);

        while (minHeap.size() > 1) {
            int sum = minHeap.remove() + minHeap.remove();
            cost += sum;
            minHeap.add(sum);
        }

        return cost;
    }
}
