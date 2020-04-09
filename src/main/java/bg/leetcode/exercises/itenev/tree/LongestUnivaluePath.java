package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value.
 * This path may or may not pass through the root.
 * <p>
 * The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * Output: 2
 */
public class LongestUnivaluePath {

    public int longestUnivaluePath(TreeNode root) {
        int[] res = new int[1];
        if (root != null) dfs(root, res);
        return res[0];
    }

    private int dfs(TreeNode node, int[] res) {
        int left = node.left != null ? dfs(node.left, res) : 0;
        int right = node.right != null ? dfs(node.right, res) : 0;

        int leftSum = node.left != null && node.left.val == node.val ? left + 1 : 0;
        int rightSum = node.right != null && node.right.val == node.val ? right + 1 : 0;

        res[0] = Math.max(res[0], leftSum + rightSum);
        return Math.max(leftSum, rightSum);
    }

    /************************************************************************************/

    int level = 0;

    public int longestUnivaluePath2(TreeNode root) {
        if (root == null)
            return 0;

        dfs(root, root.val);
        return level;
    }

    private int dfs(TreeNode node, int val) {
        if (node == null)
            return 0;

        int left = dfs(node.left, node.val);
        int right = dfs(node.right, node.val);

        level = Math.max(level, left + right);

        if (val == node.val)
            return Math.max(left, right) + 1;

        return 0;
    }

}
