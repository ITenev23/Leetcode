package bg.leetcode.exercises.itenev.string;

import java.util.Stack;

/**
 * Given a string S, return the "reversed" string where all characters
 * that are not a letter stay in the same place, and all letters reverse their positions.
 * <p>
 * Input: "ab-cd"
 * Output: "dc-ba"
 * <p>
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * <p>
 * Input: "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 */
public class ReverseOnlyLetters {

    public String reverseOnlyLetters(String S) {
        int left = 0;
        int right = S.length() - 1;
        char[] chars = S.toCharArray();

        while (left < right) {
            while (left < right && !Character.isLetter(S.charAt(left)))
                left++;

            while (left < right && !Character.isLetter(S.charAt(right)))
                right--;


            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }

        return new String(chars);
    }

    /******************************************************************************/


    public String reverseOnlyLetters2(String S) {
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }

}
