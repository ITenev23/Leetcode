package bg.leetcode.exercises.itenev.array;

/**
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * <p>
 * Input: [1,3,5,6], 5
 * Output: 2
 * <p>
 * Input: [1,3,5,6], 2
 * Output: 1
 * <p>
 * Input: [1,3,5,6], 0
 * Output: 0
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0 || target <= nums[0])
            return 0;
        if(target > nums[nums.length-1])
            return nums.length;

        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                right = mid;
            else
                left = ++mid;
        }

        return left;
    }

}
