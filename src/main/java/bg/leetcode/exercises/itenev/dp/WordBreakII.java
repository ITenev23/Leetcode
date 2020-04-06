package bg.leetcode.exercises.itenev.dp;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * 1. The same word in the dictionary may be reused multiple times in the segmentation.
 * 2. You may assume the dictionary does not contain duplicate words.
 * <p>
 * Input: s = "catsanddog" , wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * Input: s = "pineapplepenapple" , wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 */
public class WordBreakII {

    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> result = new ArrayList<>();

        for (int j = s.length() - 1; j >= 0; j--)
            if (dict.contains(s.substring(j)))
                break;
            else if (j == 0)
                return result;


        for (int i = 0; i < s.length() - 1; i++) {
            if (dict.contains(s.substring(0, i + 1))) {
                List<String> strs = wordBreak(s.substring(i + 1), dict);
                if (strs.size() != 0)
                    for (String str : strs) {
                        result.add(s.substring(0, i + 1) + " " + str);
                    }
            }
        }
        if (dict.contains(s))
            result.add(s);

        return result;
    }

    /***********************************************************/

    public List<String> wordBreak2(String s, Set<String> dict) {
        return dfs(s, dict, new HashMap<>());
    }

    public List<String> dfs(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        int n = s.length();

        for (String w : dict) {
            if (!s.startsWith(w)) {
                continue;
            }
            int end = w.length();
            if (end == n) {
                res.add(w);
            } else {
                List<String> sublist = dfs(s.substring(end), dict, memo);
                for (String item : sublist) {
                    item = w + " " + item;
                    res.add(item);
                }
            }
        }

        memo.put(s, res);
        return res;
    }

    /*******************************************************************/

    public List<String> wordBreak3(String s, List<String> wordDict) {
        if (s == null || "".equals(s)) return new ArrayList<>();
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (wordDict.contains(s.substring(0, i + 1))) dp[i] = 1;
            else {
                for (int j = 0; j < i; j++) {
                    if (dp[j] == 1 && wordDict.contains(s.substring(j + 1, i + 1))) {
                        dp[i] = 1;
                        break;
                    }
                }
            }
        }
        if (dp[s.length() - 1] == 0) return new ArrayList<>();
        return backtracking(s, dp, s.length() - 1, wordDict);
    }

    private List<String> backtracking(String s, int[] dp, int end, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            if (dp[i] == 1 && wordDict.contains(s.substring(i + 1, end + 1))) {
                List<String> temp = backtracking(s, dp, i, wordDict);
                for (String str : temp) result.add(str + " " + s.substring(i + 1, end + 1));
            }
        }
        if (wordDict.contains(s.substring(0, end + 1))) result.add(s.substring(0, end + 1));
        return result;
    }
}
