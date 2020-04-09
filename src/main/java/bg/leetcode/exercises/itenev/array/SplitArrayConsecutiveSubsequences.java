package bg.leetcode.exercises.itenev.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums sorted in ascending order,
 * return true if and only if you can split it into 1 or
 * more subsequences such that each subsequence consists of consecutive integers and has length at least 3.
 * <p>
 * Input: [1,2,3,3,4,5]
 * Output: True
 * You can split them into two consecutive subsequences :
 * 1, 2, 3
 * 3, 4, 5
 * <p>
 * Input: [1,2,3,3,4,4,5,5]
 * Output: True
 * You can split them into two consecutive subsequences :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * <p>
 * Input: [1,2,3,4,4,5]
 * Output: False
 */
public class SplitArrayConsecutiveSubsequences {

    public boolean isPossible(int[] nums) {
        Counter count = new Counter();
        Counter tails = new Counter();
        for (int x : nums)
            count.add(x, 1);

        for (int x : nums) {
            if (count.get(x) == 0) {
                continue;
            } else if (tails.get(x) > 0) {
                tails.add(x, -1);
                tails.add(x + 1, 1);
            } else if (count.get(x + 1) > 0 && count.get(x + 2) > 0) {
                count.add(x + 1, -1);
                count.add(x + 2, -1);
                tails.add(x + 3, 1);
            } else {
                return false;
            }
            count.add(x, -1);
        }
        return true;
    }

    private static class Counter extends HashMap<Integer, Integer> {
        public int get(int k) {
            return containsKey(k) ? super.get(k) : 0;
        }

        public void add(int k, int v) {
            put(k, get(k) + v);
        }
    }

    /***************************************************************/

    /**
     * We iterate through the array once to get the frequency of all the elements in the array
     * We iterate through the array once more and for each element we either see
     * if it can be appended to a previously constructed consecutive sequence or
     * if it can be the start of a new consecutive sequence. If neither are true, then we return false.
     */
    public boolean isPossible2(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> appendfreq = new HashMap<>();

        for (int i : nums)
            freq.put(i, freq.getOrDefault(i, 0) + 1);

        for (int i : nums) {
            if (freq.get(i) == 0)
                continue;
            else if (appendfreq.getOrDefault(i, 0) > 0) {
                appendfreq.put(i, appendfreq.get(i) - 1);
                appendfreq.put(i + 1, appendfreq.getOrDefault(i + 1, 0) + 1);
            } else if (freq.getOrDefault(i + 1, 0) > 0 && freq.getOrDefault(i + 2, 0) > 0) {
                freq.put(i + 1, freq.get(i + 1) - 1);
                freq.put(i + 2, freq.get(i + 2) - 1);
                appendfreq.put(i + 3, appendfreq.getOrDefault(i + 3, 0) + 1);
            } else return false;
            freq.put(i, freq.get(i) - 1);
        }
        return true;
    }

    /***************************************************************/

    //https://leetcode.com/problems/split-array-into-consecutive-subsequences/discuss/106495/Java-O(n)-time-and-O(1)-space-solution-greedily-extending-shorter-subsequence

    public boolean isPossible3(int[] nums) {
        int pre = Integer.MIN_VALUE;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        int cur = 0;
        int cnt = 0;
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        for (int i = 0; i < nums.length; pre = cur, p1 = c1, p2 = c2, p3 = c3) {
            for (cur = nums[i], cnt = 0; i < nums.length && cur == nums[i]; i++) {
                cnt++;
            }

            if (cur != pre + 1) {
                if (p1 != 0 || p2 != 0) {
                    return false;
                }

                c1 = cnt;
                c2 = 0;
                c3 = 0;

            } else {
                if (cnt < p1 + p2) {
                    return false;
                }

                c1 = Math.max(0, cnt - (p1 + p2 + p3));
                c2 = p1;
                c3 = p2 + Math.min(p3, cnt - (p1 + p2));
            }
        }

        return (p1 == 0 && p2 == 0);
    }
}
