package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete,
 * we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest.
 * You may return the result in any order.
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 */
public class DeleteNodesReturnForest {

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> remaining = new ArrayList<>();
        Set<Integer> delete = new HashSet<>();
        for (int i : to_delete)
            delete.add(i);

        dfs(root, delete, remaining);

        if (!delete.contains(root.val))
            remaining.add(root);

        return remaining;
    }

    private TreeNode dfs(TreeNode root, Set<Integer> toDelete, List<TreeNode> remaining) {
        if (root == null)
            return null;

        root.left = dfs(root.left, toDelete, remaining);
        root.right = dfs(root.right, toDelete, remaining);

        if (toDelete.contains(root.val)){
            if (root.left != null)
                remaining.add(root.left);
            if (root.right != null)
                remaining.add(root.right);

            return null;
        }
        return root;
    }

    /*********************************************************************/

    Set<Integer> set;
    List<TreeNode> result = new ArrayList<>();

    public List<TreeNode> delNodes2(TreeNode root, int[] to_delete) {
        set = Arrays.stream(to_delete).boxed().collect(Collectors.toSet());
        dfs(root, null);
        return result;
    }

    TreeNode dfs(TreeNode root, TreeNode parent) {
        if (root == null)
            return null;

        TreeNode current = set.contains(root.val) ? null : root;

        if (parent == null && current != null)
            result.add(root);

        root.left = dfs(root.left, current);
        root.right = dfs(root.right, current);
        return current;
    }


    /*********************************************************************/


    List<TreeNode> res;
    Set<Integer> delSet;

    public List<TreeNode> delNodes3(TreeNode root, int[] to_delete) {
        res = new ArrayList<>();
        delSet = new HashSet<>();
        for(int delIdx : to_delete)
            delSet.add(delIdx);

        dfs(root, null, true);
        return res;
    }

    void dfs(TreeNode n, TreeNode parent, boolean isLeft) {
        if (n == null)
            return;
        //this is where we need to add the root of the dijoined tree to result
        if (parent == null && !delSet.contains(n.val)) {
            res.add(n);
        }
        //if we need to delete this node
        if (delSet.contains(n.val)) {
            //and we have parent
            if (parent != null) {
                //disjoin the tree
                if (isLeft)
                    parent.left = null;
                else
                    parent.right = null;
            }
            //continue recursion
            dfs(n.left, null, true);
            dfs(n.right, null, false);
        } else {
            //if we can keep the node - go deeper with recursion, pass node child as current and
            //current node as parent, do this for left and right children
            dfs(n.left, n, true);
            dfs(n.right, n, false);
        }
    }

}
