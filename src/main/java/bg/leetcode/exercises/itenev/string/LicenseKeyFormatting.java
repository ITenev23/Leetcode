package bg.leetcode.exercises.itenev.string;

/**
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes.
 * The string is separated into N+1 groups by N dashes.
 * <p>
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters,
 * except for the first group which could be shorter than K, but still must contain at least one character. Furthermore,
 * there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 * <p>
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 * <p>
 * Input: S = "5F3Z-2e-9-w", K = 4
 * Output: "5F3Z-2E9W"
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * <p>
 * Input: S = "2-5g-3-J", K = 2
 * Output: "2-5G-3J"
 * Explanation: The string S has been split into three parts,
 * each part has 2 characters except the first part as it could be shorter as mentioned above.
 */
public class LicenseKeyFormatting {

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        int i = S.length() - 1;
        while (i >= 0) {
            char current = Character.toUpperCase(S.charAt(i));
            if (current == '-')
                i--;
            else if (count != 0 && count % K == 0) {
                sb.insert(0, "-");
                count = 0;
            } else {
                sb.insert(0, current);
                count++;
                i--;
            }
        }

        return sb.toString();
    }

    /************************************************************/

    public String licenseKeyFormatting2(String S, int K) {
        S = S.toUpperCase();
        S = S.replaceAll("-", "");

        StringBuilder sb = new StringBuilder(S);
        for (int i = S.length() - K; i > 0; i = i - K) {
            sb.insert(i, "-");
        }
        return sb.toString();
    }

}
