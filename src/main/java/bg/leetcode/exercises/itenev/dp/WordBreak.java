package bg.leetcode.exercises.itenev.dp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 1. The same word in the dictionary may be reused multiple times in the segmentation.
 * 2. You may assume the dictionary does not contain duplicate words.
 * <p>
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * <p>
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 */
public class WordBreak {

    /**
     * ----- if (j == 0 || dp[j - 1]) ------
     * The for loop is looking at ranges or substrings.
     * If we know that the current substring is in the dictionary AND
     * the substring before it is also in the dictionary then we know that the str.substring(0, i) is true.
     * J == 0 because the first substring has nothing before it (dp[j- 1] does not exist).
     * <p>
     * ------- left substring ---- | substring (j, i + 1) | -------right substring ------ |
     * <p>
     * If we know that str.substring(j,i+1) is in the dictionary,
     * we can only mark it true if the left substring is also in the dictionary.
     * Then, for the right substring, it is str.substring(i + 1, end).
     * You can only mark dp[end] is true if you know the substring up to i are in dictionary.
     * This is what makes this a dynamic programming solution.
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        int len = s.length();
        boolean[] dp = new boolean[len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                // NOTE: we are going to update dp only for below two scenarios, so avoided
                // making unecessary computation until this condition is met
                if (j == 0 || dp[j - 1]) {
                    String sub = s.substring(j, i + 1);
                    if (wordDict.contains(sub)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[len - 1];
    }

    /*****************************************************************************/

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<Integer> set = new HashSet<>();
        return dfs(s, 0, wordDict, set);
    }

    private boolean dfs(String s, int index, List<String> dict, Set<Integer> set) {
        if (index == s.length())
            return true;
        // check memory
        if (set.contains(index))
            return false;

        for (int i = index + 1; i <= s.length(); i++) {
            String t = s.substring(index, i);
            if (dict.contains(t))
                if (dfs(s, i, dict, set))
                    return true;
                else
                    set.add(i);
        }
        set.add(index);
        return false;
    }

    /*****************************************************************************/

    public boolean wordBreak(String s, Set<String> wordDict) {
        return dfs2(s, wordDict, new HashSet<>());
    }

    private boolean dfs2(String s, Set<String> wordDict, Set<String> checked) {
        if (s.isEmpty())
            return true;
        if (checked.contains(s))
            return false;

        checked.add(s);

        for (String w : wordDict)
            if (s.startsWith(w) && dfs2(s.substring(w.length()), wordDict, checked))
                return true;

        return false;
    }
}
