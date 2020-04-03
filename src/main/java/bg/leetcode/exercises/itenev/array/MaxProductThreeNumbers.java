package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 * <p>
 * Input: [1,2,3]
 * Output: 6
 * <p>
 * Input: [1,2,3,4]
 * Output: 24
 */
public class MaxProductThreeNumbers {

    public static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int s = nums[n - 1] * nums[n - 2] * nums[n - 3];
        s = Math.max(s, nums[n - 1] * nums[1] * nums[0]);
        return s;
    }

    /*********************************************************/

    public int maximumProduct2(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }

    /*********************************************************/

    public int maximumProduct3(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        return Math.max(
                nums[0] * nums[1] * nums[length - 1],
                nums[length - 1] * nums[length - 2] * nums[length - 3]
        );
    }

}
