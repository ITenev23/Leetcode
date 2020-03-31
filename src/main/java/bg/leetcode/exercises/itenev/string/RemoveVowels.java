package bg.leetcode.exercises.itenev.string;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, remove the vowels 'a','e','i','o','u'
 *
 * Input: "aeoiou"
 * Output: ""
 */
public class RemoveVowels {

    public static String removeVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('o');
        vowels.add('u');
        vowels.add('i');

        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!vowels.contains(c))
                sb.append(c);
        }
        return sb.toString();
    }

    /*************************************************/

    public static String removeVowels2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u')
                sb.append(c);
        }
        return sb.toString();
    }
}
