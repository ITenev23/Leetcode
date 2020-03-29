package bg.leetcode.exercises.itenev.array;

import java.util.Arrays;

/**
 * Given an array of integers A sorted in non-decreasing order,
 * return an array of the squares of each number,
 * also in sorted non-decreasing order.
 * <p>
 * Input: [-4,-1,0,3,10]
 * Output: [0,1,9,16,100]
 * <p>
 * Input: [-7,-3,2,3,11]
 * Output: [4,9,9,49,121]
 */
public class SquaresSortedArray {

    public int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];
        for (int i = 0; i < A.length; ++i)
            ans[i] = (int)Math.pow(A[i], 2);

        Arrays.sort(ans);
        return ans;
    }

    public int[] sortedSquaresWithPointers(int[] A) {
        int[] res = new int[A.length];
        int left = 0;
        int right = A.length - 1;
        int index = A.length - 1;

        while (left <= right) {
            if (Math.abs(A[left]) >= Math.abs(A[right])) {
                res[index--] = (int) Math.pow(A[left], 2);
                left++;
            } else {
                res[index--] = (int) Math.pow(A[right], 2);
                right--;
            }
        }
        return res;
    }

    public int[] sortedSquares2(int[] A) {
        return  Arrays.stream(A).map(a -> a*a).sorted().toArray();
    }

}
