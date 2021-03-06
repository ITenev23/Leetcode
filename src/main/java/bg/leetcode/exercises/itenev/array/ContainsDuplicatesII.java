package bg.leetcode.exercises.itenev.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
 * and the absolute difference between i and j is at most k.
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 */
public class ContainsDuplicatesII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            if (map.containsKey(current) && i - map.get(current) <= k)
                return true;
            else
                map.put(current, i);
        }

        return false;
    }
}
