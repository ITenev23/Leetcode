package bg.leetcode.exercises.itenev.array;

import java.util.*;

/**
 * Given an array A, we can perform a pancake flip:
 * We choose some positive integer k <= A.length,
 * then reverse the order of the first k elements of A.
 * We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.
 * <p>
 * Return the k-values corresponding to a sequence of pancake flips that sort A.
 * Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 * <p>
 * Input: [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: A = [3, 2, 4, 1]
 * After 1st flip (k=4): A = [1, 4, 2, 3]
 * After 2nd flip (k=2): A = [4, 1, 2, 3]
 * After 3rd flip (k=4): A = [3, 2, 1, 4]
 * After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
 * <p>
 * Input: [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 */
public class PancakeSorting {

    /**
     * First, sort the locations from largest value of A to smallest value of A.
     * <p>
     * For each element (from largest to smallest) with location i,
     * we will first simulate where this element actually is,
     * based on the pancake flips we have done. For a pancake flip f,
     * if i <= f, then the element has moved from location i to f+1 - i.
     * <p>
     * After, we flip by i then N-- to put this element in the correct position.
     */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        int length = A.length;

        Integer[] B = new Integer[length];
        for (int i = 0; i < length; ++i)
            B[i] = i + 1;

        Arrays.sort(B, (i, j) -> A[j - 1] - A[i - 1]);

        for (int i : B) {
            for (int f : result)
                if (i <= f)
                    i = f + 1 - i;
            result.add(i);
            result.add(length--);
        }

        return result;
    }

    /***************************************************/
    /**
     * https://leetcode.com/problems/pancake-sorting/discuss/494417/Dew-It-or-True-O(n)-or-Explained-with-Diagrams
     */
    public List<Integer> pancakeSort2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] indices = new int[n];

        for (int i = 0; i < n; ++i)
            indices[arr[i] - 1] = i;

        for (int i = 0; i < n; ++i) {
            int pull = indices[i];
            dew(stack, i, pull);
            indices[arr[i] - 1] = pull;
            indices[i] = i;
            arr[pull] = arr[i];
            arr[i] = i + 1;
        }

        LinkedList<Integer> ans = new LinkedList<>();

        while (!stack.isEmpty()) {
            int x = stack.pop();
            ans.addFirst(x);
        }

        return ans;
    }

    private void dew(Stack<Integer> stack, int j, int i) {
        if (i != j) {
            add(stack, i + 1);
            add(stack, i - j + 1);
            add(stack, i - j);
            add(stack, i - j - 1);
            add(stack, i - j);
            add(stack, i + 1);
        }
    }

    private void add(Stack<Integer> stack, int x) {
        if (x > 1) {
            if (!stack.isEmpty() && stack.peek() == x) stack.pop();
            else stack.push(x);
        }
    }
}
