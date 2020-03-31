package bg.leetcode.exercises.itenev.hash_table;

import java.util.*;

/**
 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
 * for example: "ACGAATTCCG". When studying DNA,
 * it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings)
 * that occur more than once in a DNA molecule.
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 */
public class RepeatedDNASequences {

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i + 9 < s.length(); i++) {
            String ten = s.substring(i, i + 10);
            if (!seen.add(ten))
                repeated.add(ten);
        }
        return new ArrayList<>(repeated);
    }

    /***********************************************************************/

    public List<String> findRepeatedDnaSequences2(String s) {
        Map<String, Integer> seen = new HashMap<>();
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index + 10 <= s.length()) {
            //index++ here or at the end
            String subsequence = s.substring(index, index++ + 10);
            seen.put(subsequence, seen.getOrDefault(subsequence, 0) + 1);
            if (seen.get(subsequence) == 2) {
                result.add(subsequence);
            }
        }

        return result;
    }


    /***********************************************************************/

    private static final Map<Character, Integer> A = new HashMap<>();
    private final int A_SIZE_POW_9 = (int) Math.pow(A.size(), 9);

    static {
        A.put('A', 0);
        A.put('C', 1);
        A.put('G', 2);
        A.put('T', 3);
    }

    /**
     * The idea is to use rolling hash technique or in case of string search also known as Rabin-Karp algorithm.
     * As our alphabet A consists of only 4 letters we can be not afraid of collisions.
     * The hash for a current window slice could be found in a constant time
     * by subtracting the former first character times size of the A in the power of 9
     * and updating remaining hash by the standard rule: hash = hash*A.size() + curr_char.
     * <p>
     * https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm
     * https://en.wikipedia.org/wiki/Rolling_hash
     */
    public List<String> findRepeatedDnaSequences3(String s) {
        Set<String> res = new HashSet<>();
        Set<Integer> hashes = new HashSet<>();
        for (int i = 0, rhash = 0; i < s.length(); i++) {
            if (i > 9) rhash -= A_SIZE_POW_9 * A.get(s.charAt(i - 10));
            rhash = A.size() * rhash + A.get(s.charAt(i));
            if (i > 8 && !hashes.add(rhash)) res.add(s.substring(i - 9, i + 1));
        }
        return new ArrayList<>(res);
    }

}
