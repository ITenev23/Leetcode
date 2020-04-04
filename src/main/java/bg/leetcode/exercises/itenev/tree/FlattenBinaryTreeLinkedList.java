package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example, given the following tree:
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 */
public class FlattenBinaryTreeLinkedList {

    public void flatten(TreeNode root) {
        flatten(root,null);
    }

    private TreeNode flatten(TreeNode root, TreeNode pre) {
        if(root == null)
            return pre;

        pre = flatten(root.right,pre);
        pre = flatten(root.left,pre);

        root.right = pre;
        root.left = null;
        pre = root;

        return pre;
    }

    /**************************************/

    public void flatten2(TreeNode root) {
        if (root == null)
            return;

        flatten2(root.left);
        flatten2(root.right);

        TreeNode right = root.right;

        if (root.left != null) {
            // step 1: concatinate root with left flatten subtree
            root.right = root.left;
            root.left = null;

            // step 2: move to the end of new added flatten subtree
            while (root.right != null)
                root = root.right;

            // step 3: contatinate left flatten subtree with flatten right subtree
            root.right = right;
        }
    }

    /**************************************************/

    public void flatten3(TreeNode root) {
        if(root==null)
            return;

        flatten3(root.left);
        flatten3(root.right);

        TreeNode left  = root.left;
        TreeNode right = root.right;
        root.left  = null;
        root.right = left;

        while(root.right != null)
            root = root.right;

        root.right = right;
    }

}
