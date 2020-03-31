package bg.leetcode.exercises.itenev.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines,
 * write a function that will return true if the ransom note can be constructed from the magazines,
 * otherwise, it will return false.
 * <p>
 * Each letter in the magazine string can only be used once in your ransom note.
 * <p>
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 */
public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : magazine.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        for (char c : ransomNote.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) == 0)
                return false;

            map.put(c, map.get(c) - 1);
        }
        return true;
    }

    /******************************************************************/

    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] c = new int[26];

        for (char value : magazine.toCharArray())
            c[value - 'a']++;

        for (char value : ransomNote.toCharArray())
            if (c[value - 'a']-- < 1)
                return false;

        return true;
    }

}
