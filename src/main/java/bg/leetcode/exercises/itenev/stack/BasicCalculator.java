package bg.leetcode.exercises.itenev.stack;

import java.util.Stack;

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

    /**
     * 1.Start from +1 sign and scan s from left to right;
     * 2.if c == digit: This number = Last digit * 10 + This digit;
     * 3.if c == '+': Add num to result before this sign; This sign = Last context sign * 1; clear num;
     * 4.if c == '-': Add num to result before this sign; This sign = Last context sign * -1; clear num;
     * 5.if c == '(': Push this context sign to stack;
     * 6.if c == ')': Pop this context and we come back to last context;
     * 7.Add the last num. This is because we only add number after '+' / '-'.
     */
    public int calculate(String s) {
        if(s == null)
            return 0;

        int result = 0;
        int sign = 1;
        int num = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(sign);

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');

            } else if(c == '+' || c == '-') {
                result += sign * num;
                sign = stack.peek() * (c == '+' ? 1: -1);
                num = 0;

            } else if(c == '(') {
                stack.push(sign);

            } else if(c == ')') {
                stack.pop();
            }
        }

        result += sign * num;
        return result;
    }

    /****************************************************/

    /**
     * Only 5 possible input we need to pay attention:
     *
     * 1.digit: it should be one digit from the current number
     * 2.'+': number is over, we can add the previous number and start a new number
     * 3.'-': same as above
     * 4.'(': push the previous result and the sign into the stack, set result to 0,
     *      just calculate the new result within the parenthesis.
     * 5.')': pop out the top two numbers from stack, first one is the sign before this pair of parenthesis,
     *      second is the temporary result before this pair of parenthesis. We add them together.
     *
     * Finally if there is only one number, from the above solution,
     * we haven't add the number to the result, so we do a check see if the number is zero.
     */
    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (int) (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop();    //stack.pop() is the sign before the parenthesis
                result += stack.pop();   //stack.pop() now is the result calculated before the parenthesis

            }
        }
        if (number != 0) result += sign * number;
        return result;
    }

    /************************************************************/

    private int i;

    public int calculateRec(String s) {
        int operand = 0;
        int result = 0;
        //positive or negative
        int nextOperandSign = 1;

        while (i < s.length()) {
            char ch = s.charAt(i++);

            if (ch == ' ' || Character.isDigit(ch)) {
                operand = (ch == ' ') ? operand : 10 * operand + (ch - '0');

            } else if (ch == '(') {
                operand = calculateRec(s);

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
