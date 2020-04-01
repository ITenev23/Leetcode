package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of strings arr.
 * String s is a concatenation of a sub-sequence of arr which have unique characters.
 * <p>
 * Return the maximum possible length of s.
 * <p>
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * <p>
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 * <p>
 * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
 * Output: 26
 */
public class MaximumLengthConcatenatedStringUniqueCharacters {

    public int maxLength(List<String> arr) {
        int[] result = new int[1];
        dfs(arr, 0, "", result);
        return result[0];
    }

    private void dfs(List<String> arr, int index, String current, int[] result) {
        if (index == arr.size() && uniqueCharCount(current) > result[0]) {
            result[0] = current.length();
            return;
        }
        if (index == arr.size())
            return;

        dfs(arr, index + 1, current, result);
        dfs(arr, index + 1,  current + arr.get(index), result);
    }

    private int uniqueCharCount(String current) {
        int[] counts = new int[26];
        for (char c : current.toCharArray()) {
            if (counts[c - 'a']++ > 0)
                return -1;
        }
        return current.length();
    }


    /**********************************************************************/


    private int maxLen = 0;

    public int maxLength2(List<String> arr) {
        Set<Character> set = new HashSet<>();
        helper(set, arr, 0);
        return maxLen;
    }

    private void helper(Set<Character> set, List<String> arr, int index) {
        if (index == arr.size())
            return;

        char[] s = arr.get(index).toCharArray();
        boolean unique = true;
        Set<Character> chars = new HashSet<>();

        for (char ch : s) {
            if (chars.contains(ch) || set.contains(ch)) {
                unique = false;
                break;
            }

            chars.add(ch);
        }

        helper(set, arr, index + 1);

        if (unique) {
            maxLen = Math.max(maxLen, set.size() + s.length);

            set.addAll(chars);
            helper(set, arr, index + 1);
            set.removeAll(chars);
        }
    }


    /**********************************************************************/


    int max = 0;

    public int maxLength3(List<String> arr) {
        List<String> strList = new ArrayList<String>();
        List<Integer> flagList = new ArrayList<Integer>();
        // 1. preprocess the list
        for (String str : arr) {
            boolean[] temp = new boolean[26];
            boolean valid = true;
            for (int j = 0; j < str.length(); j++) {
                if (temp[str.charAt(j) - 'a']) {
                    valid = false;
                    break;
                }
                temp[str.charAt(j) - 'a'] = true;
            }
            if (!valid) // eliminate strings that contain duplicate char
                continue;
            int flag = 0;
            for (int j = 0; j < 26; j++) { // record chars contained in a string
                flag |= (temp[j] ? (1 << j) : 0);
            }
            strList.add(str);
            flagList.add(flag);
        }
        dfs(strList, flagList, 0, 0, 0);
        return max;
    }

    public void dfs(List<String> strList, List<Integer> flagList, int index, int flag, int length) {
        max = Math.max(max, length);
        if (index >= strList.size())
            return;
        for (int i = index; i < flagList.size(); i++) {
            if ((flagList.get(i) & flag) == 0) { // judge whether string sequence contains duplicate char via bit manipulation
                dfs(strList, flagList, i + 1, flagList.get(i) | flag, length + strList.get(i).length());
            }
        }
    }

}
