package bg.leetcode.exercises.itenev.string;

import java.util.Stack;

/**
 * Balanced strings are those who have equal quantity of 'L' and 'R' characters.
 * Given a balanced string s split it in the maximum amount of balanced strings.
 * Return the maximum amount of splitted balanced strings.
 * <p>
 * Input: s = "RLRRLLRLRL"
 * Output: 4
 * Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
 * <p>
 * Input: s = "RLLLLRRRLR"
 * Output: 3
 * Explanation: s can be split into "RL", "LLLRRR", "LR", each substring contains same number of 'L' and 'R'.
 * <p>
 * Input: s = "RLRRRLLRLL"
 * Output: 2
 * Explanation: s can be split into "RL", "RRRLLRLL", since each substring contains an equal number of 'L' and 'R'
 */
public class SplitInBalancedStrings {

    public int balancedStringSplit(String s) {
        int balanced = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == 'L' ? 1 : -1;

            if (count == 0)
                balanced++;
        }

        return balanced;
    }

    /*******************************************************************/

    public int balancedStringSplit2(String s) {
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (char ch : s.toCharArray()) {
            //check if char doesn't balance the previous one, or the stack is empty
            if (stack.isEmpty() || stack.peek() == ch)
                stack.push(ch);
                //if chars are balanced - remove the pair
            else
                stack.pop();
            //if stack is empty - all pairs are balanced and we have a balanced substring
            if (stack.isEmpty())
                res++;
        }
        return res;
    }

}
