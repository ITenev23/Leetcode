package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.HashMap;
import java.util.Stack;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * You may assume that duplicates do not exist in the tree.
 * <p>
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Output:
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 */
public class ConstructBTPreorderAndInorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length)
            return null;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < inorder.length; ++i)
            map.put(inorder[i], i);

        return buildTreePostIn(inorder, 0, inorder.length - 1, postorder, 0,postorder.length - 1, map);
    }

    private TreeNode buildTreePostIn(int[] inorder, int startI, int endI, int[] postorder, int startP, int endP,
                                     HashMap<Integer, Integer> map) {
        if (startP > endP || startI > endI)
            return null;

        TreeNode root = new TreeNode(postorder[endP]);
        int ri = map.get(postorder[endP]);

        TreeNode leftchild = buildTreePostIn(inorder, startI, ri - 1, postorder, startP, startP + ri - startI - 1, map);
        TreeNode rightchild = buildTreePostIn(inorder, ri + 1, endI, postorder, startP + ri - startI, endP - 1, map);

        root.left = leftchild;
        root.right = rightchild;
        return root;
    }
}
