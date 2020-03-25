package bg.leetcode.exercises.itenev.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 * <p>
 * But for multiples of 3 it should output “Fizz” instead of the number
 * and for the multiples of 5 output “Buzz”.
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 */
public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (i % 15 == 0) res.add("FizzBuzz");
            else if (i % 3 == 0) res.add("Fizz");
            else if (i % 5 == 0) res.add("Buzz");
            else res.add(Integer.toString(i));
        }

        return res;
    }

    public List<String> fizzBuzz2(int n) {
        List<String> str = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            str.add(i % 15 == 0 ? "FizzBuzz" : (i % 5 == 0 ? "Buzz" : (i % 3 == 0 ? "Fizz" : "" + i)));
        }
        return str;
    }

}
