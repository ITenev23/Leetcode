package bg.leetcode.exercises.itenev.tree;

import bg.leetcode.exercises.itenev.common.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value.
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
 */
public class N_aryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> solution = new ArrayList<>();
        if (root == null)
            return solution;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                level.add(node.val);
                q.addAll(node.children);
            }
            solution.add(level);
        }
        return solution;
    }

    /*******************************************************/

    public List<List<Integer>> levelOrder2(Node root) {
        return levelOrder(root, 0, new ArrayList<>());
    }

    private List<List<Integer>> levelOrder(Node node, int level, List<List<Integer>> order){
        if (node == null){
            return order;
        }
        List<Integer> list = order.size() > level ?  order.get(level) : new ArrayList<>();
        list.add(node.val);
        if (order.size() <= level){
            order.add(list);
        }
        for (Node n : node.children){
            levelOrder(n, level + 1, order);
        }
        return order;
    }
}
