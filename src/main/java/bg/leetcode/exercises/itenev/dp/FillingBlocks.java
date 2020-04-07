package bg.leetcode.exercises.itenev.dp;

/**
 * You have a block with the dimensions 4 × n.
 * Find the number of different ways you can fill this block with smaller blocks that have the dimensions 1 × 2.
 * You are allowed to rotate the smaller blocks.
 *
 * For n = 1, the output should be
 * fillingBlocks(n) = 1.
 *
 * There is only one possible way to arrange the smaller 1 × 2 blocks inside the 4 × 1 block.
 * For n = 4, the output should be
 * fillingBlocks(n) = 36.
 *
 */
public class FillingBlocks {

    int fillingBlocks(int n) {
        switch (n) {
            case 0:
            case 1: return 1;
            case 2: return 5;
            case 3: return 11;
            case 4: return 36;
        }

        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 5;
        dp[3] = 11;
        dp[4] = 36;

        for (int i = 5; i <= n; i++) {
            dp[i] = dp[i - 1] + (dp[i - 2] * 5) + dp[i - 3] - dp[i -4];
        }

        return dp[n];
    }

    /****************************************/

    int fillingBlocks2(int n) {
        if(n == 0)
            return 0;

        int[] dp = new int[n+1];
        dp[0] = 1;

        if(n >= 1)
            dp[1] = 1;
        if(n >= 2)
            dp[2] = 5;
        if(n >= 3)
            dp[3] = 11;

        for(int i = 4; i <= n; i++)
            dp[i] = dp[i-1] + dp[i-2]*5 + dp[i-3]-dp[i-4];

        return dp[n];
    }

}
