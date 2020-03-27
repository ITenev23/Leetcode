package bg.leetcode.exercises.itenev.binary_search;

/**
 * Given a binary search tree (BST),
 * find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 * <p>
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * <p>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2,
 * since a node can be a descendant of itself according to the LCA definition.
 */
public class LowestCommonAncestorBST {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val)
            return lowestCommonAncestor(root.left, p, q);
        else if (p.val > root.val && q.val > root.val)
            return lowestCommonAncestor(root.right, p, q);
        else
            return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;

        // Traverse the tree
        while (node != null) {
            if (p.val > node.val && q.val > node.val)
                node = node.right;
            else if (p.val < node.val && q.val < node.val)
                node = node.left;
            else
                // We have found the split point, i.e. the LCA node.
                return node;

        }
        return null;
    }

}
