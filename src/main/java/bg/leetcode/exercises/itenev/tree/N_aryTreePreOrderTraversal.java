package bg.leetcode.exercises.itenev.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value
 * <p>
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [1,3,5,6,2,4]
 * <p>
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 */
public class N_aryTreePreOrderTraversal {

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        preorderRecursive(root, result);
        return result;
    }

    public List<Integer> preorderBottomUp(Node root) {
        if (root == null)
            return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        res.add(root.val);
        for (Node node : root.children) {
            res.addAll(preorderBottomUp(node));
        }
        return res;
    }

    private void preorderRecursive(Node root, List<Integer> nodes) {
        if (root == null)
            return;

        nodes.add(root.val);

        if (root.children == null)
            return;

        for (Node child : root.children) {
            preorderRecursive(child, nodes);
        }
    }
}
