package bg.leetcode.exercises.itenev;

import java.util.HashMap;
import java.util.Map;

/**
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
 * The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language, and the order of the alphabet,
 * return true if and only if the given words are sorted lexicographicaly in this alien language.
 * <p>
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * <p>
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * <p>
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.)
 * According to lexicographical rules "apple" > "app", because 'l' > '∅',
 * where '∅' is defined as the blank character which is less than any other character
 * (https://en.wikipedia.org/wiki/Lexicographical_order).
 */
public class VerifyingAlienDictionary {

    public static boolean isAlienSorted(String[] words, String order) {
        if (words.length < 2) {
            return true;
        }

        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];

            if (!compare(w1, w2, order)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare(String word1, String word2, String order) {
        int p1 = 0;
        int p2 = 0;

        while (p1 < word1.length() && p1 < word2.length()) {
            char c1 = word1.charAt(p1);
            char c2 = word2.charAt(p2);

            int i1 = order.indexOf(c1);
            int i2 = order.indexOf(c2);

            if (i1 > i2)
                return false;
            else if (i1 < i2)
                return true;
            else
                p1++;
            p2++;

        }

        return false;
    }

    public boolean isAlienSorted2(String[] words, String order) {
        int[] index = new int[26];
        for (int i = 0; i < order.length(); ++i)
            index[order.charAt(i) - 'a'] = i;

        search:
        for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i + 1];

            for (int k = 0; k < Math.min(word1.length(), word2.length()); ++k) {
                if (word1.charAt(k) != word2.charAt(k)) {
                    if (index[word1.charAt(k) - 'a'] > index[word2.charAt(k) - 'a'])
                        return false;
                    continue search;
                }
            }

            // words are like ("app", "apple").
            if (word1.length() > word2.length())
                return false;
        }

        return true;
    }

}
