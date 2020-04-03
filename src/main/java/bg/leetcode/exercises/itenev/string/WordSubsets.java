package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.List;

/**
 *We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
 * For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 * Return a list of all universal words in A.  You can return the words in any order.
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 *
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 */
public class WordSubsets {

    public List<String> wordSubsets(String[] A, String[] B) {
        int[] maxCharFrequencies = new int[26];

        for (String word : B) {
            int[] charFrequencies = getCharFrequencis(word);
            for (int j = 0; j < 26; j++) {
                maxCharFrequencies[j] = Math.max(maxCharFrequencies[j], charFrequencies[j]);
            }
        }

        List<String> result = new ArrayList<>();

        for (String word : A) {
            int[] charFrequencies = getCharFrequencis(word);

            boolean valid = true;
            for (int j = 0; j < 26; j++) {
               if (maxCharFrequencies[j] > charFrequencies[j]) {
                   valid = false;
                   break;
               }
            }
            if (valid)
                result.add(word);
        }

        return result;
    }

    private int[] getCharFrequencis(String current) {
        int[] res = new int[26];
        for (char c : current.toCharArray()) {
            res[c - 'a'] += 1;
        }
        return res;
    }
}
