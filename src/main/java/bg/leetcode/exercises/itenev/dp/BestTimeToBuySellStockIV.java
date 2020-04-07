package bg.leetcode.exercises.itenev.dp;

import java.util.Arrays;

/**
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * <p>
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * <p>
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 */
public class BestTimeToBuySellStockIV {

    /**
     * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
     * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
     * = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
     * dp[0, j] = 0; 0 transactions makes 0 profit
     * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
     */
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n <= 1)
            return 0;

        //if k >= n/2, then you can make maximum number of transactions.
        if (k >= n / 2) {
            int maxPro = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    maxPro += prices[i] - prices[i - 1];
            }
            return maxPro;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int localMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }


    /**********************************************************************/

    public int maxProfit2(int k, int[] prices) {
        int transactions = Math.min(k, prices.length / 2) + 1;

        int[] sell = new int[transactions];
        int[] hold = new int[transactions];
        Arrays.fill(hold, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int j = transactions - 1; j >= 1; j--) {
                sell[j] = Math.max(sell[j], hold[j] + price);
                hold[j] = Math.max(hold[j], sell[j - 1] - price);
            }
        }

        return sell[transactions - 1];
    }

    /**********************************************************************/

    public int maxProfit3(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k < 1) {
            return 0;
        } else if (k >= (prices.length + 1) / 2) {
            // k reach max transactions we can make, use stock problem ||
            return maxTransaction(prices);
        }
        // 2 solutions extended from stock problem |||
        return constantSpaceDP(prices, k);
        //return nSpaceDP(prices, k);
    }

    private int maxTransaction(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(0, prices[i] - prices[i - 1]);
        }
        return res;
    }

    /**
     * M[i][j]: max profit of i-th transaction from day 0 to day j
     * M[i][j] = max(M[i][j - 1], max(M[i - 1][k] + p[j] - p[k])), k from 0 to j - 1
     * = max(M[i][j - 1], p[j] + max(M[i - 1][k] - p[k]))
     * = max(M[i][j - 1], p[j] + tmp);
     * tmp = Math.max(tmp, M[i - 1][j] - p[j]);
     * optimization: space O(m*n) -> O(n)
     */
    private int constantSpaceDP(int[] p, int k) {
        int[] M = new int[p.length];
        for (int i = 1; i <= k; i++) {
            int tmp = M[0] - p[0], top;
            for (int j = 1; j < p.length; j++) {
                top = M[j];
                M[j] = Math.max(M[j - 1], tmp + p[j]);
                tmp = Math.max(tmp, top - p[j]);
            }
        }
        return M[p.length - 1];
    }
}
