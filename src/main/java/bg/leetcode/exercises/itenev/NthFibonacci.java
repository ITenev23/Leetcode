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

}
