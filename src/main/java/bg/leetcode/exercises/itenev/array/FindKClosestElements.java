package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie,
 * the smaller elements are always preferred.
 * <p>
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * <p>
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 */
public class FindKClosestElements {

    /**
     * Assume we are taking arr[i] ~ arr[i + k -1].
     * We can binary research i
     * We compare the distance between x - arr[mid] and arr[mid + k] - x
     *
     * If x - arr[mid] > arr[mid + k] - x,
     * it means arr[mid + 1] ~ arr[mid + k] is better than arr[mid] ~ arr[mid + k - 1],
     * and we have mid smaller than the right i.
     * So assign left = mid + 1.
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;

        while (left < right) {
            int mid = (left + right) / 2;

            if (x - arr[mid] > arr[mid + k] - x)
                left = mid + 1;
            else
                right = mid;
        }
        return Arrays.stream(arr, left, left + k).boxed().collect(Collectors.toList());
    }
}
