package bg.leetcode.exercises.itenev.array;

/**
 * Given an integer array nums,
 * find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * <p>
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaxProductSubarray {

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int max = nums[0];
        int min = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int maxCandidate = Math.max(max * nums[i], min * nums[i]);
            int minCandidate = Math.min(max * nums[i], min * nums[i]);

            max = Math.max(maxCandidate, nums[i]);
            min = Math.min(minCandidate, nums[i]);

            if (max > result)
                result = max;
        }
        return result;
    }

    /********************************/

    public int maxProduct2(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int max = nums[0];
        int[] maxProductSoFar = new int[nums.length];
        int[] minProductSoFar = new int[nums.length];
        maxProductSoFar[0] = nums[0];
        minProductSoFar[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxProductSoFar[i] = Math.max(nums[i], maxProductSoFar[i - 1] * nums[i]);
            maxProductSoFar[i] = Math.max(maxProductSoFar[i], minProductSoFar[i - 1] * nums[i]);
            minProductSoFar[i] = Math.min(nums[i], minProductSoFar[i - 1] * nums[i]);
            minProductSoFar[i] = Math.min(minProductSoFar[i], maxProductSoFar[i - 1] * nums[i]);
            max = Math.max(max, maxProductSoFar[i]);
        }
        return max;
    }

    /********************************/

    public int maxProduct3(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        if (nums.length == 1)
            return nums[0];

        int prod = 1;
        int result = 0;

        for (int num : nums) {
            prod *= num;
            result = Math.max(result, prod);
            if (prod == 0)
                prod = 1;

        }

        prod = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            prod *= nums[i];
            result = Math.max(result, prod);
            if (prod == 0)
                prod = 1;

        }

        return result;
    }
}
