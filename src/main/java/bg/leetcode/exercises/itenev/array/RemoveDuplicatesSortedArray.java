package bg.leetcode.exercises.itenev.array;

/**
 * Given a sorted array nums, remove the duplicates in-place
 * such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array,
 * you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * Your function should return length = 5,
 * with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * <p>
 * Given nums = [1,1,2],
 * Your function should return length = 2,
 * with the first two elements of nums being 1 and 2 respectively.
 * <p>
 * Note: It doesn't matter what values are set beyond the returned length.
 */
public class RemoveDuplicatesSortedArray {

    public int removeDuplicates(int[] nums) {
        int index = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            int next = nums[i + 1];

            if (nums[i] != next) {
                nums[index++] = next;
            }
        }

        return index;
    }

}
