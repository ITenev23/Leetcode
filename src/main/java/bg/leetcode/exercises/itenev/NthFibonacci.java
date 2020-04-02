package bg.leetcode.exercises.itenev;

public class NthFibonacci {

    public static int getNthFib(int n){
        int[] lastTwo = {0, 1};
        int counter = 3;
        while (counter <= n) {
            int nextFib = lastTwo[0] + lastTwo[1];
            lastTwo[0] = lastTwo[1];
            lastTwo[1] = nextFib;
            counter ++;
        }

        return n > 1 ? lastTwo[1] : lastTwo[0];
    }

    /*************************************************/

    public static long fib(int n, long[] memo){
        if (memo[n] != 0)
            return memo[n];
        if (n < 3)
            return 1;

        long result = fib(n - 1, memo) + fib(n - 2, memo);
        memo[n] = result;
        return result;
    }

}
