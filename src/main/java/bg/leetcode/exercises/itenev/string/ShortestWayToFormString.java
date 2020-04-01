package bg.leetcode.exercises.itenev.string;

/**
 * From any string, we can form a subsequence by deleting some number of chars
 * (possible no deletions)
 * Return min number of subsequences of source suck that their concatenation
 * equals target, if the task is impossible return -1
 *
 * Input: source = "abc", target = "abcbc"
 * Output: 2 -> abc + bc
 *
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 *
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3 -> xz + y + xz
 */
public class ShortestWayToFormString {

    public static int shortestWay(String source, String target) {
            int numSubsequences = 0;
            String remaining = target;
            while (remaining.length() > 0) {
                StringBuilder sb = new StringBuilder();
                int i = 0;
                int j = 0;
                while (i < source.length() && j < remaining.length()) {
                    if (source.charAt(i++) == remaining.charAt(j))
                        sb.append(remaining.charAt(j++));
                }

                if (sb.length() == 0)
                    return -1;

                numSubsequences++;
                remaining = remaining.substring(sb.length());
            }
            return numSubsequences;
    }

}
