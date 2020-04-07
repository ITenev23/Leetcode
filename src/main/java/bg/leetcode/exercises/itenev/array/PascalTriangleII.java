package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 * <p>
 * Input: 3
 * Output: [1,3,3,1]
 */
public class PascalTriangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < rowIndex + 1; i++) {
            res.add(1);
            for (int j = i - 1; j > 0; j--) {
                res.set(j, res.get(j - 1) + res.get(j));
            }
        }
        return res;
    }

    /*************************************************/

    public List<Integer> getRow2(int k) {
        Integer[] arr = new Integer[k + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;

        for (int i = 1; i <= k; i++)
            for (int j = i; j > 0; j--)
                arr[j] = arr[j] + arr[j - 1];

        return List.of(arr);
    }

    /*************************************************/


    public List<Integer> getRow3(int k) {
        List<Integer> row = new ArrayList<>();
        if (k < 0)
            return row;

        row.add(1);
        // long -> prevent overflow
        long C = 1;

        for (int i = 1; i <= k; i++) {
            C = C * (k + 1 - i) / i;
            row.add((int) C);
        }

        return row;
    }

}
