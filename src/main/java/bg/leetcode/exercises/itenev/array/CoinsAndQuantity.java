package bg.leetcode.exercises.itenev.array;

import java.util.HashSet;
import java.util.Set;

/**
 * You have a collection of coins, and you know the values of the coins and the quantity of each type of coin in it.
 * You want to know how many distinct sums you can make from non-empty groupings of these coins.
 * Input: coins = [10, 50, 100] , quantity = [1, 2, 1]
 * Output: 9.
 * <p>
 * Here are all the possible sums:
 * 50 = 50;
 * 10 + 50 = 60;
 * 50 + 100 = 150;
 * 10 + 50 + 100 = 160;
 * 50 + 50 = 100;
 * 10 + 50 + 50 = 110;
 * 50 + 50 + 100 = 200;
 * 10 + 50 + 50 + 100 = 210;
 * 10 = 10;
 * 100 = 100;
 * 10 + 100 = 110.
 * As you can see, there are 9 distinct sums that can be created from non-empty groupings of your coins.
 */
public class CoinsAndQuantity {

    int possibleSums(int[] coins, int[] quantity) {
        HashSet<Integer> sums = new HashSet<>();
        solve(coins, quantity, 0, 0, sums);

        return sums.size() - 1;
    }

    void solve(int[] coins, int[] quantity, int idx, int sum, Set<Integer> sums) {
        if (idx == coins.length) {
            sums.add(sum);
            return;
        }

        for (int i = 0; i <= quantity[idx]; i++)
            solve(coins, quantity, idx + 1, sum + coins[idx] * i, sums);
    }

    /********************************************************************************/

    int possibleSums2(int[] coins, int[] quantity) {
        Set<Integer> result = new HashSet<>();
        result.add(0);

        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            Set<Integer> sets = new HashSet<>();

            for (Integer sum : result) {
                for (int j = 1; j <= quantity[i]; j++) {
                    sets.add(sum + coin * j);
                }
            }
            result.addAll(sets);
        }

        return result.size() - 1;
    }


}
