package bg.leetcode.exercises.itenev.recursion;

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

    /*************************************************/

    public int fib(int N) {
        switch(N) {
            case 0 : return 0;
            case 1 : return 1;
            case 2 : return 1;
            case 3 : return 2;
        }

        int[] dp = new int[N];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for(int i = 4; i < N; i++) {
            dp[i] = dp[i -1] + dp[i - 2];
        }

        return dp[N - 1];
    }

}
