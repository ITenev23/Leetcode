package bg.leetcode.exercises.itenev.array;

/**
 * Your are given an array of positive integers nums.
 * Count and print the number of (contiguous) subarrays
 * where the product of all the elements in the subarray is less than k.
 * <p>
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class SubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1)
            return 0;

        int product = 1;
        int result = 0;
        int left = 0;
        int right = 0;

        while(right < nums.length) {
            product *= nums[right];

            while (product >= k)
                product /= nums[left++];

            result += right - left + 1;
            right++;
        }
        return result;
    }

    /**
     * After this transformation where every value x becomes log(x),
     * let us take prefix sums prefix[i+1] = nums[0] + nums[1] + ... + nums[i].
     * Now we are left with the problem of finding, for each i,
     * the largest j so that nums[i] + ... + nums[j] = prefix[j] - prefix[i] < k.
     * <p>
     * Because prefix is a monotone increasing array,
     * this can be solved with binary search. We add the width of the interval [i, j] to our answer,
     * which counts all subarrays [i, k] with k <= j.
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k == 0) return 0;
        double logk = Math.log(k);
        double[] prefix = new double[nums.length + 1];

        for (int i = 0; i < nums.length; i++)
            prefix[i + 1] = prefix[i] + Math.log(nums[i]);


        int product = 0;
        for (int i = 0; i < prefix.length; i++) {
            int left = i + 1;
            int right = prefix.length;

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (prefix[mid] < prefix[i] + logk - 1e-9)
                    left = mid + 1;
                else
                    right = mid;
            }
            product += left - i - 1;
        }
        return product;
    }
}
