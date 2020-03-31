package bg.leetcode.exercises.itenev.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Add the mathematical operators + or - before any of the digits in
 * the decimal numeric string 123456789 such that the resulting mathematical expression adds up to 100.
 * Return all possible solutions.
 */
public class SumTo100 {

    public static void test() {
        String s = "123456789";
        int target = 100;
        List<List<String>> fullCombinations = dfs(s);
        List<String> res = new ArrayList<>();
        for (List<String> ele : fullCombinations) {
            dfs2(ele, res, 0, target, 0, "0");
        }
        for (String str : res) {
            System.out.println(str);
        }
    }

    static void dfs2(List<String> ele, List<String> res, int i, int target, int tmpSum, String tmp) {
        if (i == ele.size()) {
            if (target == tmpSum)
                if (tmp.charAt(1) == '+')
                    res.add(tmp.substring(2));
                else
                    res.add(tmp.substring(1));
        } else if (i > ele.size()) {
            return;
        } else {
            int curInt = Integer.parseInt(ele.get(i));
            dfs2(ele, res, i + 1, target, tmpSum + curInt, tmp + "+" + ele.get(i));
            dfs2(ele, res, i + 1, target, tmpSum - curInt, tmp + "-" + ele.get(i));
        }
    }

    static List<List<String>> dfs(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() != 1) {
            for (int i = 0; i < s.length() - 1; i++) {
                String l = s.substring(0, i + 1);
                String r = s.substring(i + 1);
                for (List<String> strs : dfs(r)) {
                    List<String> tmp = new ArrayList<>();
                    tmp.add(l);
                    tmp.addAll(strs);
                    res.add(tmp);
                }
            }
        }
        res.add(Collections.singletonList(s));
        return res;
    }

    /******************************************************************/

    private static final String DIGITS = "123456789";

    public static void test2(int target) {
        System.out.println(String.format("Sum to %d -----------------", target));
        int count = 1;
        for (String s : sumTo(target)) {
            System.out.println(count + ": " + s);
            count++;
        }
    }

    public static List<String> sumTo(int target) {
        List<String> result = new ArrayList<>();
        generate(0, 0, target, new StringBuilder(), result);
        return result;
    }

    private static void generate(int start, int sum, int target, StringBuilder path, List<String> result) {
        if (start == DIGITS.length()) {
            if (sum == target) {
                result.add(path.toString());
            }
            return;
        }

        int len = path.length();
        int num = 0;
        for (int i = start; i < DIGITS.length(); i++) {
            num = 10 * num + DIGITS.charAt(i) - '0';

            if (start != 0) path.append('+');
            path.append(num);
            generate(i + 1, sum + num, target, path, result);
            path.setLength(len);

            path.append('-');
            path.append(num);
            generate(i + 1, sum - num, target, path, result);
            path.setLength(len);
        }
    }

}
