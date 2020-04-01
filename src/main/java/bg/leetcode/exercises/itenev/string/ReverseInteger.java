package bg.leetcode.exercises.itenev.string;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Input: 123
 * Output: 321
 */
public class ReverseInteger {

    public static int reverse(int x) {
        String s = Integer.toString(x);
        if (s.startsWith("-")) {
            s = s.substring(1);
            return Integer.parseInt("-" + new StringBuilder(s).reverse().toString());
        }
        return Integer.parseInt(new StringBuilder(s).reverse().toString());
    }

    public static int reverse2(int x) {
        boolean negative = false;

        if (x < 0) {
            negative = true;
            x *= -1;
        }

        long reversed = 0L;
        while (x > 0) {
            reversed = (reversed * 10) + (x % 10);
            x /= 10;
        }

        if (reversed > Integer.MAX_VALUE) return 0;
        return negative ? (int)(-1 * reversed) : (int)reversed;
    }

    public int reverse3(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
