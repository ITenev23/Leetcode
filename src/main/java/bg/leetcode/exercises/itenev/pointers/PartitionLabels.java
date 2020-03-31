package bg.leetcode.exercises.itenev.pointers;

import java.util.ArrayList;
import java.util.List;

/**
 * A string S of lowercase letters is given.
 * We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part,
 * and return a list of integers representing the size of these parts.
 *
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        List<Integer> partitionLengths = new ArrayList<>();
        int[] lastIndexes = new int[26];
        for (int i = 0; i < S.length(); i++) {
            lastIndexes[S.charAt(i) - 'a'] = i;
        }

        int i = 0;
        while (i < S.length()) {
            int end = lastIndexes[S.charAt(i) - 'a'];
            int j = i + 1;
            while (j != end) {
                end = Math.max(end, lastIndexes[S.charAt(j++) - 'a']);
            }
            partitionLengths.add(j - i - 1);
            i = j + 1;
        }

        return partitionLengths;
    }

    /********************************************************************/

    /**
     * We need an array last[char] -> index of S where char occurs last.
     * Then, let anchor and j be the start and end of the current partition.
     * If we are at a label that occurs last at some index after j,
     * we'll extend the partition j = last[c].
     * If we are at the end of the partition (i == j) then we'll append a partition size to our answer,
     * and set the start of our new partition to i+1.
     */
    public List<Integer> partitionLabels2(String S) {
        int[] last = new int[26];

        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0;
        int anchor = 0;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                result.add(i - anchor + 1);
                anchor = i + 1;
            }
        }

        return result;
    }

}
