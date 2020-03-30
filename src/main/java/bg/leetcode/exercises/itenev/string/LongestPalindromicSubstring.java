package bg.leetcode.exercises.itenev.string;

/**
 * Given a string s, find the longest palindromic substring in s.
 * You may assume that the maximum length of s is 1000.
 * <p>
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * <p>
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0)
            return "";

        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0;
        int end = 0;
        int max = 0;

        for(int i = 0; i < s.length(); i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j+1][i-1])) {
                    dp[j][i] = true;
                }
                if(dp[j][i] && max < i - j + 1) {
                    max = i - j + 1;
                    start = j;
                    end = i;
                }
            }
        }
        return s.substring(start, end + 1);
    }

    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 1)
            return "";

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left;
        int R = right;

        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    StringBuilder longest = new StringBuilder("");

    public String longestPalindrome3(String s) {
        if (s.length() <= 1) return s;

        for (int i = 0; i < s.length(); i++) {
            expand(s, longest, i, i);
            expand(s, longest, i, i + 1);
        }

        return longest.toString();
    }

    private void expand(String s, StringBuilder longest, int i, int j) {
        while (i >= 0 && j < s.length()) {
            if (s.charAt(i) == s.charAt(j)) {
                if (j - i + 1 > longest.length()) {
                    longest.delete(0, longest.length());
                    longest.append(s.substring(i, j + 1));
                }
                i--;
                j++;
            }
            else
                break;
        }
    }

}
