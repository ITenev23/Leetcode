package bg.leetcode.exercises.itenev.tree.dfs;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree and a sum,
 * find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 *
 * Return:
 *[
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();

        findPaths(root, sum, new ArrayList<>(), paths);

        return paths;
    }

    private void findPaths(TreeNode root, int sum, ArrayList<Integer> current, List<List<Integer>> paths) {
        if (root == null)
            return;

        current.add(root.val);
        if (root.val == sum && root.left == null && root.right == null){
            paths.add(current);
            return;
        }

        findPaths(root.left, sum - root.val, new ArrayList<>(current),paths);
        findPaths(root.right, sum - root.val, new ArrayList<>(current),paths);
    }

    /******************************************************************/

    private List<List<Integer>> resultList = new ArrayList<List<Integer>>();

    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        if(root==null)
            return resultList;

        Stack<Integer> path = new Stack<>();
        pathSumInner(root, sum, path);
        return resultList;
    }

    public void pathSumInner(TreeNode root, int sum, Stack<Integer>path) {
        path.push(root.val);

        if(root.left == null && root.right == null)
            if(sum == root.val)
                resultList.add(new ArrayList<>(path));

        if(root.left != null)
            pathSumInner(root.left, sum-root.val, path);
        if(root.right != null)
            pathSumInner(root.right, sum-root.val, path);

        path.pop();
    }

}
