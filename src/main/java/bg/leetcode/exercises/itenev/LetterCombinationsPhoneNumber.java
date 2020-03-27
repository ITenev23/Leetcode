package bg.leetcode.exercises.itenev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive,
 * return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 * <p>
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * Although the above answer is in lexicographical order,
 * your answer could be in any order you want.
 */
public class LetterCombinationsPhoneNumber {

    private static Map<String, String> phone = new HashMap<>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    private static List<String> result = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return result;
    }

    public void backtrack(String combination, String next_digits) {
        if (next_digits.length() == 0) {
            // the combination is done
            result.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }

    public List<String> letterCombinations2(String digits) {
        List<String> res = new ArrayList<>();

        if (digits == null || digits.length() == 0)
            return res;

        String[] mapping = {
          "0",
          "1",
          "abc",
          "def",
          "ghi",
          "jkl",
          "mno",
          "pqrs",
          "tuv",
          "wxyz",
        };

        letterCombRecursive(res, digits, "", 0, mapping);
        return res;
    }

    public void letterCombRecursive(List<String> res, String digits, String current, int index, String[] mappings) {
        if (index == digits.length()) {
            res.add(current);
            return;
        }

        //char - '0' becomes int
        String letters = mappings[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            letterCombRecursive(res, digits, current + letters.charAt(i), index + 1, mappings);
        }
    }

}
