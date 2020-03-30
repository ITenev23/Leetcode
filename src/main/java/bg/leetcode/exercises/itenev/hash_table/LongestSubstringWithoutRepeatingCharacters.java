package bg.leetcode.exercises.itenev.hash_table;

import java.util.*;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring,
 * "pwke" is a subsequence and not a
 */
public class LongestSubstringWithoutRepeatingCharacters {

    //Sliding Window 1
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j)))
                max = Math.max(max, map.get(s.charAt(j)));

            res = Math.max(res, j - max + 1);
            map.put(s.charAt(j), j + 1);
        }

        return res;
    }

    //Sliding Window 2
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() < 1)
            return 0;
        Map<Character, Integer> uniqueChars = new HashMap<>();
        int maxLen = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            if (uniqueChars.containsKey(s.charAt(i))) {
                int index = uniqueChars.get(s.charAt(i));
                start = Math.max(index, start);
            }

            uniqueChars.put(s.charAt(i), i + 1);

            if (i - start + 1 > maxLen)
                maxLen = i - start + 1;
        }

        return maxLen;
    }

    public int lengthOfLongestSubstring4(String s) {
        int maxVal = 0;
        int j = 0;
        int i = 0;

        Set<Character> set = new LinkedHashSet<>();

        while (i < s.length()) {
            if (set.add(s.charAt(i))) {
                i++;
                maxVal = Math.max(maxVal, set.size());
            } else {
                set.remove(s.charAt(j));
                j++;
            }
        }

        return maxVal;
    }

}
