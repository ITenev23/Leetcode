package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns),
 * return all elements of the matrix in spiral order.
 * <p>
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] i = new int[3][3];
        i[0] = new int[]{1, 2, 3};
        i[1] = new int[]{4, 5, 6};
        i[2] = new int[]{7, 8, 9};
        List<Integer> integers = spiralOrder(i);
        System.out.println();
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new LinkedList<>();
        if (matrix.length == 0) {
            return result;
        }

        int leftBound = 0;
        int rightBound = matrix[0].length - 1;
        int bottomBound = matrix.length - 1;
        int upperBound = 0;

        while (leftBound <= rightBound && upperBound <= bottomBound) {
            for (int col = leftBound; col <= rightBound; col++) {
                result.add(matrix[upperBound][col]);
            }
            upperBound++;

            for (int row = upperBound; row <= bottomBound; row++) {
                result.add(matrix[row][rightBound]);
            }
            rightBound--;

            for (int col = rightBound; upperBound <= bottomBound && col >= leftBound; col--) {
                result.add(matrix[bottomBound][col]);
            }
            bottomBound--;

            for (int row = bottomBound; leftBound <= rightBound && row >= upperBound; row--) {
                result.add(matrix[row][leftBound]);
            }
            leftBound++;
        }

        return result;
    }

    /**************************************************************************/

    /**
     * Let the array have R rows and C columns.
     * seen[r][c] denotes that the cell on the r-th row and c-th column was previously visited.
     * Our current position is (r, c), facing direction di,
     * and we want to visit R x C total cells.
     * <p>
     * As we move through the matrix, our candidate next position is (cr, cc).
     * If the candidate is in the bounds of the matrix and unseen, then it becomes our next position;
     * otherwise, our next position is the one after performing a clockwise turn.
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            int cr = r + dr[di];
            int cc = c + dc[di];
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }


    /**************************************************************************/


    public List<Integer> spiralOrder3(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix.length == 0)
            return ans;

        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;

        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++)
                ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++)
                ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--)
                    ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--)
                    ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }
}
