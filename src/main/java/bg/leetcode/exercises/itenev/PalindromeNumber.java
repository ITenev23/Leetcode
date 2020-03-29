package bg.leetcode.exercises.itenev;

/**
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 * <p>
 * Input: 121
 * Output: true
 * <p>
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-.
 * <p>
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left.
 */
public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        String a = Integer.toString(x);
        String reversed = new StringBuilder(a).reverse().toString();
        return a.equals(reversed);
    }

    public boolean isPalindrome2(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;

        int reversed = 0;
        while (x > reversed) {
            int pop = x % 10;
            x /= 10;

            reversed = (reversed * 10) + pop;
        }

        return x == reversed || x == reversed / 10;
    }

    public boolean IsPalindrome3(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // When the length is an odd number, we can get rid of the middle digit by reversed/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, reversed = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == reversed || x == reversed / 10;
    }

}
