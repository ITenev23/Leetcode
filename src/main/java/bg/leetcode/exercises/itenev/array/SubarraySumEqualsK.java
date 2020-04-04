package bg.leetcode.exercises.itenev.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k,
 * you need to find the total number of continuous subarrays whose sum equals to k.
 * <p>
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 */
public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            sum += num;

            if (map.containsKey(sum - k))
                count += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * Use Dynamic Programming. For each element in array, save the sum from beginning of array until that element.
     *          key: sum
     *          value: number of contiguous arrays, starting at beginning of array, that sum to "key: sum"
     * Current sum gives us SUM[0...j].
     * So subtraction gives us SUM[0...j] - SUM[0...i] = SUM[i...j].
     * Each SUM[i...j] == k gives us 1 solution.
     */
    public int subarraySumMerge(int[] nums, int k) {
        HashMap<Integer, Integer> savedSum = new HashMap<>();
        savedSum.put(0, 1);
        int sum = 0;
        int result = 0;
        for (int num : nums) {
            sum += num;
            result += savedSum.getOrDefault(sum - k, 0);
            savedSum.merge(sum, 1, Integer::sum);
        }
        return result;
    }

    /**************************************************/

    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;

            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
                else if (sum > k)
                    break;
            }
        }
        return count;
    }

    /**************************************************/

    /**
     * Instead of determining the sum of elements every time for every new subarray considered,
     * we can make use of a cumulative sum array , sum.
     * Then, in order to calculate the sum of elements lying between two indices,
     * we can subtract the cumulative sum corresponding to the two indices to obtain the sum directly,
     * instead of iterating over the subarray to obtain the sum.
     */
    public int subarraySum3(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }

}
