package bg.leetcode.exercises.itenev.math;

/**
 * You have an array nums. We determine two functions to perform on nums. In both cases, n is the length of nums:
 * <p>
 * fi(nums) = nums[0] · nums[1] · ... · nums[i - 1] · nums[i + 1] · ... · nums[n - 1].
 * (In other words, fi(nums) is the product of all array elements except the ithf.)
 * g(nums) = f0(nums) + f1(nums) + ... + fn-1(nums).
 * Using these two functions, calculate all values of f modulo the given m.
 * Take these new values and add them together to get g. You should return the value of g modulo the given m.
 * <p>
 * Input: [1, 2, 3, 4] , m = 12
 * Output: 2.
 * <p>
 * The array of the values of f is: [24, 12, 8, 6]. If we take all the elements modulo m, we get:
 * [0, 0, 8, 6]. The sum of those values is 8 + 6 = 14, making the answer 14 % 12 = 2.
 */
public class ProductExceptSelf {

    int productExceptSelf(int[] nums, int m) {
        int sum = 0;
        int product = 1;
        for (int num : nums) {
            sum = num * sum + product;
            sum %= m;
            product *= num;
            product %= m;
        }
        return sum;
    }

    /****************************************************************/

    int productExceptSelf2(int[] nums, int m) {
        int n = nums.length;
        int[] prodLeft = new int[n];
        int[] prodRight = new int[n];
        prodLeft[0] = 1;
        prodRight[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            prodLeft[i] = (prodLeft[i - 1] * nums[i - 1]) % m;
            prodRight[n - i - 1] = (prodRight[n - i] * nums[n - i]) % m;
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum = (sum + prodLeft[i] * prodRight[i]) % m;
        }
        return sum;
    }

}
