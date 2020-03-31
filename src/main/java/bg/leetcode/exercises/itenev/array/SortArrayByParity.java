package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array A of non-negative integers,
 * return an array consisting of all the even elements of A,
 * followed by all the odd elements of A.
 *
 * You may return any answer array that satisfies this condition.
 *
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
public class SortArrayByParity {

    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int rigth = A.length - 1;

        while (left < rigth) {
            if (A[left] % 2 > A[rigth] % 2) {
                int tmp = A[left];
                A[left] = A[rigth];
                A[rigth] = tmp;
            }

            if (A[left] % 2 == 0) left++;
            if (A[rigth] % 2 == 1) rigth--;
        }

        return A;
    }

    /**************************************************************************/


    public int[] sortArrayByParity2(int[] A) {
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                int temp = A[index];
                A[index++] = A[i];
                A[i] = temp;
            }
        }

        return A;
    }

    /**************************************************************************/


    public int[] sortArrayByParity3(int[] A) {
        Integer[] B = new Integer[A.length];
        for (int t = 0; t < A.length; ++t)
            B[t] = A[t];

        Arrays.sort(B, Comparator.comparingInt(a -> a % 2));

        for (int t = 0; t < A.length; ++t)
            A[t] = B[t];
        return A;

        /* Alternative:
        return Arrays.stream(A)
                     .boxed()
                     .sorted((a, b) -> Integer.compare(a%2, b%2))
                     .mapToInt(i -> i)
                     .toArray();
        */
    }

}
