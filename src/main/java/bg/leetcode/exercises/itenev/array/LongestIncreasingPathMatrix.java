package bg.leetcode.exercises.itenev.array;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * <p>
 * Input: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * <p>
 * Input: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int[][] memo = new int[matrix.length][matrix[0].length];
        int result = 0;

        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix[row].length; col++)
                result = Math.max(result, findLongest(matrix, row, col, Integer.MIN_VALUE, memo));


        return result;
    }

    public int findLongest(int[][] matrix, int row, int col, int pre, int[][] memo) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length || matrix[row][col] <= pre)
            return 0;

        if (memo[row][col] > 0)
            return memo[row][col];

        int curr = matrix[row][col];
        int tempMax = 0;
        tempMax = Math.max(tempMax, findLongest(matrix, row - 1, col, curr, memo));
        tempMax = Math.max(tempMax, findLongest(matrix, row + 1, col, curr, memo));
        tempMax = Math.max(tempMax, findLongest(matrix, row, col - 1, curr, memo));
        tempMax = Math.max(tempMax, findLongest(matrix, row, col + 1, curr, memo));
        memo[row][col] = tempMax + 1;

        return memo[row][col];
    }

    /**************************************************************************/


    public int longestIncreasingPath2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int[] xShift = {0, -1, 1, 0};
        int[] yShift = {1, 0, 0, -1};

        int[][] memo = new int[matrix.length][matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (memo[i][j] == 0)
                    dfs(matrix, memo, i, j, xShift, yShift);

                result = Math.max(result, memo[i][j]);
            }
        }
        return result;
    }

    private void dfs(int[][] matrix, int[][] longestLength, int x, int y, int[] xShift, int[] yShift) {
        longestLength[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int newX = x + xShift[i];
            int newY = y + yShift[i];

            if (newX < matrix.length && newX >= 0
                    && newY < matrix[0].length && newY >= 0 && matrix[x][y] < matrix[newX][newY]) {
                if (longestLength[newX][newY] == 0) {
                    dfs(matrix, longestLength, newX, newY, xShift, yShift);
                }
                longestLength[x][y] = Math.max(longestLength[x][y], 1 + longestLength[newX][newY]);
            }
        }
    }

}
