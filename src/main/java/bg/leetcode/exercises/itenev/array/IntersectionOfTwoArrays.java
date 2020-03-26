package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 * Each element in the result must be unique.
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * <p>
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        for (int value : nums1) {
            set.add(value);
        }
        for (int value : nums2) {
            if (set.contains(value))
                intersection.add(value);
        }

        int[] result = new int[intersection.size()];
        int index = 0;
        for (Integer i : intersection) {
            result[index++] = i;
        }

        return result;
    }

    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
            return new int[0];

        Set<Integer> s1 = buildSet(nums1);
        Set<Integer> s2 = buildSet(nums2);

        if (s1.size() < s2.size())
            return intersection(s1, s2);
        else
            return intersection(s2, s1);
    }

    private int[] intersection(Set<Integer> s1, Set<Integer> s2) {
        List<Integer> intersected = new ArrayList<>();

        for (int e : s1)
            if (s2.contains(e))
                intersected.add(e);

        return intersected.stream().mapToInt(i -> i).toArray();
    }

    private Set<Integer> buildSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int e : nums)
            set.add(e);
        return set;
    }
}
