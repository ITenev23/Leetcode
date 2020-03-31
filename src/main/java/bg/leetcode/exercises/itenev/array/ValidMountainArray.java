package bg.leetcode.exercises.itenev.array;

/**
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 * Recall that A is a mountain array if and only if:
 * 1. A.length >= 3
 * 2. There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * Input: [0,2,3,4,5,2,1,0]
 * Output: true
 * <p>
 * Input: [3,5,5]
 * Output: false
 * <p>
 * Input: [0,3,2,1]
 * Output: true
 */
public class ValidMountainArray {

    public boolean validMountainArray(int[] A) {
        int left = 0;
        int right = A.length;

        // walk up
        while (left + 1 < right && A[left] < A[left + 1])
            left++;

        // peak can't be first or last
        if (left == 0 || left == right - 1)
            return false;

        // walk down
        while (left + 1 < right && A[left] > A[left + 1])
            left++;

        return left == right - 1;
    }

    /*********************************************************************/

    public boolean validMountainArray2(int[] A) {
        int left = 0;
        while (left < A.length && left + 1 < A.length && A[left] < A[left + 1]) {
            left++;
        }
        if (left == 0 || left + 1 >= A.length) {
            return false;
        }
        while (left < A.length && left + 1 < A.length) {
            if (A[left] <= A[left + 1]) {
                return false;
            }
            left++;
        }
        return true;
    }

}
