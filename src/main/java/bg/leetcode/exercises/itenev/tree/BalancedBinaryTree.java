package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 * a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * Input: [3,9,20,null,null,15,7]:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: true.
 *
 * Input: [1,2,2,3,3,null,null,4,4]:
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Output: false.
 */
public class BalancedBinaryTree {
    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxDepth(root);
        return result;
    }

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        if (Math.abs(left - right) > 1)
            result = false;

        return Math.max(left, right) + 1;
    }

    /***********************************************/

    public boolean isBalanced2(TreeNode root) {
        return bottomUp(root) != -1;
    }

    private int bottomUp(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = bottomUp(root.left);
        if (leftHeight == -1) {
            return -1; // left tree is unbalanced
        }

        int rightHeight = bottomUp(root.right);
        if (rightHeight == -1) {
            return -1; // right tree is unbalanced
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // imbalance between the 2 subtrees
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /***********************************************/

    public boolean isBalanced3(TreeNode root) {
        if(root==null)
            return true;

        Map<TreeNode, Integer> map = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            if((node.left==null || node.left != null && map.containsKey(node.left)) &&
                    (node.right==null || node.right != null && map.containsKey(node.right))){
                int left = node.left == null ? 0 : map.get(node.left);
                int right = node.right == null ? 0 : map.get(node.right);

                if(Math.abs(left-right) > 1)
                    return false;

                map.put(node, Math.max(left, right) + 1);
            } else {
                if(node.left != null && !map.containsKey(node.left)){
                    stack.push(node);
                    stack.push(node.left);
                } else {
                    stack.push(node);
                    stack.push(node.right);
                }
            }
        }
        return true;
    }

}
