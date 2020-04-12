package bg.leetcode.exercises.itenev.array;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Given an array nums, there is a sliding window of size k
 * which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 * Return the max sliding window.
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMax {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = nums[q.peek()];
            }
        }
        return r;
    }

    /********************************************************/

    public static int[] slidingWindowMax(int[] nums, int k) {
        int[] max_left = new int[nums.length];
        int[] max_right = new int[nums.length];

        int size = nums.length - 1;
        max_left[0] = nums[0];
        max_right[size] = nums[size];

        for (int i = 1; i < nums.length; i++) {
            max_left[i] = (i % k == 0) ? nums[i] : Math.max(max_left[i - 1], nums[i]);

            final int j = size - i;
            max_right[j] = (j % k == 0) ? nums[j] : Math.max(max_right[j + 1], nums[j]);
        }

        int[] sliding_max = new int[nums.length - k + 1];
        for (int i = 0, j = 0; i + k <= nums.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + k - 1]);
        }

        return sliding_max;
    }

    /*****************************************************************/

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] result = new int[n - k + 1];

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                (i1, i2) -> (nums[i2] - nums[i1])
        );

        for (int i = 0; i < n; ++i) {
            int start = i - k;
            if (start >= 0) {
                maxHeap.remove(start);
            }
            maxHeap.offer(i);
            if (maxHeap.size() == k) {
                result[i - k + 1] = nums[maxHeap.peek()];
            }
        }
        return result;
    }
}
