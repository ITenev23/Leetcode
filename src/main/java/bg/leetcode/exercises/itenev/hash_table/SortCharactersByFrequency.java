package bg.leetcode.exercises.itenev.hash_table;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * <p>
 * Input: "tree"
 * Output: "eert"
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * <p>
 * Input: "Aabb"
 * Output: "bbAa"
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * <p>
 * Input: "cccaaa"
 * Output: "cccaaa"
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);


        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
                (a, b) -> map.get(b) - map.get(a)
        );
        maxHeap.addAll(map.keySet());
        StringBuilder sb = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            char current = maxHeap.remove();
            sb.append(String
                    .valueOf(current)
                    .repeat(map.get(current))
            );
        }

        return sb.toString();
    }

}
