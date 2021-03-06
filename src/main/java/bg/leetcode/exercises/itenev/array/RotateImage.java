package bg.leetcode.exercises.itenev.array;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * <p>
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * ----------------------------------
 * Given input matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * rotate the input matrix in-place such that it becomes:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        int size = matrix.length - 1;
        int mid = matrix.length / 2;

        for (int first = 0; first < mid; ++first) {
            int last = size - first;

            for (int i = first; i < last; ++i) {
                int offset = i - first;
                int j = last - offset;

                int top = matrix[first][i];
                int right = matrix[i][last];
                int left = matrix[j][first];
                int bottom = matrix[last][j];

                //left -> top
                matrix[first][i] = left;
                //bottom -> left
                matrix[last - offset][first] = bottom;
                //right -> bottom
                matrix[last][last - offset] = right;
                //top -> right
                matrix[i][last] = top;
            }
        }
    }

    /***********************************************/

    public void rotate2(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < (size + 1) / 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[size - j - 1][i];
                matrix[size - j - 1][i] = matrix[size - i - 1][size - j - 1];
                matrix[size - i - 1][size - j - 1] = matrix[j][size - i - 1];
                matrix[j][size - i - 1] = tmp;
            }
        }
    }

    /***********************************************/

    public void rotate3(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                swap(matrix, i, j, j, i);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0, k = matrix.length - 1; j < k; j++, k--) {
                swap(matrix, i, j, i, k);
            }
        }
    }

    public void swap(int[][] m, int i, int j, int s, int t) {
        int temp = 0;
        temp = m[i][j];
        m[i][j] = m[s][t];
        m[s][t] = temp;
    }
}
