package bg.leetcode.exercises.itenev.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 * <p>
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * <p>
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> index = new HashMap<>();
        index.put(0, -1);
        int balance = 0;
        int maxlen = 0;

        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] * 2 - 1;
            Integer first = index.putIfAbsent(balance, i);
            if (first != null)
                maxlen = Math.max(maxlen, i - first);
            //if (first != null && i - first > maxlen)
            //            maxlen = i - first;
        }
        return maxlen;
    }

    /******************************************************************************/

    public int findMaxLength2(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private int dfs(int[] a, int l, int r) {
        if (l >= r) return 0;
        int mid = (l + r) / 2;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = mid, one = 0, zero = 0; i >= l; i--) {
            if (a[i] == 0) zero++;
            else one++;
            map.put(zero - one, zero + one);
        }
        int max = 0;
        for (int i = mid + 1, one = 0, zero = 0; i <= r; i++) {
            if (a[i] == 0)
                zero++;
            else
                one++;

            if (map.containsKey(one - zero))
                max = Math.max(max, map.get(one - zero) + zero + one);
        }

        return Math.max(max, Math.max(dfs(a, l, mid), dfs(a, mid + 1, r)));
    }

    /******************************************************************************/

    /**
     * Create "deltas" array. Each value in this array will represent (number of 1s) minus (number of 0s) seen so far.
     * Create a HashMap<Integer, Integer>, that for each delta,
     * it saves the first index we saw this delta in our array
     *      "key" is the "delta" calculated earlier
     *      "value" is first index of this delta in original array
     *
     * Example:
     *  index: [ 0  1  2  3  4  5  6  7]
     *  input: [ 0  0  1  0  0  0  1  1]
     * deltas: [-1 -2 -1 -2 -3 -4 -3 -2]
     */
    public int findMaxLength3(int[] array) {
        if (array == null) {
            return 0;
        }
        int[] deltas = findDeltas(array);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // for testcases such as [1, 0], [1, 0, 0]
        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            if (!map.containsKey(deltas[i])) {
                map.put(deltas[i], i);
            } else {
                maxLength = Math.max(maxLength, i - map.get(deltas[i]));
            }
        }
        return maxLength;
    }

    private int[] findDeltas(int[] array) {
        int[] deltas = new int[array.length];
        int delta = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                delta++;
            } else if (array[i] == 0) {
                delta--;
            }
            // if array[i] is not a 1 or 0, delta does not change

            deltas[i] = delta;
        }
        return deltas;
    }
}
