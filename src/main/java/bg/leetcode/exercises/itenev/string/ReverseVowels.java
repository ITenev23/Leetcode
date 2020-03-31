package bg.leetcode.exercises.itenev.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Input: "hello"
 * Output: "holle"
 * <p>
 * Input: "leetcode"
 * Output: "leotcede"
 * <p>
 * Note: The vowels does not include the letter "y".
 */
public class ReverseVowels {

    public String reverseVowels(String s) {
        Set<Character> vowels = initSet();

        int left = 0;
        int right = s.length() - 1;
        char[] chars = s.toCharArray();

        while (left < right) {
            while (left < right && !vowels.contains(chars[left]))
                left++;
            while (left < right && !vowels.contains(chars[right]))
                right--;

            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }

        return new String(chars);
    }

    private Set<Character> initSet() {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('A');
        vowels.add('e');
        vowels.add('E');
        vowels.add('o');
        vowels.add('O');
        vowels.add('u');
        vowels.add('U');
        vowels.add('i');
        vowels.add('I');
        return vowels;
    }

    /****************************************************************/

    public String reverseVowels2(String s) {
        int i = 0, j = s.length() - 1;
        char[] chars = s.toCharArray();
        String vowels = "aeiouAEIOU";
        while (i < j) {
            while (i < j && vowels.indexOf(chars[i]) == -1) {
                i++;
            }
            while (i < j && vowels.indexOf(chars[j]) == -1) {
                j--;
            }
            if (i < j) {
                char temp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = temp;
            }
        }
        return new String(chars);
    }

}
