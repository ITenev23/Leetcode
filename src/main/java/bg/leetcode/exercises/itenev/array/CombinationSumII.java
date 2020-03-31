package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 * 1.All numbers (including target) will be positive integers.
 * 2.The solution set must not contain duplicate combinations.
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);

        backtrack(candidates, 0,target, new ArrayList<>(),result);
        return result;
    }

    private void backtrack(int[] candidates, int index, int target, ArrayList<Integer> current, List<List<Integer>> result) {
        if (target == 0){
            result.add(new ArrayList<>(current));
            return;
        } else if (target < 0) {
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i == index || candidates[i] != candidates[i - 1]){
                current.add(candidates[i]);
                backtrack(candidates, i + 1, target - candidates[i], current, result);
                current.remove(current.size() - 1);
            }
        }
    }

}
