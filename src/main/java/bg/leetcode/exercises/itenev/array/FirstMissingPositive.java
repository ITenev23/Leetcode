package bg.leetcode.exercises.itenev.array;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.
 *
 * Input: [1,2,0]
 * Output: 3
 *
 * Input: [3,4,-1,1]
 * Output: 2
 *
 * Input: [7,8,9,11,12]
 * Output: 1
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int current = nums[i];

            if (current >= 1 && current <= nums.length && nums[current - 1] != nums[i]) {
                swap(nums, i, current - 1);
            } else {
                i++;
            }
        }
        i = 0;
        while (i < nums.length && nums[i] == i + 1)
            i++;

        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        A[i] = A[i] + A[j];
        A[j] = A[i] - A[j];
        A[i] = A[i] - A[j];
    }

    /***********************************************************************/

    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        int m = n + 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) nums[i] = 0;
        }
        for (int i = 0; i < n; i++) {
            int prev = nums[i] % m;
            if (prev > 0)
                nums[prev - 1] = (prev * m) + nums[prev - 1] % m;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] / m != i + 1) return i + 1;
        }
        return m;
    }

    /***********************************************************************/

    public int firstMissingPositive3(int[] nums) {
        int n = nums.length;
        boolean oneExist = false;
        for (int o : nums) {
            if (o == 1) {
                oneExist = true;
                break;
            }
        }
        if (!oneExist)
            return 1;

        for (int i = 0; i < nums.length; i++)
            if (nums[i] <= 0 || nums[i] > n)
                nums[i] = 1;


        for (int i = 0; i < nums.length; i++) {
            int v = Math.abs(nums[i]);

            //simply invalidating an index v and it's content
            //because we found a value v
            if (v == n)
                nums[0] = -1 * Math.abs(nums[0]);
            else
                nums[v] = -1 * Math.abs(nums[v]);

        }

        for (int i = 1; i < n; i++)
            if (nums[i] > 0)
                return i;

        if (nums[0] > 0)
            return n;

        return n + 1;
    }

}
