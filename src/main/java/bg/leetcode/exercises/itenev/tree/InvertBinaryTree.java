package bg.leetcode.exercises.itenev.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *   Input:
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 *   Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * This is a classic tree problem that is best-suited for a recursive approach.
 */
public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        if(root == null)
            return null;

        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);

        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTreeRec(TreeNode root) {
        if (root == null)
            return root;

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTreeRec(root.left);
        invertTreeRec(root.right);

        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null)
            return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);
        }

        return root;
    }

}
