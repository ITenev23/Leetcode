package bg.leetcode.exercises.itenev.tree;

/**
 * Find the sum of all left leaves in a given binary tree.
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null)
            return 0;
        else if (root.left != null &&
                root.left.left == null &&
                root.left.right == null)
            return root.left.val + sumOfLeftLeaves(root.right);
        else
            return sumOfLeftLeaves(root.left) +
                    sumOfLeftLeaves(root.right);
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        return this.getLeftLeavesSum(root, true);
    }

    private int getLeftLeavesSum(TreeNode node, boolean isRightEntry) {
        if (node == null)
            return 0;
        else if (node.left == null && node.right == null && !isRightEntry) //Leaf
            return node.val;
        else
            return getLeftLeavesSum(node.left, false) + getLeftLeavesSum(node.right, true);
    }

}
