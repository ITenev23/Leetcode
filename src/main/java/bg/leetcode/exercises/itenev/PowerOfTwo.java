package bg.leetcode.exercises.itenev;

/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Input: 16
 * Output: true
 * Explanation: 24 = 16
 *
 */
public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        long i = 1;

        while (i < n) {
            i *= 2;
        }

        return i == n;
    }
}
