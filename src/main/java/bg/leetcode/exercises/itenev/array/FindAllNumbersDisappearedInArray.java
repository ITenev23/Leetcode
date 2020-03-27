package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
 * some elements appear twice and others appear once.
 * <p>
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 * You may assume the returned list does not count as extra space.
 * <p>
 * Input: [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 */
public class FindAllNumbersDisappearedInArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= nums.length; i++) {
            set.add(i);
        }
        for (int num : nums) {
            set.remove(num);
        }
        return new ArrayList<>(set);
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int[] store = new int[nums.length + 1];

        for (int num : nums)
            ++store[num];

        for (int j = 1; j < store.length; j++)
            if (store[j] == 0)
                res.add(j);

        return res;
    }

}
