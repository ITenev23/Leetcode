package bg.leetcode.exercises.itenev.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Given an array strings, determine whether it follows the sequence given in the patterns array.
 * In other words, there should be no i and j for which:
 * strings[i] = strings[j] && patterns[i] ≠ patterns[j] ||
 * strings[i] ≠ strings[j] && patterns[i] = patterns[j].
 * <p>
 * For strings = ["cat", "dog", "dog"] and patterns = ["a", "b", "b"], the output should be
 * areFollowingPatterns(strings, patterns) = true;
 * For strings = ["cat", "dog", "doggy"] and patterns = ["a", "b", "b"], the output should be
 * areFollowingPatterns(strings, patterns) = false.
 */
public class FollowingPatterns {

    boolean areFollowingPatterns(String[] strings, String[] patterns) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i < strings.length; i++) {
            boolean added = set1.add(strings[i]);
            boolean added1 = set2.add(patterns[i]);
            if (added != added1) {
                return false;
            }
        }
        return true;
    }

    /************************************************************************/

    boolean areFollowingPatterns2(String[] strings, String[] patterns) {
        Map<String, String> map = new HashMap<>();
        return IntStream.range(0, strings.length)
                .allMatch(i -> map.computeIfAbsent(patterns[i], p -> strings[i]).equals(strings[i])) &&
                map.size() == new HashSet<>(map.values()).size();
    }

    /************************************************************************/

    boolean areFollowingPatterns3(String[] strings, String[] patterns) {
        Map<String, String> patternCache = new HashMap<>();
        Map<String, String> valueCache = new HashMap<>();

        for (int i = 0; i < patterns.length; ++i) {
            String pattern = patterns[i];
            String value = strings[i];
            if (patternCache.containsKey(pattern) && !patternCache.get(pattern).equals(value)) {
                return false;
            } else if (valueCache.containsKey(value) && !valueCache.get(value).equals(pattern)) {
                return false;
            } else {
                patternCache.put(pattern, value);
                valueCache.put(value, pattern);
            }
        }
        return true;
    }
}
