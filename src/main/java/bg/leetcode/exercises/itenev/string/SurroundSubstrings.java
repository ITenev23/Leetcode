package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You have two arrays of strings, words and parts.
 * Write a function findSubstrings(words, parts) that returns the array that contains the strings from words,
 * modified so that any occurrences of the substrings from parts are surrounded by square brackets [],
 * following these guidelines:
 * <p>
 * 1. If several parts substrings occur in one string in words, choose the longest one.
 * 2. If there is still more than one such part, then choose the one that appears first in the string.
 * <p>
 * Input: words = ["Apple", "Melon", "Orange", "Watermelon"], parts = ["a", "mel", "lon", "el", "An"]
 * Output: ["Apple", "Me[lon]", "Or[a]nge", "Water[mel]on"].
 * Explanation: While "Watermelon" contains three substrings from the parts array ("a", "mel", and "lon")
 * "mel" is the longest substring that appears first in the string.
 */
public class SurroundSubstrings {

    public static void main(String[] args) {
        String[] words = {"apple", "Melon", "Orange", "Watermelon"};
        String[] parts = {"a", "mel", "lon", "el", "An"};

        List<String> result = transform(Arrays.asList(words), Arrays.asList(parts));

        for (String r : words) {
            System.out.print(surroundString3(r, parts) + " ");
        }
    }

    public static List<String> transform(List<String> words, List<String> parts) {
        List<String> result = new ArrayList<>();
        for (String word : words) {
            int index = -1, size = 0;
            for (String p : parts) {
                if (word.contains(p) && p.length() > size) {
                    index = word.indexOf(p);
                    size = p.length();
                }
            }
            if (index >= 0) {
                StringBuilder sb = new StringBuilder(word);
                sb.insert(index, '[');
                if (index + size + 1 >= word.length()) {
                    sb.append(']');
                } else {
                    sb.insert(index + size + 1, ']');
                }
                result.add(sb.toString());
            } else {
                result.add(word);
            }
        }
        return result;
    }

    /****************************************************************************/

    public static List<String> transform2(String[] words, String[] parts) {
        List<String> output = new ArrayList<>();
        for (String word : words) {
            int maxLen = -1;
            int first = -1;
            int last = -1;
            for (String part : parts) {
                int ind = word.indexOf(part);
                if (ind >= 0) {
                    if (maxLen == -1 || part.length() > maxLen) {
                        maxLen = part.length();
                        first = ind;
                        last = ind + part.length();
                    }
                }
            }
            if (first == -1) {
                output.add(word);
            } else {
                StringBuffer st = new StringBuffer();
                for (int i = 0; i < word.length(); ++i) {
                    if (first == i) {
                        st.append('[');
                    }
                    st.append(word.charAt(i));
                    if ((last - 1) == i) {
                        st.append(']');
                    }
                }
                output.add(st.toString());
            }
        }
        return output;
    }

    /****************************************************************************/

    public static String surroundString3(String word, String[] parts) {
        int start = -1;
        int maxLen = 0;

        for (String part : parts) {
            if (word.contains(part) && maxLen < part.length()) {
                start = word.indexOf(part);
                maxLen = part.length();
            }
            if (word.contains(part) && word.indexOf(part) < start && maxLen <= part.length()) {
                start = word.indexOf(part);
                maxLen = part.length();
            }
        }
        if (start == -1)
            return word;

        String subtr = word.substring(start, start + maxLen);
        return word.replace(subtr, "[" + subtr + "]");
    }

}
