package bg.leetcode.exercises.itenev.array;

/**
 * Given an array of integers, find the maximum possible sum you can get from one of its contiguous subarrays.
 * The subarray from which this sum comes must contain at least 1 element.
 * <p>
 * For inputArray = [-2, 2, 5, -11, 6], the output should be
 * arrayMaxConsecutiveSum2(inputArray) = 7.
 * The contiguous subarray that gives the maximum possible sum is [2, 5], with a sum of 7.
 */
public class ArrayMaxConsecutiveSum {

    public int maxConsecutive(int[] arr) {
        if (arr.length == 1)
            return arr[0];

        int current = 0;
        int max = Integer.MIN_VALUE;

        for (int val : arr) {
            current = Math.max(current, current + val);
            max = Math.max(max, current);
        }

        return max;
    }

}
