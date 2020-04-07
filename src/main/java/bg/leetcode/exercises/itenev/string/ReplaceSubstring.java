package bg.leetcode.exercises.itenev.string;

import java.util.HashMap;
import java.util.Map;

/**
 * You have two arrays of strings, words and parts.
 * Return an array that contains the strings from words,
 * modified so that any occurrences of the substrings from parts
 * that are surrounded by square brackets [], following these guidelines:
 * <p>
 * If several parts substrings occur in one string in words, choose the longest one.
 * If there is still more than one such part, then choose the one that appears first in the string.
 * <p>
 * For words = ["Apple", "Melon", "Orange", "Watermelon"] and parts = ["a", "mel", "lon", "el", "An"]
 * Output: ["Apple", "Me[lon]", "Or[a]nge", "Water[mel]on"]
 * <p>
 * While "Watermelon" contains three substrings from the parts array, "a", "mel", and "lon", "mel"
 * is the longest substring that appears first in the string.
 */
public class ReplaceSubstring {

    public static String[] findSubstrings(String[] words, String[] parts) {
        Trie t = new Trie(parts);
        String[] result = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            int longest = 0;

            for (int j = 0; j < current.length(); j++) {
                int index = t.search(current.substring(j));
                if (index != -1 && index > longest) {
                    longest = index;
                    result[i] = current.substring(0, j) + "[" +
                            current.substring(j, index + j) + "]" +
                            words[i].substring(index + j);
                }
                if (longest > current.length() - j) {
                    break;
                }
            }
            if (result[i] == null) {
                result[i] = words[i];
            }
        }
        return result;
    }


    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        TrieNode(boolean isWord) {
            children = new HashMap<>();
            this.isWord = isWord;
        }
    }

    static class Trie {
        TrieNode root;

        Trie(String[] array) {
            root = new TrieNode(false);
            for (String s : array) {
                insert(s);
            }
        }

        public void insert(String s) {
            TrieNode cur = root;
            TrieNode next;
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                next = cur.children.get(ch);
                if (next == null) {
                    next = new TrieNode(false);
                    cur.children.put(ch, next);
                }
                cur = next;
            }
            cur.isWord = true;
        }

        //return index of end match longest
        public int search(String s) {
            TrieNode cur = root;
            TrieNode next;
            int n = s.length();
            int i = 0;
            int result = -1;
            while (cur != null && i < n) {
                char ch = s.charAt(i++);
                next = cur.children.get(ch);
                cur = next;
                if (cur != null && cur.isWord) {
                    result = i;
                }
            }
            return result;
        }
    }
}
