package bg.leetcode.exercises.itenev.array;

/**
 * Given a binary matrix A, we want to flip the image horizontally,
 * then invert it, and return the resulting image.
 * <p>
 * To flip an image horizontally means that each row of the image is reversed.
 * For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 * <p>
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
 * For example, inverting [0, 1, 1] results in [1, 0, 0].
 * <p>
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 * <p>
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 */
public class FlippingImage {

    public int[][] flipAndInvertImage(int[][] A) {
        int length = A[0].length;

        for (int[] row : A)
            for (int i = 0; i < (length + 1) / 2; ++i) {
                int tmp = row[i] ^ 1;
                row[i] = row[length - 1 - i] ^ 1;
                row[length - 1 - i] = tmp;
            }

        return A;
    }

    public int[][] flipAndInvertImage2(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int left = 0;
            int right = A[i].length - 1;

            //swap elements
            while (left < right) {
                int temp = A[i][left];
                A[i][left++] = A[i][right];
                A[i][right--] = temp;
            }

            //swap 1 with 0 and vise versa
            for (int l = 0; l < A[i].length; l++) {
                A[i][left] = A[i][left] == 1 ? 0 : 1;
            }
        }

        return A;
    }

    public int[][] flipAndInvertImage3(int[][] A) {
        int length = A[0].length;
        int leftPtr = 0;
        int rightPtr = length - 1;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (leftPtr > rightPtr)
                    break;
                if (A[i][leftPtr] == A[i][rightPtr]) {
                    if (leftPtr == rightPtr) {
                        invert(A, i, leftPtr);
                        break;
                    }
                    invert(A, i, leftPtr);
                    invert(A, i, rightPtr);
                }
                leftPtr++;
                rightPtr--;
            }
            leftPtr = 0;
            rightPtr = A[i].length - 1;
        }
        return A;
    }

    private void invert(int[][] A, int i, int j) {
        if (A[i][j] == 0)
            A[i][j] = 1;
        else A[i][j] = 0;
    }

}
