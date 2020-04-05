package bg.leetcode.exercises.itenev.array;

/**
 * Given an integer array A, you partition the array into (contiguous) subarrays of length at most K.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 * Return the largest sum of the given array after partitioning.
 * <p>
 * Input: A = [1,15,7,9,2,5,10], K = 3
 * Output: 84
 * Explanation: A becomes [15,15,15,9,10,10,10]
 */
public class PartitionForMaxSum {

    public int maxSumAfterPartitioning(int[] A, int K) {
        int N = A.length;
        int[] dp = new int[N];

        for (int i = 0; i < N; ++i) {
            int max = 0;
            for (int k = 1; k <= K && i - k + 1 >= 0; ++k) {
                max = Math.max(max, A[i - k + 1]);
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + max * k);
            }
        }
        return dp[N - 1];
    }

    /*****************************************************/

    public int maxSumAfterPartitioning2(int[] A, int K) {
        int[] dp = new int[A.length];
        dp[0] = A[0];

        for (int i = 1; i < A.length; i++) {
            int sum = A[i] + dp[i - 1];
            int value = A[i];

            for (int j = i - 1; j >= 0 && j > i - K; j--) {
                value = Math.max(value, A[j]);
                if (j == 0)
                    sum = Math.max(sum, value * (i - j + 1));
                else
                    sum = Math.max(sum, value * (i - j + 1) + dp[j - 1]);
            }
            dp[i] = sum;
        }

        return dp[A.length - 1];
    }

}
