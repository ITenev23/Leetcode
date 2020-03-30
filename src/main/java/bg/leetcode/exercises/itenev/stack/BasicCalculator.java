package bg.leetcode.exercises.itenev.stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ),
 * the plus + or minus sign -, non-negative integers and empty spaces .
 * <p>
 * Input: " 2-1 + 2 "
 * Output: 3
 * <p>
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * <p>
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {

    private int i;

    public int calculate(String s) {
        int operand = 0;
        int result = 0;
        //positive or negative
        int nextOperandSign = 1;

        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch == ' ' || Character.isDigit(ch)) {
                operand = (ch == ' ') ? operand : 10 * operand + (ch - '0');

            } else if (ch == '(') {
                operand = calculate(s);

            } else if (ch == ')') {
                break; // Sub-expression we were evaluating has ended. Exit now...

            } else {
                // If we're here, we just read the operator for the next operand/expression.
                // Evaluate the existing expression already read, reset operand, set sign for next incoming operand.
                result += nextOperandSign * operand;
                nextOperandSign = ch == '+' ? 1 : -1;
                operand = 0;
            }
        }

        return result + (nextOperandSign * operand);
    }

}
