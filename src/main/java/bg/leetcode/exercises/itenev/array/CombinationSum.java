package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Share
 * Given a set of candidate numbers (candidates) (without duplicates)
 * and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand, int remain, int start) {
        if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < cand.length; i++) {
                tempList.add(cand[i]);
                backtrack(list, tempList, cand, remain - cand[i], i);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

}
