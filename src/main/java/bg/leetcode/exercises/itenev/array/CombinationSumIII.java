package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * <p>
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<Integer>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int k, int remain, int start) {
        if (tempList.size() == k && remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i <= 9; i++) {
                tempList.add(i);
                backtrack(list, tempList, k, remain - i, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
