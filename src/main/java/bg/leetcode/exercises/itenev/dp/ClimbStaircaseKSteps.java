package bg.leetcode.exercises.itenev.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * You need to climb a staircase that has n steps, and you decide to get some extra exercise by jumping up the steps.
 * You can cover at most k steps in a single jump.
 * Return all the possible sequences of jumps that you could take to climb the staircase, sorted.
 * <p>
 * For n = 4 and k = 2, the output should be
 * <p>
 * climbingStaircase(n, k) =
 * [[1, 1, 1, 1],
 * [1, 1, 2],
 * [1, 2, 1],
 * [2, 1, 1],
 * [2, 2]]
 * There are 4 steps in the staircase, and you can jump up 2 or fewer steps at a time. There are 5 potential sequences in which you jump up the stairs either 2 or 1 at a time.
 */
public class ClimbStaircaseKSteps {

    int[][] climbingStaircase(int n, int k) {
        if (n < 0) return new int[0][0];
        if (n == 0) return new int[1][0];
        Stream<IntStream> r = Stream.empty();
        for (int i = 1; i <= k; i++) {
            int[][] c = climbingStaircase(n - i, k);
            final int fi = i;
            r = Stream.concat(
                    r,
                    Arrays.stream(c).map(a -> IntStream.concat(IntStream.of(fi), Arrays.stream(a)))
            );
        }
        return r.map(IntStream::toArray).toArray(int[][]::new);
    }

    /*************************************************************************************/

    public int[][] climbingStaircase2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, res, new ArrayList<>());
        int[][] result = new int[res.size()][0];
        for (int i = 0; i < res.size(); i++) {
            result[i] = new int[res.get(i).size()];
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = res.get(i).get(j);
            }
        }
        return result;
    }

    public void backtrack(int n, int k, List<List<Integer>> res, List<Integer> t) {
        if (n == 0) {
            res.add(new ArrayList<>(t));
        }
        for (int i = 1; i <= k; i++) {
            if (i <= n) {
                t.add(i);
                backtrack(n - i, k, res, t);
                t.remove(t.size() - 1);
            }
        }
    }


    /*************************************************************************************/

    public int[][] climbingStaircase3(int n, int k) {
        List<List<Integer>> possiblePaths = getPossiblePaths(k, n);
        int[][] result = new int[possiblePaths.size()][];

        for (int i = 0; i < result.length; i++) {
            List<Integer> l = possiblePaths.get(i);
            int[] temp = new int[l.size()];
            for (int j = 0; j < l.size(); j++) {
                temp[j] = l.get(j);
            }
            result[i] = temp;
        }

        return result;
    }

    private List<List<Integer>> getPossiblePaths(int k, int n) {
        if (n == 0) {
            List<List<Integer>> l = new ArrayList<>();
            List<Integer> steps = new ArrayList<>();
            l.add(steps);

            return l;
        } else {
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 1; i <= Math.min(n, k); i++) {
                List<List<Integer>> temp = getPossiblePaths(k, n - i);
                for (List<Integer> l : temp) {
                    l.add(0, i);
                }

                result.addAll(temp);
            }

            return result;
        }
    }
}
