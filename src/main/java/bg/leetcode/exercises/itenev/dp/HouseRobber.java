package bg.leetcode.exercises.itenev.dp;

/**
 * Given a list of non-negative integers nums representing the amount of money hidden in each house,
 * determine the maximum amount of money you can rob in one night without triggering an alarm.
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber {

    int houseRobber(int[] nums) {
        int a = 0, b = 0;

        for (int m : nums) {
            b = Math.max(m + a, a = b);
        }

        return b;
    }

    /********************************************/

    public int rob(int[] num) {
        int currentHouse = 0;
        int max = 0;

        for (int value : num) {
            int temp = max + value;
            max = Math.max(max, currentHouse);
            currentHouse = temp;
        }

        return Math.max(currentHouse, max);
    }

    /********************************************/

    int houseRobberDP(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(nums[i - 1], nums[i] + nums[i - 2]);
        }

        return dp[n - 1];
    }

}
