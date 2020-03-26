package bg.leetcode.exercises.itenev;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of integers,
 * every element appears three times except for one,
 * which appears exactly once. Find that single one.
 * <p>
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 * <p>
 * Input: [2,2,3,2]
 * Output: 3
 */
public class SingleNumberII {

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Integer i : map.keySet()) {
            if (map.get(i) == 1)
                return i;
        }

        return -1;
    }

    public int singleNumberBitManipulation(int[] arr) {
        int ones = 0;
        int twos = 0;

        for (int value : arr) {
            ones = (ones ^ value) & ~twos;
            twos = (twos ^ value) & ~ones;
        }
        return ones;
    }
}
