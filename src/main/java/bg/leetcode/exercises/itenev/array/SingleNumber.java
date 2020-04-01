package bg.leetcode.exercises.itenev.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 * <p>
 * Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 * <p>
 * Input: [2,2,1]
 * Output: 1
 */
public class SingleNumber {

    /** Space complexity : O(1) */
    public static int singleNumberWithXOR(int[] nums) {
        int a = 0;
        for (int i : nums) {
            a ^= i;
        }
        return a;
    }

    /** Space complexity : O(n + n) = O(n)O(n+n)=O(n). set needs space for the elements in nums*/
    public static int singleNumber(int[] nums) {
        int sumOfSet = 0;
        int sumOfNums = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
                sumOfSet += num;
            }
            sumOfNums += num;
        }
        return 2 * sumOfSet - sumOfNums;
    }
}
