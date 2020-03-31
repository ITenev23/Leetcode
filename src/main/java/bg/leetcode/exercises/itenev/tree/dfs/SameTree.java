package bg.leetcode.exercises.itenev.tree.dfs;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given two binary trees, write a function to check if they are the same or not.
 *
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Input:     1         1       |    Input:     1         1
 *           / \       / \      |              /           \
 *          2   3     2   3     |             2             2
 *                              |
 *         [1,2,3],   [1,2,3]   |        [1,2],     [1,null,2]
 *                              |
 * Output: true                 |    Output: false
 *
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * Output: false
 */
public class SameTree {

    /**
     * The simplest strategy here is to use recursion.
     * Check if p and q nodes are not None, and their values are equal.
     * If all checks are OK, do the same for the child nodes recursively.
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
         else if (p == null || q == null)
            return false;
         else if (p.val != q.val)
            return false;
         else
            return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }

    /************************************************************/


    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (q == null || p == null)
            return false;

        return p.val == q.val;
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (!check(p, q))
            return false;

        Deque<TreeNode> deqP = new ArrayDeque<>();
        Deque<TreeNode> deqQ = new ArrayDeque<>();
        deqP.addLast(p);
        deqQ.addLast(q);

        while (!deqP.isEmpty()) {
            p = deqP.removeFirst();
            q = deqQ.removeFirst();

            if (!check(p, q))
                return false;
            // in Java nulls are not allowed in Deque
            if (!check(p.left, q.left))
                return false;

            if (p.left != null) {
                deqP.addLast(p.left);
                deqQ.addLast(q.left);
            }
            if (!check(p.right, q.right))
                return false;
            if (p.right != null) {
                deqP.addLast(p.right);
                deqQ.addLast(q.right);
            }
        }
        return true;
    }

}
