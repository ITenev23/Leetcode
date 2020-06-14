package bg.leetcode.exercises.itenev.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.
 * <p>
 * Example:
 * Input: [1,2,3]
 * Output:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class Permutations {

    List<List<Integer>> permutations;

    public List<List<Integer>> permute(int[] nums) {
        permutations = new ArrayList<>();
        backtrack(new ArrayList<>(), nums);
        return permutations;
    }

    private void backtrack(List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length)
            permutations.add(new ArrayList<>(tempList));
        else
            for (int num : nums) {
                if (tempList.contains(num))
                    continue;

                tempList.add(num);
                backtrack(tempList, nums);
                tempList.remove(tempList.size() - 1);
            }

    }
}
