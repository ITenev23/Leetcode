package bg.leetcode.exercises.itenev.math;

/**
 * Given two integers ‘n’ and ‘sum’,
 * find count of all n digit numbers with sum of digits as ‘sum’.
 * Leading 0’s are not counted as digits.
 * <p>
 * Input:  n = 2, sum = 2
 * Output: 2
 * Explanation: Numbers are 11 and 20
 * <p>
 * Input:  n = 2, sum = 5
 * Output: 5
 * Explanation: Numbers are 14, 23, 32, 41 and 50
 * <p>
 * Input:  n = 3, sum = 6
 * Output: 21
 */
public class CountDigit {

    public static void main(String[] args) {
        int n = 2, sum = 5;
        System.out.println(finalCount(n, sum));
    }

    static int finalCount(int n, int sum) {
        int ans = 0;

        for (int i = 1; i <= 9; i++)
            if (sum - i >= 0)
                ans += countRec(n - 1, sum - i);

        return ans;
    }

    static int countRec(int n, int sum) {
        if (n == 0)
            return sum == 0 ? 1 : 0;

        if (sum == 0)
            return 1;

        int ans = 0;

        // Traverse through every digit and count
        // numbers beginning with it using recursion
        for (int i = 0; i <= 9; i++)
            if (sum - i >= 0)
                ans += countRec(n - 1, sum - i);

        return ans;
    }

}
