package bg.leetcode.exercises.itenev.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 * <p>
 * A happy number is a number defined by the following process:
 * Starting with any positive integer,
 * replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * <p>
 * Input: 19
 * Output: true
 * Explanation:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();

        while (n != 1) {
            int current = n;
            int sum = 0;

            while (current != 0) {
                sum += Math.pow(current % 10, 2);
                current /= 10;
            }

            if (seen.contains(sum))
                return false;

            seen.add(sum);
            n = sum;
        }

        return true;
    }

    /********************************************************/

    //this is the bitset representation of happy numbers in range[0,810)
    private static final long[] happyMark = new long[]{580548859274370L, 35812649762896L,
            5764889547766761510L, 158621866461187L, -9061136725337702208L,
            -8574391826910016959L, 4683743612499927428L, 286423854940160L,
            29290989780729856L, 7566260683533189120L, 1170945809492058640L,
            720593571220033568L, 292095590400L};

    /**
     * Calculate the sum of the square of each digit until it is equal to 1 or has a recurrence
     * However,there is no need to calculate the sum every time.
     * As we all know, each digit's range is [0,9]
     * the range of Integer is [0,2^31-1],that is [0,10] in number of digits
     * which means the square sum of each digit would always be in range[0 , 9 * 9 * 10) or [0 , 810)
     * to speed up our processing,we can pre-calculate the happy numbers in range[0. 810)
     * and store them in our program for later lookups
     *
     * https://leetcode.com/problems/happy-number/discuss/57160/1ms-java-solution
     */
    public boolean isHappy2(int n) {
        if (n > 810) {
            int sum = 0, remainder = 0;
            while (n > 0) {
                remainder = n % 10;
                sum += remainder * remainder;
                n /= 10;
            }
            n = sum;
        }
        int idx = (n >> 6);
        long bitRep = (1L << (n & 0x3f));
        return (happyMark[idx] & bitRep) != 0;
    }

}
