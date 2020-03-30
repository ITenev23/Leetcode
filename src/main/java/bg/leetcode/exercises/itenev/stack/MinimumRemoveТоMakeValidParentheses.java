package bg.leetcode.exercises.itenev.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * 1. It is the empty string, contains only lowercase characters, or
 * 2. It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * 3. It can be written as (A), where A is a valid string.
 * <p>
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * <p>
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * <p>
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 */
public class MinimumRemoveТоMakeValidParentheses {

    public String minRemoveToMakeValid(String s) {
        Stack<Character> st = new Stack<>();
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push('(');
                arr.add(i);
            } else if (s.charAt(i) == ')') {
                if (!st.isEmpty() && st.peek() == '(') {
                    st.pop();
                    arr.remove(arr.size() - 1);
                } else {
                    st.push(')');
                    arr.add(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if (arr.size() == 0) {
            return s;
        }
        sb.append(s, 0, arr.get(0));
        for (int i = 0; i < arr.size() - 1; i++) {
            sb.append(s, arr.get(i) + 1, arr.get(i + 1));
        }
        sb.append(s.substring(arr.get(arr.size() - 1) + 1));
        return sb.toString();
    }

    /*****************************************************************/

    public String minRemoveToMakeValid2(String s) {
        int il = 0;
        int ir = 0;
        List<Integer> delete = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                il++;
                left.add(i);
            }
            if (s.charAt(i) == ')') {
                ir++;
            }
            if (il < ir) {
                ir--;
                delete.add(i);
            }
        }
        for (int k = 0; k < il - ir; k++) {
            delete.add(left.get(left.size() - 1 - k));
        }
        StringBuilder sb = new StringBuilder(s);
        for (int index : delete) {
            sb.replace(index, index + 1, " ");
        }
        return sb.toString().replace(" ", "");
    }

    /********************************************************/

    public String minRemoveToMakeValid3(String s) {
        StringBuilder sb = new StringBuilder();
        int c = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                if (c == 0) continue;
                --c;
            } else if (ch == '(') ++c;
            sb.append(ch);
        }
        c = 0;
        int i = sb.length();
        while (--i >= 0) {
            if (sb.charAt(i) == ')') ++c;
            else if (sb.charAt(i) == '(') {
                if (c == 0) {
                    sb.deleteCharAt(i);
                    continue;
                }
                --c;
            }
        }
        return sb.toString();
    }

}
