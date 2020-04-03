package bg.leetcode.exercises.itenev.stack;

import java.util.Stack;

/**
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 */
public class BackspaceStringCompare {

    public static boolean backspace(String s, String t) {

        Stack<Character> first = fillStack(s);
        Stack<Character> second = fillStack(t);

        while (!first.isEmpty()) {
            Character current = first.pop();
            if (second.isEmpty() || second.pop() != current)
                return false;
        }

        return second.isEmpty();
    }

    private static Stack<Character> fillStack(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c != '#') stack.push(c);
            else if (!stack.isEmpty()) stack.pop();
        }
        return stack;
    }

    /*************************************************/

    public boolean backspaceCompare(String S, String T) {
        String x = deleteChars(S);
        String y = deleteChars(T);

        return x.equals(y);
    }

    public String deleteChars(String S) {
        Stack<Character> ans = new Stack<>();
        for (char c : S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }


    /*************************************************/

    public boolean backspaceCompare2(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else break;
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else break;
            }

            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;

            if ((i >= 0) != (j >= 0))
                return false;

            i--;
            j--;
        }
        return true;
    }
}
