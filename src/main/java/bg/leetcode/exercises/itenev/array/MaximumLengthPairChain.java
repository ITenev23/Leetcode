package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given n pairs of numbers.
 * In every pair, the first number is always smaller than the second number.
 * <p>
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c.
 * Chain of pairs can be formed in this fashion.
 * <p>
 * Given a set of pairs, find the length longest chain which can be formed.
 * You needn't use up all the given pairs. You can select pairs in any order.
 * <p>
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 */
public class MaximumLengthPairChain {

    /**
     * Consider the pairs in increasing order of their second coordinate.
     * We'll try to add them to our chain.
     * If we can, by the above argument we know that it is correct to do so.
     */
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int cur = Integer.MIN_VALUE;
        int longestChain = 0;

        for (int[] pair : pairs)
            if (cur < pair[0]) {
                cur = pair[1];
                longestChain++;
            }

        return longestChain;
    }

    /**********************************************************************************/

    /**
     * Sort the pairs by first coordinate, and let dp[i] be the length of the longest chain ending at pairs[i].
     * When i < j and pairs[i][1] < pairs[j][0], we can extend the chain,
     * and so we have the candidate answer dp[j] = max(dp[j], dp[i] + 1).
     */
    public int findLongestChainDP(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int N = pairs.length;
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int j = 1; j < N; ++j) {
            for (int i = 0; i < j; ++i) {
                if (pairs[i][1] < pairs[j][0])
                    dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;
        for (int x: dp) if (x > ans) ans = x;
        return ans;
    }

}
