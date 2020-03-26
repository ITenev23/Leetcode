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
            if (second.isEmpty() || second.pop() != current) return false;
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
}
