package bg.leetcode.exercises.itenev.array;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.
 * (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * If it cannot be done, return -1.
 * <p>
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes,
 * we can make every value in the top row equal to 2, as indicated by the second figure.
 * <p>
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 */
public class MinimumDominoRotationsForEqualRow {

    /**
     * countSum is total number of occurrences with each index in either A or B
     * countA is total number of occurrences with each index only in A but not in B
     * countB is total number of occurrences with each index only in B but not in A
     */
    public int minDominoRotations(int[] A, int[] B) {
        int len = A.length;
        int[] countSum = new int[7];
        int[] countA = new int[7];
        int[] countB = new int[7];

        for (int i = 0; i < len; i++) {
            if (A[i] == B[i])
                countSum[A[i]]++;
            else {
                countSum[A[i]]++;
                countSum[B[i]]++;
                countA[A[i]]++;
                countB[B[i]]++;
            }
        }

        for (int i = 1; i <= 6; i++) {
            if (countSum[i] == len)
                return Math.min(countA[i], countB[i]);
        }

        return -1;
    }

    /********************************************************************/


    /**
     * There are 5 possible situations:
     * After moving, array A just contains A[0];
     * After moving, array A just contains B[0];
     * After moving, array B just contains A[0];
     * After moving, array B just contains B[0];
     * After moving, array A and B can't just contain the same value, return -1.
     */
    public int minDominoRotations2(int[] A, int[] B) {
        int move1 = helper(A, B, A[0]);
        int move2 = helper(A, B, B[0]);
        if (move1 == -1 && move2 == -1)
            return -1;
        if (move1 != -1 && move2 != -1)
            return Math.min(move1, move2);

        return move1 == -1 ? move2 : move1;

    }

    public int helper(int[] A, int[] B, int target) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] != target && B[i] == target) count1++;
            if (B[i] != target && A[i] == target) count2++;
            if (B[i] != target && A[i] != target) return -1;
        }

        return Math.min(count1, count2);
    }


    /*******************************************************************************/

    /**
     * The idea is that for each domino we can either swap it or leave it
     * an solution possible or not possible at all.
     * Also there are 6 possible values, so we can check every value.
     * Start loop for values from 1 to 6.
     * For each one keep count1 and count2 for solution on upper and lower part respectively.
     * Check every domino anf current value.
     * There are four cases overall - either value is already there, so no swap required,
     * value is on the opposite side (up and down separately) or value is not present
     * on either of sides - then solution is not possible for this number,
     * break out of the loop and go to next value.
     *
     * O(n) time - 6 time iterate over all n dominos. O(1) space - keep all results in several variables.
     */
    public int minDominoRotations3(int[] A, int[] B) {
        int res = Integer.MAX_VALUE;

        for (int i = 1; i <= 6; i++) {
            int c1 = 0;
            int c2 = 0;

            for (int n = 0; n < A.length; n++) {
                if (A[n] != i && B[n] == i)
                    c1++;
                else if (A[n] == i && B[n] != i)
                    c2++;
                else if (A[n] == i && B[n] == i)
                    continue;

                break;

            }
            res = Math.min(res, c1);
            res = Math.min(res, c2);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }


    /*******************************************************************************/



    public int minDominoRotations4(int[] A, int[] B) {
        int topCount = swap(A, B, A[0]);
        int bottomCount = swap(A, B, B[0]);

        if(topCount == -1 && bottomCount == -1) {
            return -1;
        } else if (topCount == -1 || bottomCount == -1) {
            // has a single solution
            return Math.max(topCount, bottomCount);
        } else {
            // chose the faster solution
            return Math.min(topCount, bottomCount);
        }
    }

    private int swap(int[] A, int[] B, int comparedValue) {
        int topSwapCount = 0;
        int bottomSwapCount = 0;

        for(int i = 0; i < A.length; i++) {
            int top = A[i];
            int bottom = B[i];

            if(top != comparedValue && bottom != comparedValue) {
                return -1;
            } else if(top != comparedValue) {
                topSwapCount++;
            } else if(bottom != comparedValue) {
                bottomSwapCount++;
            }
        }
        return Math.min(topSwapCount, bottomSwapCount);
    }

}
