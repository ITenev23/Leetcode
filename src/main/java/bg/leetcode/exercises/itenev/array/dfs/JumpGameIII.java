package bg.leetcode.exercises.itenev.array.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
 * When you are at index i, you can jump to i + arr[i] or i - arr[i],
 * check if you can reach to any index with value 0.
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * <p>
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 */
public class JumpGameIII {

    public boolean canReach(int[] arr, int start) {
        return dfs(arr, start, 0);
    }

    private boolean dfs(int[] arr, int start, int count) {
        if (start < 0 || start >= arr.length || count > arr.length)
            return false;

        if (arr[start] == 0)
            return true;

        return dfs(arr, start + arr[start], count + 1) ||
                dfs(arr, start - arr[start], count + 1);
    }

    /*****************************************************************/

    private Set<Integer> set = new HashSet<>();

    public boolean canReach2(int[] arr, int start) {
        if (start >= 0 && start <= arr.length - 1 && arr[start] == 0) {
            return true;
        }

        if (start >= 0 && start <= arr.length - 1) {
            int rigth = start + arr[start];
            int left = start - arr[start];

            if (set.add(rigth) && canReach2(arr, rigth)) { // if the recurred idx has been found
                return true;
            }
            // if the recurred idx has been found
            return set.add(left) && canReach2(arr, left);
        }

        return false;
    }

}
