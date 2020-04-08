package bg.leetcode.exercises.itenev.dp;

/**
 * Given an integer array arr and an integer k, modify the array by repeating it k times.
 * For example, if arr = [1, 2] and k = 3 then the modified array will be [1, 2, 1, 2, 1, 2].
 * Return the maximum sub-array sum in the modified array.
 * Note that the length of the sub-array can be 0 and its sum in that case is 0.
 * As the answer can be very large, return the answer modulo 10^9 + 7.
 * <p>
 * Input: arr = [1,2], k = 3
 * Output: 9
 * <p>
 * Input: arr = [1,-2,1], k = 5
 * Output: 2
 * <p>
 * Input: arr = [-1,-2], k = 7
 * Output: 0
 */
public class KConcatenationMaxSum {

    private final int MOD = 1000000007;

    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        int currentSum = 0;
        int max = 0;
        for (int value : arr) {
            max = Math.max(max + value, 0);
            currentSum = Math.max(currentSum, max);
        }

        if (k < 2)
            return currentSum;

        int leftSum = arr[0];
        int rightSum = arr[n - 1];
        int lMax = Math.max(0, arr[0]);
        int rMax = Math.max(0, arr[n - 1]);

        for (int i = 1; i < n; i++) {
            leftSum += arr[i];
            lMax = Math.max(lMax, leftSum);
        }

        for (int i = n - 2; i >= 0; i--) {
            rightSum += arr[i];
            rMax = Math.max(rMax, rightSum);
        }

        int headTailMax = lMax + rMax;

        if (leftSum < 0)
            return Math.max(currentSum, headTailMax);
        else
            return Math.max(currentSum, (int) (headTailMax + ((k - 2) * (long) leftSum) % MOD));
    }

    /******************************************************************************/

    private final int MOD2 = (int) Math.pow(10, 9) + 7;

    public int kConcatenationMaxSum2(int[] ar, int k) {
        long kadanes = kadanesAlgo(ar);

        if (k == 1)
            return (int) kadanes;

        long prefixSum = prefixSum(ar);
        long suffixSum = suffixSum(ar);
        long sum = 0;

        for (int i1 : ar)
            sum += i1;

        if (sum > 0) {
            return (int) (Math.max(((sum * (k - 2)) % MOD2 + suffixSum % MOD2 + prefixSum % MOD2) % MOD2, kadanes % MOD2));
        } else {
            return (int) (Math.max((prefixSum % MOD2 + suffixSum % MOD2) % MOD2, kadanes % MOD2));
        }

    }

    public long kadanesAlgo(int[] ar) {
        long currentSum = 0;
        long maxSum = Integer.MIN_VALUE;

        for (int value : ar) {
            currentSum = currentSum > 0 ? (currentSum + value) % MOD2 : value;
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum < 0 ? 0 : maxSum % MOD2;
    }


    public long prefixSum(int[] ar) {
        long currentSum = 0;
        long maxSum = Integer.MIN_VALUE;

        for (int value : ar) {
            currentSum = (currentSum + value) % MOD2;
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public long suffixSum(int[] ar) {
        long currentSum = 0;
        long maxSum = Integer.MIN_VALUE;
        for (int i = ar.length - 1; i >= 0; i--) {
            currentSum = (currentSum + ar[i]) % MOD2;
            maxSum = Math.max(currentSum, maxSum);
        }

        return maxSum;
    }
}
