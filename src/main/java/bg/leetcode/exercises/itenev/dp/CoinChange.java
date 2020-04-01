package bg.leetcode.exercises.itenev.dp;

import java.util.Arrays;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * Note: You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i)
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                else
                    break;
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /***********************************************************************/

    /**
     * The idea of the algorithm is to build the solution of the problem from top to bottom.
     * It applies the idea described above. It use backtracking and cut the partial solutions in the recursive tree,
     * which doesn't lead to a viable solution.
     * Тhis happens when we try to make a change of a coin with a value greater than the amount SS.
     * To improve time complexity we should store the solutions of the already calculated subproblems in a table.
     */
    public int coinChangeTopDown(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }

    /***********************************************************************/


    public int coinChangeBottomUp(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
