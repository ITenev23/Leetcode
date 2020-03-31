package bg.leetcode.exercises.itenev.tree;

import java.util.*;

/**
 * Given an unordered list of pairs where
 * each pair represents a parent-child relationship (not necessary direct) in an N-ary tree.
 * Reconstruct and return the original tree.
 * You can assume that each node has a unique value.
 *
 * Input: [[1, 2], [1, 3], [1, 4], [2, 4]]
 * Output:
 *      1
 *     / \
 *    2   3
 *   /
 *  4
 *
 *  Input: [[1, 2], [1, 3], [1, 4], [1, 5], [1, 6], [1, 7], [2, 4], [2, 5], [2, 7], [3, 6], [4, 7]]
 * Output:
 *       1
 *      / \
 *     2   3
 *    / \   \
 *   4   5   6
 *  /
 * 7
 */
public class N_aryTreeReconstruction {

    public static void main(String[] args) {
        List<int[]> pairs = new ArrayList<>();
        pairs.add(new int[] {1,2});
        pairs.add(new int[] {1,3});
        pairs.add(new int[] {1,4});
        pairs.add(new int[] {1,5});
        pairs.add(new int[] {1,6});
        pairs.add(new int[] {1,7});
        pairs.add(new int[] {2,4});
        pairs.add(new int[] {2,5});
        pairs.add(new int[] {2,7});
        pairs.add(new int[] {3,6});
        pairs.add(new int[] {4,7});

        constructTree(pairs);
    }

    private static class TreeNode {
        int val;
        Set<TreeNode> children;
        public TreeNode(int val) {
            this.val = val;
            this.children = new HashSet<>();
        }
    }

    /**
     * Construct an adjacency list first, then do a dfs from the root and build the tree
     * For example 2, adjacency list will look like
     *
     * 1 : {2, 3, 4, 5, 6, 7}
     * 2 : {4, 5, 7}
     * 3 : {6}
     * 4 : {7}
     */
    public static TreeNode constructTree(List<int[]> pairs) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        for(int[] p : pairs) {
            adjList.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
        }

        TreeNode root = new TreeNode(pairs.get(0)[0]);
        dfs(root, adjList, visited);
        return root;
    }

    /**
     * As you are doing dfs, keep adding values to visited list,
     * so e.g. while exploring 1 -> 2 -> 4 -> 7,
     * it will mark 2, 4, 7 as visited,
     * hence will not consider in the next recursion cycle for 1
     */
    private static void dfs(TreeNode root, Map<Integer, List<Integer>> adjList, Set<Integer> visited) {
        visited.add(root.val);

        if (adjList.containsKey(root.val)) {
            for (Integer n : adjList.get(root.val)) {
                if (!visited.contains(n)) {
                    TreeNode next = new TreeNode(n);
                    root.children.add(next);
                    dfs(next, adjList, visited);
                }
            }
        }
    }

}
