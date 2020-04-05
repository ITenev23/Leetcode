package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 * <p>
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */
public class PartitionKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (k <= 0 || sum % k != 0)
            return false;

        int[] visited = new int[nums.length];
        return canPartition(nums, visited, 0, k, 0, 0, sum / k);
    }

    public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target) {
        if (k == 1)
            return true;

        if (cur_sum == target && cur_num > 0)
            return canPartition(nums, visited, 0, k - 1, 0, 0, target);

        for (int i = start_index; i < nums.length; i++) {
            if (visited[i] == 0) {
                visited[i] = 1;
                if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], cur_num++, target))
                    return true;
                visited[i] = 0;
            }
        }
        return false;
    }

    /***************************************************************************/

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        for (int n : nums)
            sum += n;

        if (sum % k != 0)
            return false;

        int[] used = new int[nums.length];
        Map<Integer, Boolean> dp = new HashMap<>();
        return backtracking(nums, used, dp, sum, sum / k, 0);
    }

    boolean backtracking(int[] nums, int[] used, Map<Integer, Boolean> dp, int sum, int shard, int currentSum) {
        if (currentSum == shard) {
            sum = sum - shard;
            currentSum = 0;
            if (sum == 0)
                return true;
        }
        if (currentSum > shard)
            return false;

        int hashcode = 0;
        for (int u : used) {
            hashcode = hashcode << 1;
            hashcode = hashcode | u;
        }
        if (dp.containsKey(hashcode))
            return dp.get(hashcode);


        for (int i = 0; i < nums.length; i++) {
            if (used[i] == 0) {
                used[i] = 1;
                boolean can = backtracking(nums, used, dp, sum, shard, currentSum + nums[i]);
                used[i] = 0;
                if (can) {
                    dp.put(hashcode, true);
                    return true;
                }
            }
        }
        dp.put(hashcode, false);
        return false;
    }

}
