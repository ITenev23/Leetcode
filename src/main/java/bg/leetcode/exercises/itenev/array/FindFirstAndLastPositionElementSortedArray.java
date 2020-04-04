package bg.leetcode.exercises.itenev.array;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class FindFirstAndLastPositionElementSortedArray {

    public int[] searchRange(int[] A, int target) {
        int left = bs(A, target);

        if (left == A.length || A[left] != target)
            return new int[]{-1, -1};

        int right = bs(A, target + 1) - 1;

        return new int[]{left, right};
    }

    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    private static int bs(int[] A, int target) {
        int left = 0;
        int right = A.length;

        while (left < right) {
            int mid = left + ((right - left) >> 1);

            if (A[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /***********************************************************/

    public int[] searchRange2(int[] nums, int target) {
        int[] result = {-1, -1};

        /** find the index of the leftmost appearance of `target`.*/
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == target) {
                result[0] = i;
                break;
            }

        if (result[0] == -1)
            return result;

        /** find the index of the rightmost appearance of `target` (by reverse
         iteration). it is guaranteed to appear.*/
        for (int j = nums.length - 1; j >= 0; j--)
            if (nums[j] == target) {
                result[1] = j;
                break;
            }


        return result;
    }

    /*********************************************/

    public int[] searchRange3(int[] nums, int target) {
        if (nums.length == 0)
            return new int[]{-1, -1};

        int left = binarySearch(nums, target - 0.5);
        int right = binarySearch(nums, target + 0.5);

        if (right - left == 0)
            return new int[]{-1, -1};

        return new int[]{left, right - 1};
    }

    private int binarySearch(int[] arr, double target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] > target)
                right = mid - 1;
            else if (arr[mid] < target)
                left = mid + 1;
        }
        return left;
    }

}
