package bg.leetcode.exercises.itenev.tree.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *    3
 *   / \
 *  9  20
 *    /  \
 *   15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                currentLevel.add(current.val);
                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }

            result.add(currentLevel);
        }

        return result;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        preorder(root, 1, res);
        return res;
    }

    private void preorder(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) return;

        // cur row does not exist, create a new row
        if (res.size() < level) {
            List<Integer> newRow = new ArrayList<>();
            res.add(newRow);
        }

        // retrieve the row of this level
        List<Integer> curRow = res.get(level - 1);

        // add root value to current row
        curRow.add(root.val);

        // recursively add left and right node if not null
        preorder(root.left, level + 1, res);
        preorder(root.right, level + 1, res);

        return;
    }
}
