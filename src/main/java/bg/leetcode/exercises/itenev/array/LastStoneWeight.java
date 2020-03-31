package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * We have a collection of rocks, each rock has a positive integer weight.
 * <p>
 * Each turn, we choose the two heaviest rocks and smash them together.
 * Suppose the stones have weights x and y with x <= y.
 * The result of this smash is:
 * <p>
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 * <p>
 * Input: [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of last stone.
 */
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones)
            maxHeap.add(stone);

        while (maxHeap.size() > 1) {
            int first = maxHeap.remove();
            int second = maxHeap.remove();
            if (first != second)
                maxHeap.add(first - second);
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.remove();
    }

    /****************************************************************************/

    public int lastStoneWeight2(int[] stones) {
        if (stones.length == 0)
            return 0;

        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones)
            maxHeap.add(stone);

        while (maxHeap.size() > 1)
            maxHeap.offer(maxHeap.poll() - maxHeap.poll());

        return maxHeap.poll();
    }

    /****************************************************************************/

    public int lastStoneWeight3(int[] stones) {
        if (stones.length == 1)
            return stones[0];

        Arrays.sort(stones);
        int last = stones.length - 1;
        int prev = stones.length - 2;

        while (stones[prev] == 0) {
            if (stones[prev] == stones[last]) {
                stones[prev] = 0;
                stones[last] = 0;
            } else {
                stones[last] -= stones[prev];
                stones[prev] = 0;
            }
            Arrays.sort(stones);
        }

        return stones[last];
    }

}
