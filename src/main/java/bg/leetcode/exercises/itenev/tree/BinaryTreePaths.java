package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<>();
        if (root != null)
            dfs(root, "", answer);
        return answer;
    }
    private void dfs(TreeNode root, String s, List<String> paths) {
        if (root.left == null && root.right == null)
            paths.add(s + root.val);
        if (root.left != null)
            dfs(root.left, s + root.val + "->", paths);
        if (root.right != null)
            dfs(root.right, s + root.val + "->", paths);
    }

/********************************************************************************/


    public List<String> binaryTreePathsRec(TreeNode root) {
        List<String> paths = new LinkedList<>();

        if (root==null)
            return paths;

        if (root.left == null && root.right == null) {
            paths.add(Integer.toString(root.val));
            return paths;
        }

        for (String s: binaryTreePathsRec(root.left))
            paths.add(root.val + "->" + s);

        for (String s: binaryTreePathsRec(root.right))
            paths.add(root.val + "->" + s);

        return paths;
    }

}
