package bg.leetcode.exercises.itenev.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 * <p>
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * <p>
 * Input: "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
public class LongestPalindrome {

    public int longestPalindrome(String s) {
        if (s == null || s.length() < 1)
            return 0;
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i)))
                set.remove(s.charAt(i));
            else
                set.add(s.charAt(i));
        }
        if (set.size() <= 1)
            return s.length();
        return s.length() - set.size() + 1;
    }

    /************************************/

    public int longestPalindrome2(String s) {
        if (s == null || s.length() == 0)
            return 0;

        Set<Character> set = new HashSet<>();
        int count = 0;

        for (char c : s.toCharArray())
            if (set.remove(c))
                count++;
            else
                set.add(c);


        return set.isEmpty() ? count * 2 : count * 2 + 1;
    }


    /************************************/

    public int longestPalindrome3(String s) {
        int[] count = new int[128];
        int length = 0;
        for (char c : s.toCharArray()) {
            if (++count[c] == 2) {
                length += 2;
                count[c] = 0;
            }
        }
        return (length == s.length()) ? length : length + 1;
    }

    /*********************************************************/

    public int longestPalindrome4(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        int res = 0;
        int oddNum = 0;

        for (char ch : s.toCharArray())
            if (hash.containsKey(ch))
                hash.put(ch, hash.get(ch) + 1);
            else
                hash.put(ch, 1);


        for (Map.Entry<Character, Integer> entry : hash.entrySet())
            if (entry.getValue() % 2 == 0) {
                res += entry.getValue();
            } else {
                res += entry.getValue() - entry.getValue() % 2;
                oddNum++;
            }


        if (oddNum > 0)
            return res + (oddNum - (oddNum - 1));
        else
            return res;
    }

}
