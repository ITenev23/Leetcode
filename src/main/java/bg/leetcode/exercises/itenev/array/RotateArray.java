package bg.leetcode.exercises.itenev.array;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Input: [1,2,3,4,5,6,7] and k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * <p>
 * Input: [-1,-100,3,99] and k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    /**************************************************************/

    public void rotate2(int[] nums, int k) {
        if (nums.length <= 1)
            return;

        //step each time to move
        int step = k % nums.length;
        int[] tmp = new int[step];

        System.arraycopy(nums, nums.length - step, tmp, 0, step);

        if (nums.length - step - 1 + 1 >= 0)
            System.arraycopy(nums, 0, nums, step, nums.length - step - 1 + 1);

        System.arraycopy(tmp, 0, nums, 0, step);
    }

    public void rotate3(int[] nums, int k) {
        if (nums.length == 0)
            return;

        int n = nums.length;
        while ((k %= n) > 0 && n > 1) {
            int range = n - k;
            for (int i = 1; i <= range; i++) {
                int val = nums[n - i];
                nums[n - i] = nums[n - i - k];
                nums[n - i - k] = val;
            }
            n = k;
            k = n - (range % k);
        }
    }
}
