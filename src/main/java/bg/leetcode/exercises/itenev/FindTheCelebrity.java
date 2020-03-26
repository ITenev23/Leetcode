package bg.leetcode.exercises.itenev;

public class FindTheCelebrity {

    /* The knows API is defined in the parent class */
    private static boolean knows(int a, int b) {
        //returns true if A knows B
        return true;
    }

    public static int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate && knows(candidate, i) || !knows(i, candidate)) {
                return -1;
            }
        }

        return candidate;
    }
}
