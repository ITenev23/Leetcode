package bg.leetcode.exercises.itenev.string;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * <p>
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * <p>
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int max = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty())
                    stack.push(i);
                else
                    max = Math.max(max, i - stack.peek());

            }

        return max;
    }

    /***********************************************/

    public int longestValidParenthesesDP(String s) {
        int max = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(')
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(')
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;

                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }


    /***********************************************/

    public static int longestValidParentheses2(String s) {
        int left = 0;
        int right = 0;
        int maxlength = 0;

        //traversing from the left
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;

            if (left == right)
                maxlength = Math.max(maxlength, 2 * right);
            else if (right >= left)
                left = right = 0;

        }

        left = right = 0;
        //traversing from the right
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(')
                left++;
            else
                right++;

            if (left == right)
                maxlength = Math.max(maxlength, 2 * left);
            else if (left >= right)
                left = right = 0;

        }
        return maxlength;
    }

    /*******************************************************/

    public int longestValidParenthesesBruteForce(String s) {
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                stack.push('(');
            else if (!stack.empty() && stack.peek() == '(')
                stack.pop();
            else
                return false;
        }
        return stack.empty();
    }
}
