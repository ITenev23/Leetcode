package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * Given an array of integers and an integer k,
 * you need to find the number of unique k-diff pairs in the array.
 * Here a k-diff pair is defined as an integer pair (i, j),
 * where i and j are both numbers in the array and their absolute difference is k.
 * <p>
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * <p>
 * Input:[1, 2, 3, 4, 5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * <p>
 * Input: [1, 3, 1, 5, 4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * <p>
 * The pairs (i, j) and (j, i) count as the same pair.
 * The length of the array won't exceed 10,000.
 */
public class K_DiffPairsArray {

    /**
     * This is a pretty standard 2 pointer approach with a unique take away when looking at duplicates.
     *
     * If the left pointer has caught up to our right pointer.
     * This is between two different values so if left == right, this will never be a valid answer.
     * If we have previously used this value to accurately determine that two values compute a valid answer.
     * For this problem I used a previous variable and set it whenever we find a value where nums[r] - nums[l] == k.
     * This stops any sort of duplicates from happening.
     * As mentioned earlier we must also check that nums[r]-nums[l] == k.
     * We use the while loop to get as close as possible for each iteration but we need the final if statement to check accuracy.
     */
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 1)
            return 0;

        Arrays.sort(nums);
        int l = 0;
        int ans = 0;
        int prev = Integer.MAX_VALUE;

        for (int r = 1; r < nums.length; r++) {
            while (l < r && nums[r] - nums[l] > k)
                l++;

            if (l != r && prev != nums[l] && nums[r] - nums[l] == k) {
                ans++;
                prev = nums[l];
            }
        }
        return ans;
    }


    /************************************************************/


    public int findPairs2(int[] nums, int k) {
        Set<List<Integer>> hs = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) > k)
                    break;
                if (Math.abs(nums[i] - nums[j]) == k)
                    hs.add(new ArrayList<>(Arrays.asList(
                            Math.min(nums[i], nums[j]),
                            Math.max(nums[i], nums[j])))
                    );

            }
        }
        return hs.size();
    }

    /************************************************************/

    public int findPairs3(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }

        Arrays.sort(nums);

        int counter = 0;

        int left = 0;
        int right = 0;

        HashSet<Integer> set = new HashSet<>();

        while (right < nums.length) {
            int s = nums[right] - nums[left];

            if (s == k && left != right && !set.contains(nums[right])) {
                counter++;
                set.add(nums[right]);

                left++;
                right++;
            } else if (s > k) {
                left++;
            } else {
                right++;
            }
        }

        return counter;
    }


    /************************************************************/

    public int findPairs4(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            Integer currentCount = countMap.get(num);
            if (currentCount == null) {
                currentCount = 0;
            }
            currentCount++;
            countMap.put(num, currentCount);
        }
        int count = 0;
        for (int key : countMap.keySet()) {
            if (k == 0) {
                int occuranceCount = countMap.get(key) != null ? countMap.get(key) : 0;
                if (occuranceCount > 1) {
                    count++;
                }
            } else {
                int target2 = key + k;
                int targetCount2 = countMap.get(target2) != null ? countMap.get(target2) : 0;
                if (targetCount2 > 0) {
                    count++;
                }
            }
        }
        return count;
    }

}
