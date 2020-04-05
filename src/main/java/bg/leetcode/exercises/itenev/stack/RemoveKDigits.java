package bg.leetcode.exercises.itenev.stack;

import java.util.Stack;

/**
 * Given a non-negative integer num represented as a string,
 * remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 */
public class RemoveKDigits {

    public String removeKdigits(String num, int k) {
        if (k == num.length())
            return "0";

        Stack<Character> stack = new Stack<>();
        stack.push(num.charAt(0));
        int i = 1;

        while (i < num.length()) {
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }

            stack.push(num.charAt(i));
            i++;
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());

        sb.reverse();

        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        return sb.toString();
    }

    /*****************************************************************/

    public String removeKdigits2(String num, int k) {
        if (num == null || num.length() <= k)
            return "0";
        char[] stack = new char[num.length()];
        int i = 0;
        int index = 0;

        while (i < num.length()) {
            while (index > 0 && k > 0 && num.charAt(i) < stack[index - 1]) {
                index--;
                k--;
            }
            stack[index++] = num.charAt(i);
            i++;
            if (k == 0)
                break;
        }
        index -= k;
        StringBuilder sb = new StringBuilder(String.valueOf(stack, 0, index));
        if (i < num.length())
            sb.append(num.substring(i));

        i = 0;
        while ((sb.length() > i + 1) && sb.charAt(i) == '0')
            i++;

        return sb.substring(i);
    }

    /*****************************************************************/

    public String removeKdigits3(String num, int k) {
        StringBuilder sb = new StringBuilder(num);

        while (k > 0) {
            for (int i = 0; i < sb.length(); i++)
                if (i + 1 < sb.length() && sb.charAt(i + 1) < sb.charAt(i)) {
                    sb.deleteCharAt(i);
                    break;
                } else if (i == sb.length() - 1) {
                    sb.deleteCharAt(i);
                    break;
                }

            k--;
        }

        while (sb.length() > 0 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);

        String res = sb.toString();
        if (res.equals(""))
            return "0";
        else
            return res;
    }
}
