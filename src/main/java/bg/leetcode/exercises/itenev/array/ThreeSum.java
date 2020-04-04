package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int current = nums[i];

            //Since the array is sorted, if first number is bigger than 0,
            // it is impossible to have a sum of 0.
            if (current > 0)
                break;
            else if (i == 0 || current != nums[i - 1]) {
                int left = i + 1;
                int right = nums.length - 1;
                int sum = -current;

                while (left < right) {
                    if (nums[left] + nums[right] == sum) {
                        res.add(Arrays.asList(current, nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1])
                            left++;
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < sum)
                        left++;
                    else
                        right--;
                }
            }
        }
        return res;
    }

}
