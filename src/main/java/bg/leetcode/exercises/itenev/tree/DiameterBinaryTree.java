package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Given a binary tree
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 */
public class DiameterBinaryTree {

    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return Math.max(left, right) + 1;
    }

    /*******************************************************************/

    public int diameterOfBinaryTreeMEMO(TreeNode root) {
        if(root == null) return 0;
        int[] ans = dfs(root);
        return ans[0];
    }

    private int[] dfs(TreeNode root){
        int[] ans = new int[2];
        if(root == null )
            return ans;

        int[] left = dfs(root.left);
        int[] right = dfs(root. right);

        ans[1] = 1 + Math.max(left[1], right[1]);
        ans[0] = Math.max(left[0], Math.max(right[0], left[1] + right[1]));

        return ans;
    }

}
