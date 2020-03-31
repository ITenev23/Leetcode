package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.Stack;

/**
 * Given the root node of a binary search tree,
 * return the sum of values of all nodes with value between L and R (inclusive).
 * <p>
 * The binary search tree is guaranteed to have unique values.
 * <p>
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 * <p>
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 */
public class RangeSumBST {

    private int sum;

    public int rangeSumBST(TreeNode root, int L, int R) {
        sum = 0;
        dfs(root, L, R);
        return sum;
    }

    private void dfs(TreeNode root, int min, int max) {
        if (root == null)
            return;
        if (root.val >= min && root.val <= max)
            sum += root.val;

        dfs(root.left, min, max);
        dfs(root.right, min, max);
    }

    /*******************************************************/

    public int rangeSumBST2(TreeNode root, int L, int R) {
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (node.val >= L && node.val <= R)
                    sum += node.val;
                if (node.val > L)
                    stack.push(node.left);
                if (node.val < R)
                    stack.push(node.right);
            }
        }

        return sum;
    }

    /*******************************************************/


    public int rangeSumBST3(TreeNode root, int L, int R) {
        return dfs2(root, L, R);
    }
    public int dfs2(TreeNode current, int L, int R) {
        if(current == null) return 0;
        int sum = (current.val < L) ? 0 : dfs2(current.left, L, R);
        sum += (current.val > R) ? 0 : dfs2(current.right, L, R);
        if(current.val >= L && current.val <= R) sum += current.val;
        return sum;
    }

}
