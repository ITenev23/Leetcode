package bg.leetcode.exercises.itenev.array;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 * <p>
 * Input:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] += grid[i][j];
                if (i > 0 && j > 0) {
                    dp[i][j] += Math.min(dp[i - 1][j], dp[i][j - 1]);
                } else if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                } else if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    /******************************************************************/

    public int minPathSum2(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        return minPathSumHelper(grid, 0, 0, memo);
    }

    public int minPathSumHelper(int[][] grid, int row, int col, int[][] memo) {
        if (row == grid.length - 1 && col == grid[0].length - 1) return grid[row][col];
        if (memo[row][col] != 0) return memo[row][col];

        int rowInc = Integer.MAX_VALUE, colInc = Integer.MAX_VALUE;
        if (row < grid.length - 1) rowInc = minPathSumHelper(grid, row + 1, col, memo);
        if (col < grid[0].length - 1) colInc = minPathSumHelper(grid, row, col + 1, memo);
        memo[row][col] = Math.min(rowInc, colInc) + grid[row][col];
        return memo[row][col];
    }

    /******************************************************************/


    public int minPathSum3(int[][] grid) {
        if (grid.length == 0)
            return 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0)
                    continue;

                int leftSum = (j > 0) ? grid[i][j - 1] : Integer.MAX_VALUE;
                int topSum = (i > 0) ? grid[i - 1][j] : Integer.MAX_VALUE;

                grid[i][j] += Math.min(leftSum, topSum);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

}
