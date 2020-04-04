package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given n pairs of parentheses,
 * write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParentheses {

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    public static void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur + "(", open + 1, close, max);
        if (close < open)
            backtrack(ans, cur + ")", open, close + 1, max);
    }

    /****************************************************************************/

    public List<String> generateParenthesisIterative(int n) {
        List<List<String>> result = new ArrayList<>();
        result.add(Collections.singletonList(""));

        for (int i = 1; i <= n; ++i) {
            List<String> list = new ArrayList<>();

            for (int j = 0; j < i; ++j)
                for (String first : result.get(j))
                    for (String second : result.get(i - 1 - j))
                        list.add("(" + first + ")" + second);

            result.add(list);
        }

        return result.get(result.size() - 1);
    }

    /****************************************************************************/

    public static List<String> generateParenthesisRec(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            result.add("");
        } else {
            int number = n - 1;
            for (int i = number; i >= 0; i--) {
                List<String> insertSub = generateParenthesisRec(i);
                List<String> tailSub = generateParenthesisRec(number - i);

                for (String insert : insertSub)
                    for (String tail : tailSub)
                        result.add("(" + insert + ")" + tail);
            }
        }
        return result;
    }
}
