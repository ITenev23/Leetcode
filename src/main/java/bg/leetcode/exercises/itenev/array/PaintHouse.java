package bg.leetcode.exercises.itenev.array;

/**
 * [red,blue,green]
 * <p>
 * Input: [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Exp: Paint house 0 into blue, paint 1 in green, 2 in blue.
 * Min cost: 2 + 5 + 3 = 10
 */
public class PaintHouse {

    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0)
            return 0;

        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        return Math.min(
                Math.min(
                        costs[costs.length - 1][0],
                        costs[costs.length - 1][1]
                ),
                costs[costs.length - 1][2]);
    }

}
