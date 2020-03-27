package bg.leetcode.exercises.itenev.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 */
public class KthLargestElementArray {

    public int findKthLargestWith(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : nums) {
            minHeap.add(i);
            if (minHeap.size() > k)
                minHeap.remove();
        }

        return minHeap.remove();
    }

    public int findKthLargestWithSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

}
