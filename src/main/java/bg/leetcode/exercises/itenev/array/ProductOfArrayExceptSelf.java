package bg.leetcode.exercises.itenev.array;

/**
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];

        // answer[i] contains the product of all the elements to the left
        // Note: for the element at index '0', there are no elements to the left,
        // so the answer[0] would be 1
        answer[0] = 1;
        for (int i = 1; i < length; i++) {

            // answer[i - 1] already contains the product of elements to the left of 'i - 1'
            // Simply multiplying it with nums[i - 1] would give the product of all
            // elements to the left of index 'i'
            answer[i] = nums[i - 1] * answer[i - 1];
        }

        // R contains the product of all the elements to the right
        // Note: for the element at index 'length - 1', there are no elements to the right,
        // so the R would be 1
        int R = 1;
        for (int i = length - 1; i >= 0; i--) {

            // For the index 'i', R would contain the
            // product of all elements to the right. We update R accordingly
            answer[i] = answer[i] * R;
            R *= nums[i];
        }

        return answer;
    }

    /****************************************************************/


    public static int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        helper(nums, 0, result);
        return result;
    }

    private  static void helper(int[] nums, int index, int[] res) {
        if(index > nums.length - 1)
            return;

        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            if(i == index)
                continue;
            product *= nums[i];
        }
        res[index++] = product;
        helper(nums, index, res);
    }

}
