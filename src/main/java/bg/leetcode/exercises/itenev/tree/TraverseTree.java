package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree of integers t, return its node values in the following format:
 * The first element should be the value of the tree root;
 * The next elements should be the values of the nodes at height 1 (i.e. the root children), ordered from the leftmost to the rightmost one;
 * The elements after that should be the values of the nodes at height 2 (i.e. the children of the nodes at height 1) ordered in the same way;
 *
 * t = {
 *     "value": 1,
 *     "left": {
 *         "value": 2,
 *         "left": null,
 *         "right": {
 *             "value": 3,
 *             "left": null,
 *             "right": null
 *         }
 *     },
 *     "right": {
 *         "value": 4,
 *         "left": {
 *             "value": 5,
 *             "left": null,
 *             "right": null
 *         },
 *         "right": null
 *     }
 * }
 * Output: [1, 2, 4, 3, 5]
 *
 * This t looks like this:
 *      1
 *    /   \
 *   2     4
 *    \   /
 *     3 5
 */
public class TraverseTree {

    int[] traverseTree(TreeNode t) {
        if (t == null) return new int[] {};

        List<Integer> result = new ArrayList<Integer>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(t);

        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            result.add(current.val);

            if (current.left != null)
                q.add(current.left);
            if (current.right != null)
                q.add(current.right);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
