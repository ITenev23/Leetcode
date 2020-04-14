package bg.leetcode.exercises.itenev.string;

/**
 * You are given a string s containing lowercase English letters,
 * and a matrix shift, where shift[i] = [direction, amount]:
 * <p>
 * direction can be 0 (for left shift) or 1 (for right shift).
 * amount is the amount by which string s is to be shifted.
 * A left shift by 1 means remove the first character of s and append it to the end.
 * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
 * Return the final string after all operations.
 * <p>
 * Input: s = "abc", shift = [[0,1],[1,2]]
 * Output: "cab"
 * Explanation:
 * [0,1] means shift to left by 1. "abc" -> "bca"
 * [1,2] means shift to right by 2. "bca" -> "cab"
 * <p>
 * Input: s = "abcdefg", shift = [[1,1],[1,1],[0,2],[1,3]]
 * Output: "efgabcd"
 * Explanation:
 * [1,1] means shift to right by 1. "abcdefg" -> "gabcdef"
 * [1,1] means shift to right by 1. "gabcdef" -> "fgabcde"
 * [0,2] means shift to left by 2. "fgabcde" -> "abcdefg"
 * [1,3] means shift to right by 3. "abcdefg" -> "efgabcd"
 */
public class PerformStringShifts {

    public static String stringShift(String s, int[][] shift) {
        if (shift == null || s.length() <= 1)
            return s.length() == 1 ? s : "";

        int left = 0;
        int right = 0;

        for (int[] i : shift) {
            if (i[0] == 0)
                left += i[1];
            else
                right += i[1];
        }
        if (left == right)
            return s;
        else if (left > right)
            return leftrotate(s, (left - right) % s.length());
        else
            return rightrotate(s, (right - left) % s.length());
    }

    static String leftrotate(String str, int d) {
        return str.substring(d) + str.substring(0, d);
    }

    static String rightrotate(String str, int d) {
        return leftrotate(str, str.length() - d);
    }
}
