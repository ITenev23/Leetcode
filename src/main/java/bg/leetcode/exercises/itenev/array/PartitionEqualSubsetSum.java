package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Input: [1, 5, 11, 5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * <p>
 * Input: [1, 2, 3, 5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if (total % 2 != 0)
            return false;

        return dp(nums, 0, 0, total, new HashMap<String, Boolean>());
    }

    private boolean dp(int[] nums, int index, int sum, int total, Map<String, Boolean> state) {
        String current = index + "" + sum;
        if (state.containsKey(current)) {
            return state.get(current);
        }
        if (sum * 2 == total)
            return true;

        if (sum > total / 2 || index >= nums.length)
            return false;

        boolean found = dp(nums, index + 1, sum, total, state) ||
                dp(nums, index + 1, sum + nums[index], total, state);

        state.put(current, found);
        return found;
    }

    /***************************************************************************/


    public boolean canPartition2(int[] nums) {
        int total = Arrays.stream(nums).sum();
        if(total%2 != 0) return false; // if the array sum is not even, we cannot partition it into 2 equal subsets
        int max = total/2; // the maximum for a subset is total/2
        int[][] results = new int[nums.length][max]; // integer matrix to store the results, so we don't have to compute it more than one time
        return isPartitionable(max,0,0,nums,results);
    }

    public boolean isPartitionable(int max,int curr, int index, int[] nums, int[][] results) {
        if(curr>max || index>nums.length-1) return false; // if we passed the max, or we reached the end of the array, return false
        if(curr==max) return true; // if we reached the goal (total/2) we found a possible partition
        if(results[index][curr]==1) return true; // if we already computed teh result for the index i with the sum current, we retrieve this result (1 for true)
        if(results[index][curr]==2) return false; // if we already computed teh result for the index i with the sum current, we retrieve this result (2 for false)
        boolean res = isPartitionable(max, curr+nums[index], index+1, nums, results) || isPartitionable(max, curr, index+1, nums, results); // else try to find the equal partiion, taking this element, or not taking it
        results[index][curr] = res ? 1 : 2; // store the result for this index and this current sum, to use it in dynamic programming
        return res;
    }
    
}
