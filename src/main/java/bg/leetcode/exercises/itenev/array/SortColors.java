package bg.leetcode.exercises.itenev.array;

/**
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent,
 * with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class SortColors {

    public void sortColors(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int i = 0;

        while (i <= right) {
            if (A[i] == 0) {
                A[i] = A[left];
                A[left++] = 0;
            }
            if (A[i] == 2) {
                A[i] = A[right];
                A[right--] = 2;
                i--;
            }
            i++;
        }
    }

    /***********************************************/

    public void sortColors2(int[] A) {
        int left = 0;
        int right = A.length - 1;
        int i = 0;

        while (i <= right && i < A.length)
            if (A[i] == 0 && i > left)
                swap(A, i, left++);
            else if (A[i] == 2 && i < right)
                swap(A, i, right--);
            else
                i++;
    }

    private void swap(int[] A, int a, int b) {
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
