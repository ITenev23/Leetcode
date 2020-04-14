package bg.leetcode.exercises.itenev.string;

/**
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = product + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int p : pos)
            if (!(sb.length() == 0 && p == 0))
                sb.append(p);

        return sb.length() == 0 ? "0" : sb.toString();
    }

    /************************************************************************/

    public String multiply2(String num1, String num2) {
        int size1 = num1.length();
        int size2 = num2.length();
        int[] products = new int[size1 + size2];

        for (int i = size1 - 1; i >= 0; i--) {
            for (int j = size2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }

        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }

        StringBuilder sb = new StringBuilder();
        for (int num : products)
            sb.append(num);

        while (sb.length() != 0 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        return sb.length() == 0 ? "0" : sb.toString();
    }
}
