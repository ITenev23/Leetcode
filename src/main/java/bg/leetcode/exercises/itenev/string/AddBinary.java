package bg.leetcode.exercises.itenev.string;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 * <p>
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0)
                sum += a.charAt(i--) - '0';

            if (j >= 0)
                sum += b.charAt(j--) - '0';

            sb.insert(0, sum % 2);
            carry = sum / 2;
        }

        if (carry > 0)
            sb.insert(0, 1);

        return sb.toString();
    }

    /******************************************************************************/

    public String addBinary2(String a, String b) {
        int size1 = a.length();
        int size2 = b.length();
        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (Math.max(size1, size2) > 0) {
            int first = size1 > 0 ? (a.charAt(size1-- - 1) - '0') : 0;
            int second = size2 > 0 ? (b.charAt(size2-- - 1) - '0') : 0;
            int sum = first + second + carry;

            sb.append(sum % 2);
            carry = sum / 2;
        }
        return (carry == 1) ? sb.append(1).reverse().toString() : sb.reverse().toString();
    }

}
