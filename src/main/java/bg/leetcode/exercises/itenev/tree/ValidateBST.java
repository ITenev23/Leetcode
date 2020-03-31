package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
public class ValidateBST {

    public boolean isValidBST(TreeNode root) {
        return dfs(root, null, null);
    }

    public boolean dfs(TreeNode node, Integer min, Integer max) {
        if (node == null)
            return true;

        int current = node.val;

        if (min != null && current <= min || max != null && current >= max)
            return false;

        return dfs(node.right, current, max) && dfs(node.left, min, current);
    }

    /***************************************************************************/


    public boolean isValidBSTInorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        double inorder = - Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.val <= inorder)
                return false;

            inorder = root.val;
            root = root.right;
        }
        return true;
    }


    /***************************************************************************/


    LinkedList<TreeNode> stack = new LinkedList<>();
    LinkedList<Integer> uppers = new LinkedList<>();
    LinkedList<Integer> lowers = new LinkedList<>();

    public void update(TreeNode root, Integer lower, Integer upper) {
        stack.add(root);
        lowers.add(lower);
        uppers.add(upper);
    }

    public boolean isValidBST2(TreeNode root) {
        Integer lower = null, upper = null, val;
        update(root, lower, upper);

        while (!stack.isEmpty()) {
            root = stack.poll();
            lower = lowers.poll();
            upper = uppers.poll();

            if (root == null) continue;
            val = root.val;
            if (lower != null && val <= lower) return false;
            if (upper != null && val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

}
