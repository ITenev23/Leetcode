package bg.leetcode.exercises.itenev.recursion;

/**
 * On the first row, we write a 0. Now in every subsequent row,
 * we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
 * Given row N and index K, return the K-th indexed symbol in row N.
 * (The values of K are 1-indexed.) (1 indexed).
 * <p>
 * Input: N = 1, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 1
 * Output: 0
 * <p>
 * Input: N = 2, K = 2
 * Output: 1
 * <p>
 * Input: N = 4, K = 5
 * Output: 1
 * <p>
 * Explanation:
 * row 1: 0
 * row 2: 01
 * row 3: 0110
 * row 4: 01101001
 */
public class KSymbolGrammar {

    private int recrusion(int N, int K) {
        if (N == 1)
            return 0;
        if (K % 2 == 0)
            return recrusion(N - 1, K / 2) == 0 ? 1 : 0;
        else
            return recrusion(N - 1, (K + 1) / 2) == 0 ? 0 : 1;
    }

    public int kthGrammar(int N, int K) {
        if (N == 0)
            return 0;

        // Recursively get the value of parent symbol. Can be memorized but it's too much of chore in Java.
        int result = this.kthGrammar(N - 1, (K - 1) / 2 + 1);

        // If K is odd element is same as parent symbol else it's invert of parent symbol.
        return K % 2 != 0 ? result : result * -1 + 1;
    }
}
