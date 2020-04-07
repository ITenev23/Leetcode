package bg.leetcode.exercises.itenev.dp;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time
 * (i.e., you must sell the stock before you buy again).
 * <p>
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * <p>
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 * engaging multiple transactions at the same time. You must sell before buying again.
 */
public class BestTimeToBuySellStockIII {

    /**
     * sell2: Final profit.
     * buy2: Best profit so far, if you buy the stock not after Day i (2nd transaction).
     * sell1: Best profit so far, if you sell the stock not after Day i (1st transaction).
     * buy1: Minimum price to buy the stock.
     */
    public int maxProfit(int[] prices) {
        int sell1 = 0;
        int sell2 = 0;
        int buy1 = Integer.MIN_VALUE;
        int buy2 = Integer.MIN_VALUE;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }

        return sell2;
    }

    /*************************************************************/

    public int maxProfit2(int[] prices) {
        if (prices.length == 0)
            return 0;

        int trans = 2;
        int[][] dp = new int[trans + 1][prices.length];

        for (int i = 1; i <= trans; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j < prices.length; j++) {
                min = Math.min(min, prices[j - 1] - dp[i - 1][j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] - min);
            }
        }
        return dp[trans][prices.length - 1];
    }

    /*************************************************************/

    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int profit = 0;

        int[] left = new int[n];
        int min = prices[0];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        int[] right = new int[n];
        int max = prices[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);

            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

}
