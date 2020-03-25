package bg.leetcode.exercises.itenev.string;

import java.util.Arrays;

public class ReverseString {

    public static String reverseWithRecursion(String s) {
        if ((null == s) || (s.length() <= 1)) {
            return s;
        }
        return reverseWithRecursion(s.substring(1)) + s.charAt(0);
    }

    public static String reverseWithLoop(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static String reverseWithStringBuilder(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static String reverse(String s) {
        char[] in = s.toCharArray();
        int begin = 0;
        int end = in.length - 1;
        char temp;
        while (end > begin) {
            temp = in[begin];
            in[begin] = in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

    /*Example:
     * str= peek;
     * str[low]=p;
     * str[high]=k;
     * str[low]=p^k;
     * str[high]=(p^k)^k =p;
     * str[low]=(p^k)^p=k;
     *
     * */
    public static String reverseUsingXOR(char[] str) {
        int low = 0;
        int high = str.length - 1;

        while (low < high) {
            str[low] = (char) (str[low] ^ str[high]);
            str[high] = (char) (str[low] ^ str[high]);
            str[low] = (char) (str[low] ^ str[high]);
            low++;
            high--;
        }

        return Arrays.toString(str);
    }

}
