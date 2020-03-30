package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array a of non-negative integers,
 * find the number of distinct pairs of integers for which the sum is equal to k.
 * Input: [2,3,6,2,8], k = 8
 * Output: should be 1 as [2,6] is the only distinct unique pair.
 */
public class DistinctPairsBySum {

    public static void test() {
        int k = 8;
        int[] nums = {2, 3, 6, 2, 8};
        System.out.println(solve(nums, k));
    }

    private static int solve(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        int res = 0;
        boolean isHalf = false;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                if (!isHalf && k % 2 == 0 && nums[i] == k / 2) {
                    res++;
                    isHalf = !isHalf;
                }
                continue;
            }
            if (set.contains(k - nums[i]))
                res++;
            set.add(nums[i]);
        }
        return res;
    }
}
