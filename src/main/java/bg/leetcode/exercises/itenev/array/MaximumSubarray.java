package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {

    /**
     * Notice that dp[i] only depends on dp[i-1].
     * So instead of storing all the results in the dp array,
     * we can just save the previous value.
     */
    public static int maxSubArrayOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int maxEndingHere = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSum = Math.max(maxSum, maxEndingHere);
        }
        return maxSum;
    }

    /*************************************************************************/

    public static int maxSubArrayDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    /*************************************************************************/

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        int m = nums.length / 2;
        int left_MSA = maxSubArray(Arrays.copyOfRange(nums, 0, m));
        int right_MSA = maxSubArray(Arrays.copyOfRange(nums, m, nums.length));

        int leftMaxSum = Integer.MIN_VALUE;
        int rightMaxSum = Integer.MIN_VALUE;
        int midMaxSum = Integer.MIN_VALUE;

        int temp = 0;
        for (int i = m; i < nums.length; i++) {
            temp += nums[i];
            rightMaxSum = Math.max(rightMaxSum, temp);
        }
        temp = 0;
        for (int i = m - 1; i >= 0; i--) {
            temp += nums[i];
            leftMaxSum = Math.max(leftMaxSum, temp);
        }
        midMaxSum = leftMaxSum + rightMaxSum;
        int interMax = Math.max(left_MSA, right_MSA);
        return Math.max(interMax, midMaxSum);
    }

    /*************************************************************************/

    public static int maxSubArray2(int[] nums) {
        /**
         Divide-and-conquer method.
         The maximum summation of subarray can only exists under following conditions:
         1. the maximum summation of subarray exists in left half.
         2. the maximum summation of subarray exists in right half.
         3. the maximum summation of subarray exists crossing the midpoints to left and right.
         1 and 2 can be reached by using recursive calls to left half and right half of the subarraies.
         Condition 3 can be found starting from the middle point to the left,
         then starting from the middle point to the right. Then adds up these two parts and return.

         T(n) = 2*T(n/2) + O(n)
         this program runs in O(nlogn) time
         */
        int maxsum = subArray(nums, 0, nums.length - 1);
        return maxsum;
    }

    private static int subArray(int[] A, int left, int right) {
        if (left == right)
            return A[left];

        int mid = left + (right - left) / 2;
        int leftsum = subArray(A, left, mid);
        int rightsum = subArray(A, mid + 1, right);
        int middlesum = midSubArray(A, left, mid, right);

        if (leftsum >= rightsum && leftsum >= middlesum)
            return leftsum;

        if (rightsum >= leftsum && rightsum >= middlesum)
            return rightsum;

        return middlesum;
    }

    private static int midSubArray(int[] A, int left, int mid, int right) {
        int leftsum = Integer.MIN_VALUE;
        int rightsum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += A[i];
            if (leftsum < sum) {
                leftsum = sum;
            }
        }

        sum = 0;
        for (int j = mid + 1; j <= right; j++) {
            sum += A[j];
            if (rightsum < sum) {
                rightsum = sum;
            }
        }

        return leftsum + rightsum;
    }

    /*******************************************************************/

    public int maxSubArray3(int[] nums) {
        return dAc(nums, 0, nums.length - 1);
    }

    public int dAc(int[] nums, int l, int h) {
        if (l == h) {
            return nums[l];
        }
        int m = (l + h) / 2;
        return Math.max(Math.max(dAc(nums, l, m), dAc(nums, m + 1, h)), maxSubArrayCross(nums, l, m, h));
    }

    public int maxSubArrayCross(int[] nums, int l, int m, int h) {
        int sum = 0, left_sum = Integer.MIN_VALUE, right_sum = Integer.MIN_VALUE;
        for (int i = m; i >= l; i--) {
            sum += nums[i];
            if (sum > left_sum)
                left_sum = sum;
        }
        sum = 0;
        for (int i = m + 1; i <= h; i++) {
            sum += nums[i];
            if (sum > right_sum)
                right_sum = sum;
        }
        return left_sum + right_sum;
    }

}
