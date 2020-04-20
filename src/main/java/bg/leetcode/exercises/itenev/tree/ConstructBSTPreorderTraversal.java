package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.Stack;

/**
 * Return the root node of a binary search tree that matches the given preorder traversal.
 * <p>
 * (Recall that a binary search tree is a binary tree where for every node,
 * any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.
 * Also recall that a preorder traversal displays the value of the node first,
 * then traverses node.left, then traverses node.right.)
 * <p>
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 */
public class ConstructBSTPreorderTraversal {

    int i = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return bstFromPreorder(preorder, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int[] preorder, int bound) {
        if (i == preorder.length || preorder[i] > bound)
            return null;

        TreeNode root = new TreeNode(preorder[i++]);
        root.left = bstFromPreorder(preorder, root.val);
        root.right = bstFromPreorder(preorder, bound);
        return root;
    }

    /***********************************************************************************/

    /**
     * Iterative
     */
    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0)
            return null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);

        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = new TreeNode(preorder[i]);
            if (preorder[i] < stack.peek().val) {
                stack.peek().left = node;
            } else {
                TreeNode parent = stack.peek();
                while (!stack.isEmpty() && preorder[i] > stack.peek().val) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        return root;
    }

}
