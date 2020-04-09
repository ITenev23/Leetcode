package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Input: [1,2,2]
 * Output:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class SubsetsII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] nums, int pos, List<List<Integer>> result, List<Integer> current) {
        if (pos <= nums.length)
            result.add(new ArrayList<>(current));

        for (int i = pos; i < nums.length; i++) {
            // avoid duplicates
            if (i > pos && nums[i] == nums[i - 1])
                continue;

            current.add(nums[i]);
            backtrack(nums, i + 1, result, current);
            current.remove(current.size() - 1);
        }
    }

    /************************************************************/

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int begin = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1])
                begin = 0;

            int size = result.size();
            for (int j = begin; j < size; j++) {
                List<Integer> cur = new ArrayList<>(result.get(j));
                cur.add(nums[i]);
                result.add(cur);
            }
            begin = size;
        }
        return result;
    }
}
