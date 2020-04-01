package bg.leetcode.exercises.itenev.math;

/**
 * Given a positive integer, check whether it has alternating bits:
 * namely, if two adjacent bits will always have different values.
 *
 * Input: 5
 * Output: True
 * Explanation: The binary representation of 5 is: 101
 *
 * Input: 7
 * Output: False
 * Explanation: The binary representation of 7 is: 111.
 *
 * Input: 10
 * Output: True
 * Explanation: The binary representation of 10 is: 1010.
 */
public class BinaryNumberAlternatingBits {

    public static boolean hasAlternatingBitsConvertToString(int n) {
        String bits = Integer.toBinaryString(n);
        for (int i = 0; i < bits.length() - 1; i++) {
            if (bits.charAt(i) == bits.charAt(i+1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasAlternatingBits2(int n) {
        int last = n % 2;
        n >>= 1;
        while (n > 0) {
            int current = n % 2;
            if (current == last)
                return false;

            last = current;
            n >>= 1;
        }
        return true;
    }
}
