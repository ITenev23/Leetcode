package bg.leetcode.exercises.itenev.string;

/**
 * Given a string of digits S, insert a minimum number of opening and closing parentheses
 * into it such that the resulting string is balanced and each digit d is inside exactly d pairs of matching parentheses.
 *
 * Let the nesting of two parentheses within a string be the substring that occurs strictly between them.
 * An opening parenthesis and a closing parenthesis that is further to its right
 * are said to match if their nesting is empty, or if every parenthesis in their nesting
 * matches with another parenthesis in their nesting.
 * The nesting depth of a position p is the number of pairs of matching
 * parentheses m such that p is included in the nesting of m.
 *
 * For example, in the following strings,
 * all digits match their nesting depth: 0((2)1), (((3))1(2)), ((((4)))), ((2))((2))(1).
 * The first three strings have minimum length among those that have the same digits in the same order,
 * but the last one does not since ((22)1) also has the digits 221 and is shorter.
 *
 * 4 cases:
 * 0000  --> Case #1: 0000
 * 101   --> Case #2: (1)0(1)
 * 111000 -> Case #3: (111)000
 * 1     -->  Case #4: (1)
 */
public class NestingDepth {

    public static void main(String[] args) {
        String input = "42";
        int current = 0;
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            int x = c - '0';
            while (current < x) {
                sb.append('(');
                current++;
            }
            while (current > x){
                sb.append(')');
                current--;
            }
            sb.append(c);
        }
        while (current-- > 0) {
            sb.append(')');
        }

        sb.insert(0, "Output:  ");
        System.out.println(sb.toString());
    }
}
