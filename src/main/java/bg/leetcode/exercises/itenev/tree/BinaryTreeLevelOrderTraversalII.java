package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottomBFS(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();

            for (int i = 0; i < queue.size(); i++) {
                TreeNode current = queue.peek();

                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);

                level.add(current.val);
            }

            result.add(0, level);
        }

        return result;
    }

    /*********************************************************/

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(result, root, 0);
        return result;
    }

    public void dfs(List<List<Integer>> result, TreeNode root, int level) {
        if (root == null) return;
        if (level >= result.size()) {
            result.add(0, new LinkedList<>());
        }

        dfs(result, root.left, level + 1);
        dfs(result, root.right, level + 1);

        result.get(result.size() - level - 1).add(root.val);
    }

}
