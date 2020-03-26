package bg.leetcode.exercises.itenev;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * <p>
 * Input: [3,0,1]
 * Output: 2
 * <p>
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class MissingNumber {

    public int missingNumberGaussFormula(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums)
            actualSum += num;

        return expectedSum - actualSum;
    }

    public int missingNumberBitManipulation(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    public int missingNumber3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums)
            set.add(num);

        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i))
                return i;
        }

        return -1;
    }

}
