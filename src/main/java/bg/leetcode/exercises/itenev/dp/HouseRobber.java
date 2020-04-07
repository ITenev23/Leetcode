package bg.leetcode.exercises.itenev.dp;

/**
 * Given a list of non-negative integers nums representing the amount of money hidden in each house,
 * determine the maximum amount of money you can rob in one night without triggering an alarm.
 * <p>
 * For nums = [4, 7, 1], the output should be
 * houseRobber(nums) = 7.
 * <p>
 * For nums = [4, 6, 3], the output should be
 * houseRobber(nums) = 7.
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
