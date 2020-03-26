package bg.leetcode.exercises.itenev;

/**
 * The Hamming distance between two integers
 * is the number of positions at which the corresponding bits are different.
 * <p>
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 */
public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int res = 0;
        while (x > 0 || y > 0) {
            res += (x % 2) ^ (y % 2);
            x >>= 1;
            y >>= 1;
        }

        return res;
    }

    public int hammingDistance2(int x, int y) {
        int xor = x ^ y;
        //BRIAN KERNIGHAN'S algorithm
        int count = 0;
        while (xor > 0) {
            xor = xor & (xor - 1);
            count++;
        }

        return count;
    }

}
