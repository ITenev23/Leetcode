package bg.leetcode.exercises.itenev.math;

import java.util.*;

/**
 * Given a positive int n, print all jumping numbers smaller than or equal to n.
 * A number is called a jumping number if all adjacent digits in it differ by 1.
 * For example, 8987 and 4343456 are jumping numbers, but 796 and 89098 are not.
 * All single digit numbers are considered as jumping numbers.
 * <p>
 * Input: 105
 * Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98, 101]
 */
public class JumpingNumbers {

    public static void test() {
        int num = 104;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            dfs(num, set, i);
        }
        List<Integer> res = new ArrayList<>(set);
        Collections.sort(res);
        System.out.println(res);
    }

    static void dfs(int num, Set<Integer> res, int tmp) {
        if (tmp > num)
            return;
        if (!res.add(tmp))
            return;
        if (tmp % 10 == 0) {
            dfs(num, res, tmp * 10 + 1);
        } else if (tmp % 10 == 9) {
            dfs(num, res, tmp * 10 + 8);
        } else {
            dfs(num, res, tmp * 10 + tmp % 10 - 1);
            dfs(num, res, tmp * 10 + tmp % 10 + 1);
        }
    }
}
