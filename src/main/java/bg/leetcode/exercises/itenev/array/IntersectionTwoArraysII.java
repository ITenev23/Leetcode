package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 */
public class IntersectionTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> intersection = new ArrayList();

        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;  ) {
            if (nums1[i] == nums2[j]) {
                intersection.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] result = new int[intersection.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }

    /*************************************************************************/

    public int[] intersect2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        } else if (nums1.length > nums2.length) {
            return intersect2(nums2, nums1); // ensures 1st array is shorter than 2nd.
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> intersection = new ArrayList<>();
        int leftIndex = 0;
        for (int value : nums1) {
            Integer index = binarySearch(nums2, value, leftIndex);
            if (index != null) {
                intersection.add(value);
                leftIndex = index + 1;
            }
        }

        int[] result = new int[intersection.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }

    // Binary search from 'lo' to end of array.
    // If duplicates exist, return the index for the match furthest left.
    private Integer binarySearch(int[] sortedArray, int value, int lo) {
        int hi = sortedArray.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (sortedArray[mid] < value) {
                lo = mid + 1;
            } else if (sortedArray[mid] > value) {
                hi = mid - 1;
            } else {
                hi = mid;
            }
        }
        return lo < sortedArray.length && sortedArray[lo] == value
                ? lo
                : null;
    }

    /*************************************************************************/

    public int[] intersect3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap();
        for (Integer n : nums1) {
            map.merge(n, 1, Integer::sum);
        }

        List<Integer> intersection = new ArrayList();
        for (int n : nums2) {
            if (map.containsKey(n) && map.get(n) > 0) {
                intersection.add(n);
                map.merge(n, -1, Integer::sum);
            }
        }

        int[] result = new int[intersection.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = intersection.get(i);
        }
        return result;
    }
}
