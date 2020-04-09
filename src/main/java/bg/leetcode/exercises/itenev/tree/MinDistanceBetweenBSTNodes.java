package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a Binary Search Tree (BST) with the root node root,
 * return the minimum difference between the values of any two different nodes in the tree.
 * <p>
 * Input: root = [4,2,6,1,3,null,null]
 * Output: 1
 * Note that root is a TreeNode object, not an array.
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 * <p>
 * 4
 * /   \
 * 2      6
 * / \
 * 1   3
 * <p>
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 */
public class MinDistanceBetweenBSTNodes {

    public int minDiffInBST(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        inorder(root, nodes);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < nodes.size(); i++) {
            min = Math.min(min, nodes.get(i) - nodes.get(i - 1));
        }

        return min;
    }

    private void inorder(TreeNode root, List<Integer> nodes) {
        if (root == null)
            return;

        inorder(root.left, nodes);
        nodes.add(root.val);
        inorder(root.right, nodes);
    }

    /***********************************************************************/

    Integer prev;
    int minDiff = Integer.MAX_VALUE;

    public int minDiffInBST2(TreeNode root) {
        in(root);
        return minDiff;
    }

    private void in(TreeNode root) {
        if (root == null)
            return;
        in(root.left);
        if (prev != null)
            minDiff = Math.min(minDiff, Math.abs(root.val - prev));
        prev = root.val;
        in(root.right);
    }
}
