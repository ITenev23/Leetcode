package bg.leetcode.exercises.itenev.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements.
 * Each element must have the same probability of being returned.
 * <p>
 * RandomizedSet randomSet = new RandomizedSet();
 * <p>
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 * <p>
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 * <p>
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 * <p>
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 * <p>
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 * <p>
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 * <p>
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class RandomizedSet {

    List<Integer> nums;
    HashMap<Integer, Integer> locs;
    java.util.Random rand;

    public RandomizedSet() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
        rand = new java.util.Random();
    }

    public boolean insert(int val) {
        if (locs.containsKey(val))
            return false;

        locs.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!locs.containsKey(val))
            return false;

        if (locs.get(val) < nums.size() - 1) {
            int lastone = nums.get(nums.size() - 1);
            nums.set(locs.get(val), lastone);
            locs.put(lastone, locs.get(val));
        }
        locs.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }

    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}
