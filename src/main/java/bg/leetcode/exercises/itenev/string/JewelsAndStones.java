package bg.leetcode.exercises.itenev.string;

import java.util.HashSet;
import java.util.Set;

/**
 * You're given strings J representing the types of stones that are jewels,
 * and S representing the stones you have.
 * Each character in S is a type of stone you have.
 * You want to know how many of the stones you have are also jewels.
 * <p>
 * The letters in J are guaranteed distinct,
 * and all characters in J and S are letters.
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 * <p>
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 * <p>
 * Input: J = "z", S = "ZZ"
 * Output: 0
 * <p>
 * The characters in J are distinct.
 */
public class JewelsAndStones {

    public int numJewelsInStones(String J, String S) {
        if (J == null || J.isEmpty() || S == null || S.isEmpty())
            return 0;

        int count = 0;

        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < J.length(); j++) {
                if (J.charAt(j) == S.charAt(i)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    public int numJewelsInStones2(String J, String S) {
        if (J == null || J.isEmpty() || S == null || S.isEmpty())
            return 0;

        Set<Character> jews = new HashSet<>();
        for (char c : J.toCharArray()) {
            jews.add(c);
        }

        int res = 0;
        for (char c : S.toCharArray()) {
            if (jews.contains(c))
                res++;
        }

        return res;
    }

}
