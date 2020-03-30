package bg.leetcode.exercises.itenev.string;

/**
 * Implement atoi which converts a string to an integer.
 * <p>
 * The function first discards as many whitespace characters as necessary
 * until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign
 * followed by as many numerical digits as possible, and interprets them as a numerical value.
 * <p>
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 * <p>
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters,
 * no conversion is performed.
 * <p>
 * If no valid conversion could be performed, a zero value is returned.
 * <p>
 * Input: "4193 with words"
 * Output: 4193
 * <p>
 * Input: "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
 * Thefore INT_MIN (−231) is returned.
 */
public class StringToInteger {

    public int myAtoi(String str) {
        String strTrim = str.trim();
        if (strTrim.length() == 0)
            return 0;
        int index = 0;
        int sign = 1;
        long result = 0;

        if (strTrim.charAt(index) == '+' || strTrim.charAt(index) == '-') {
            sign = strTrim.charAt(index) == '+' ? 1 : -1;
            index++;
        }
        // convert string to integer without overflow
        while (index < strTrim.length()) {
            int digit = strTrim.charAt(index) - '0';

            if (digit < 0 || digit > 9)
                break;

            result = result * 10 + digit;

            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            index++;
        }
        return (int) (result * sign);
    }

    public int myAtoi2(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }

        char[] chas = str.trim().toCharArray();
        int index = 0;
        if (chas[index] > '9') {
            return 0;
        }

        int neg = -1;
        if (chas[index] == '-') {
            neg = 1;
            index++;
        } else if (chas[index] == '+') {
            index++;
        }

        int res = 0;
        while (index < chas.length && chas[index] >= '0' && chas[index] <= '9') {
            int val = chas[index++] - '0';
            if ((neg == -1 && (-res > Integer.MAX_VALUE / 10 ||
                    -res * 10 > Integer.MAX_VALUE - val))) {
                return Integer.MAX_VALUE;
            } else if (neg == 1 && (res < Integer.MIN_VALUE / 10 ||
                    res * 10 < Integer.MIN_VALUE + val)) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 - val;
        }

        return neg * res;
    }

    public int myAtoi3(String str) {

        int i = 0, flag = 0;
        StringBuilder st = new StringBuilder();

        // Clear white space characters
        while (i < str.length() && str.charAt(i) == ' ') {
            i++;
        }

        // Discover negative/positive sign
        if (i < str.length() && str.charAt(i) == '-') {
            st.append('-');
            i++;
        } else if (i < str.length() && str.charAt(i) == '+') {
            i++;
        }

        // Take numerals
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            st.append(str.charAt(i));
            i++;
            flag = 1;
        }
        // If flag is 0, then there must be no numbers
        if (flag == 0) return 0;

        // Detect if number is bigger than 32 bit
        try {
            return Integer.parseInt(st.toString());
        } catch (NumberFormatException e) {
            // Return MIN or MAX based on sign
            return (st.toString().charAt(0) == '-') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }

}
