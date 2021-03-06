package bg.leetcode.exercises.itenev.array;

/**
 * Given an array nums and a value val,
 * remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array,
 * you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed.
 * It doesn't matter what you leave beyond the new length.
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * Your function should return length = 2,
 * with the first two elements of nums being 2.
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * Your function should return length = 5,
 * with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * Note that the order of those five elements can be arbitrary.
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i : nums) {
            if (i != val) {
                nums[index++] = i;
            }
        }

        return index;
    }

    public int removeElementTwoPointers(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

}
