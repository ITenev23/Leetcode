package bg.leetcode.exercises.itenev.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match,
 * such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * <p>
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * <p>
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");

        if (pattern.length() != strs.length)
            return false;


        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (int i = 0; i < strs.length; i++) {
            if (map.containsKey(pattern.charAt(i))) {
                if (!map.get(pattern.charAt(i)).equals(strs[i]))
                    return false;
            } else {
                if (!set.add(strs[i]))
                    return false;
                map.put(pattern.charAt(i), strs[i]);
            }
        }
        return true;
    }

    /************************************************************************************/

    public boolean wordPattern2(String pattern, String str) {
        String[] arr = str.split(" ");
        HashMap<Character, String> map = new HashMap<>();

        if (arr.length != pattern.length())
            return false;

        for (int i = 0; i < arr.length; i++) {
            char c = pattern.charAt(i);

            if (map.containsKey(c)) {
                if (!map.get(c).equals(arr[i]))
                    return false;
            } else {
                if (map.containsValue(arr[i]))
                    return false;
                map.put(c, arr[i]);
            }
        }
        return true;
    }

    /************************************************************************************/

    public boolean wordPattern3(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        Map index = new HashMap();
        for (Integer i = 0; i < words.length; ++i)
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }
}
