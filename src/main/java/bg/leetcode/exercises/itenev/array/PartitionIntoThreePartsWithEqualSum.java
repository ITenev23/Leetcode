package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;

/**
 * Given an array A of integers, return true if and only
 * if we can partition the array into three non-empty parts with equal sums.
 * Formally, we can partition the array if we can find indexes i+1 < j with
 * (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1])
 * <p>
 * Input: A = [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * <p>
 * Input: A = [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 * <p>
 * Input: A = [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 */
public class PartitionIntoThreePartsWithEqualSum {

    public boolean canThreePartsEqualSum(int[] A) {
        int sum = Arrays.stream(A).sum();
        if (sum % 3 != 0)
            return false;

        int shard = sum / 3;
        int currentSum = 0;
        int partitions = 0;

        for (int value : A) {
            currentSum += value;

            if (currentSum == shard) {
                partitions++;
                currentSum = 0;
            }
        }
        return partitions == 3;
    }

    /******************************************************************/

    public boolean canThreePartsEqualSum2(int[] A) {
        int sum = 0;
        for (int value : A)
            sum += value;

        if (sum % 3 != 0)
            return false;

        int shard = sum / 3;
        int left = 0;
        int right = A.length - 1;
        int leftSum = A[left++];
        int rightSum = A[right--];

        while (left < right) {
            if (leftSum != shard)
                leftSum += A[left++];

            if (left < right && rightSum != shard)
                rightSum += A[right--];

            if (left <= right && leftSum == shard && rightSum == shard)
                return true;
        }
        return false;
    }
}
