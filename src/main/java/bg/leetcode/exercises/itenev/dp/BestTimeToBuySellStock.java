package bg.leetcode.exercises.itenev.dp;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 */
public class BestTimeToBuySellStock {

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int price : prices) {
            if (price < min) min = price;
            else max = Math.max(max, price - min);
        }

        return max;
    }

    public static int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;

        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit)
                    maxprofit = profit;
            }
        }
        return maxprofit;
    }

    public static int maxProfit3(int[] prices) {
        if (prices.length == 0) return 0;

        int maxProfit = 0;
        int minPrice = prices[0];

        for (int currPrice : prices) {
            maxProfit = Math.max(maxProfit, currPrice - minPrice);
            minPrice = Math.min(minPrice, currPrice);
        }

        return maxProfit;
    }
}
